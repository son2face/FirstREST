package legal.Service.UtilityService;

import legal.Model.LegalInfo.LegalInfoModel;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Son on 4/8/2017.
 */
public class Utility {
    //set số hiệu văn bản
    private LegalInfoModel info = new LegalInfoModel();
    public boolean setNumber(String number_str) {
        if (number_str.toLowerCase().indexOf("số") < 0){
            return false;
        }

        //tìm số đầu tiên
        int beginIndex = 0;
        while (!Character.isDigit(number_str.charAt(beginIndex)) &&
                beginIndex < number_str.length()){
            beginIndex++;
        }
        if (beginIndex >= number_str.length()){
            return false;
        }

        //Cắt lấy phần số hiệu văn bản
        info.number = number_str.substring(beginIndex);
        return true;
    }

    //Set ngày tháng năm ban hành
    public boolean setDate_created(String date_created) {
        if (date_created.toLowerCase().indexOf("ngày") < 0){
            return false;
        }

        if (date_created.toLowerCase().indexOf("tháng") < 0){
            return false;
        }

        if (date_created.toLowerCase().indexOf("năm") < 0){
            return false;
        }

        //ngày
        String day = "";
        //tháng
        String month = "";
        //năm
        String year = "";

        //cắt line thành các từ
        String[] words = date_created.split("[^0-9]+");
        int idx = 0;

        while (idx < words.length && day == ""){
            if (words[idx].length() > 0){
                char c = words[idx].charAt(0);
                if (Character.isDigit(c)){
                    day = words[idx];
                }
            }
            idx++;
        }

        while (idx < words.length && month == ""){
            if (words[idx].length() > 0){
                char c = words[idx].charAt(0);
                if (Character.isDigit(c)){
                    month = words[idx];
                }
            }
            idx++;
        }

        while (idx < words.length && year == ""){
            if (words[idx].length() > 0){
                char c = words[idx].charAt(0);
                if (Character.isDigit(c)){
                    year = words[idx];
                }
            }
            idx++;
        }

        if (day == "" || month == "" || year == ""){
            return false;
        }

        info.dateCreated = Date.valueOf(year + "-" + month + "-" + day);
        return true;
    }

    public void setTitle(String title) {

        info.title = title;
    }

    //set ngày tháng tháng năm có hiệu lực
    public boolean setDate_exec(String date_exec) {
        if (date_exec.toLowerCase().indexOf("ngày") < 0){
            return false;
        }

        if (date_exec.toLowerCase().indexOf("tháng") < 0){
            return false;
        }

        if (date_exec.toLowerCase().indexOf("năm") < 0){
            return false;
        }

        //ngày
        String day = "";
        //tháng
        String month = "";
        //năm
        String year = "";
        //cắt line thành các từ
        String[] words = date_exec.split("[^0-9]+");
        int idx = 0;

        while (idx < words.length && day == ""){
            if (words[idx].length() > 0){
                char c = words[idx].charAt(0);
                if (Character.isDigit(c)){
                    day = words[idx];
                }
            }
            idx++;
        }

        while (idx < words.length && month == ""){
            if (words[idx].length() > 0){
                char c = words[idx].charAt(0);
                if (Character.isDigit(c)){
                    month = words[idx];
                }
            }
            idx++;
        }

        while (idx < words.length && year == ""){
            if (words[idx].length() > 0){
                char c = words[idx].charAt(0);
                if (Character.isDigit(c)){
                    year = words[idx];
                }
            }
            idx++;
        }

        if (day == "" || month == "" || year == ""){
            return false;
        }

        info.dateExecute = Date.valueOf(year + "-" + month + "-" + day);
        return true;
    }

    //set người kí
    public boolean setConfirmation(String confirmation) {
        for (int i = 0; i < confirmation.length(); i++){
            if (Character.isLetter(confirmation.codePointAt(i)) == false &&
                    Character.isWhitespace(confirmation.codePointAt(i)) == false){
                return false;
            }
        }

        String[] words = confirmation.split("\\s+");
        if (words.length < 2 || words.length > 5){
            return false;
        }

        info.confirmation = confirmation;
        return true;
    }

    //set cơ quan ban hành
    public boolean setInstitution(String institution) {
        for (int i = 0; i < institution.length(); i++){
            if (Character.isUpperCase(institution.codePointAt(i)) == false &&
                    Character.isWhitespace(institution.codePointAt(i)) == false){
                return false;
            }
        }
        info.institution = institution;
        return true;
    }

    public boolean setStanding(String standing) {
        for (int i = 0; i < standing.length(); i++){
            if (Character.isUpperCase(standing.codePointAt(i)) == false &&
                    Character.isWhitespace(standing.codePointAt(i)) == false){
                return false;
            }
        }
        info.standing = standing;
        return true;
    }

    public String getType() {
        Map<String, String> map = new HashMap<String, String>();

        map.put("CT", "Chỉ Thị");
        map.put("NQ", "Nghị Quyết");
        map.put("NQLT", "Nghị Quyết Liên Tịch");
        map.put("TT", "Thông Tư");
        map.put("NĐ", "Nghị Định");
        map.put("QĐ", "Quyết Định");
        map.put("L", "Lệnh");
        map.put("TTLT", "Thông Tư Liên Tịch");

        return map.get(info.type);
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()){
            return;
        }
        int idx1 = type.lastIndexOf("/");
        int idx2 = type.lastIndexOf("-");
        if (idx1 >=0 && idx1 < idx2){
            info.type = type.substring(idx1+1, idx2);
        }
        else {
            info.type = type;
        }
    }

    public LegalInfoModel getLegalInfo(){
        return info;
    }
}
