package legal.Service.LegalProcess;

import legal.Interface.LegalProcess.ILegalProcess;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Service.UtilityService.Utility;

import java.util.List;

public abstract class TextProcess implements ILegalProcess {
	protected Object data;
	Boolean valid = false;
	LegalInfoModel legalInfo = new LegalInfoModel();
	//Thuộc tính lines, chứa các dòng văn bản
	public List<String> lines;
	//Hàm tạo có tham số là loại dữ liệu đầu vào
	public TextProcess(Object data){
		this.data = data;
	}
	
	public void process() {
		Utility utility = new Utility();
		int idx = -1;

		//Cơ quan ban hành
		while (idx < lines.size()-1){
			idx++;
			if (utility.setInstitution(lines.get(idx))){
				break;
			}
		}
		
		//Số hiệu văn bản
		while (idx < lines.size()-1){
			idx++;
			if (utility.setNumber(lines.get(idx))){
				break;
			}
		}
		
		//Set loại văn bản
		utility.setType(utility.getLegalInfo().number);
		
		//Ngày tháng năm ban hành
		while (idx < lines.size()-1){
			idx++;
 			if (utility.setDate_created(lines.get(idx))){
				break;
			}
		}
		
		while (idx<lines.size()-1){
			idx++;
			int idx_hl = lines.get(idx).toLowerCase().indexOf("có hiệu lực");
			if (idx_hl >= 0){
				if (utility.setDate_exec(lines.get(idx).substring(idx_hl)) == false){
					while (idx < lines.size()-1){
						idx++;
						if (utility.setDate_exec(lines.get(idx))){
							break;
						}
					}
				}
				else{
					break;
				}
			}
		}
		
		//Chức vụ
		while (idx < lines.size()-1){
			idx++;
			if (utility.setStanding(lines.get(idx))){
				break;
			}
		}
		
		//Người kí
		while (idx < lines.size()-1){
			idx++;
			if (utility.setConfirmation(lines.get(idx))){
				break;
			}
		}
		legalInfo = utility.getLegalInfo();
		valid = true;
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	@Override
	public LegalInfoModel getInfo() {
		return legalInfo;
	}

	@Override
	public List<String> getLines() {
		return lines;
	}

	@Override
	public String getData() {
		return getLines().toString();
	}
}
