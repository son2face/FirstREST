package legal.Interface.LegalInfo;

import org.json.simple.JSONObject;

/**
 * Created by Son on 4/8/2017.
 */
public interface ILegalInfoService {
    JSONObject get();
    JSONObject getLimit(String query,int limit,String date);
    boolean create(String number,String type, String title, String dateCreated, String dateExecute, String standing, String status, String confirmation, String position, String institution);
    boolean delete(int id);
    boolean update(int id, String number,String type, String title, String dateCreated, String dateExecute, String standing, String status, String confirmation, String position, String institution);
}
