package legal.Interface.LegalInfo;

import legal.Model.LegalInfo.LegalInfoModel;
import legal.Model.LegalInfo.SearchLegalInfoModel;
import org.json.simple.JSONObject;

/**
 * Created by Son on 4/8/2017.
 */
public interface ILegalInfoService {
    JSONObject get(SearchLegalInfoModel searchLegalInfoModel);

    LegalInfoModel get(int id);

    LegalInfoModel create(String number, String type, String title, String dateCreated, String dateExecute, String standing, String status, String confirmation, String position, String institution);
    boolean delete(int id);

    LegalInfoModel update(int id, String number, String type, String title, String dateCreated, String dateExecute, String standing, String status, String confirmation, String position, String institution);
}
