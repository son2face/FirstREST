package manager.Interface;

import legal.Interface.DatabaseConnection.IDatabaseConnection;
import org.json.simple.JSONObject;

/**
 * Created by Son on 4/28/2017.
 */
public interface IDatabaseService {
    IDatabaseConnection getDatabaseConnection(int id);

    IDatabaseConnection getDatabaseConnection();

    boolean create(String url, String name, String password, String dbName, int type);

    JSONObject get();

    boolean delete(int id);

    boolean update(int id, String url, String name, String password, String dbName, int type);

}
