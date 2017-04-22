package legal.Service.DatabaseConnection;

import legal.Interface.DatabaseConnection.IDatabaseDictionary;

/**
 * Created by Son on 4/11/2017.
 */
public class SQLSeverDictionary implements IDatabaseDictionary{
    @Override
    public String autoIncrement() {
        return "IDENTITY(1,1)";
    }

    @Override
    public String beginTransaction() {
        return "BEGIN TRANSACTION;";
    }

    @Override
    public String endTransaction() {
        return "COMMIT;";
    }
}
