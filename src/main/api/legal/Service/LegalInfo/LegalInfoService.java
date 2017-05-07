package legal.Service.LegalInfo;

import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Interface.LegalInfo.ILegalInfoService;
import legal.Model.LegalInfo.LegalInfoModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Son on 5/5/2017.
 */
public class LegalInfoService implements ILegalInfoService {

    @Override
    public JSONObject get() {
        return null;
    }

    @Override
    public JSONObject getLimit(String query, int limit, String dateS) {
        JSONObject obj = new JSONObject();
        LegalInfoEntity legalInfoEntity = new LegalInfoEntity();
        int statusCode = 200;
        Date date;
        JSONArray data = new JSONArray();
        try {
            date = Date.valueOf(dateS);
        } catch (Exception e) {
            date = Date.valueOf(LocalDate.now());
        }
        List<LegalInfoModel> t = null;
        try {
            t = (List<LegalInfoModel>) (List<?>) legalInfoEntity.selectLimit(query, limit, date);
        } catch (SQLException e) {
            obj.put("status", 500);
            obj.put("data", data);
            return obj;
        }
        for (LegalInfoModel x : t) {
            data.add(x.toJsonObject());
        }
        obj.put("status", statusCode);
        obj.put("data", data);
        return obj;
    }

    @Override
    public boolean create(String number, String type, String title, String dateCreated, String dateExecute, String standing, String status, String confirmation, String position, String institution) {
        LegalInfoEntity legalInfoEntity = new LegalInfoEntity(new LegalInfoModel(0, number, dateCreated, title, dateExecute, standing, confirmation, institution, type, status, position));
        try {
            legalInfoEntity.insert();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        LegalInfoEntity legalInfoEntity = new LegalInfoEntity(new LegalInfoModel(id, "", new Date(1990,1,1), "", new Date(1990,1,1), "", "", "", "", "", ""));
        try {
            legalInfoEntity.delete();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(int id, String number, String type, String title, String dateCreated, String dateExecute, String standing, String status, String confirmation, String position, String institution) {
        LegalInfoEntity legalInfoEntity = new LegalInfoEntity(new LegalInfoModel(id, number, dateCreated, title, dateExecute, standing, confirmation, institution, type, status, position));
        try {
            legalInfoEntity.update();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
