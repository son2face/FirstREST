package legal.Service.DatabaseConnection;

import legal.Interface.DatabaseConnection.IDatabaseDictionary;

/**
 * Created by Son on 4/11/2017.
 */
public class MySQLDictionary implements IDatabaseDictionary {
    @Override
    public String autoIncrement() {
        return "auto_increment";
    }

    @Override
    public String beginTransaction() {
        return "START TRANSACTION;";
    }

    @Override
    public String endTransaction() {
        return "COMMIT;";
    }
}
