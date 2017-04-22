package legal.Service.DatabaseConnection;

import legal.Interface.DatabaseConnection.IDatabaseConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Son on 4/10/2017.
 */
public class DatabaseConnectionFactory {
    private static List<IDatabaseConnection> databaseConnection = new ArrayList<>();
    public static void setDatabaseConnection(IDatabaseConnection databaseConnection, int id){
        DatabaseConnectionFactory.databaseConnection.set(id,databaseConnection);
    }

    public static int addDatabaseConnection(IDatabaseConnection databaseConnection){
        DatabaseConnectionFactory.databaseConnection.add(databaseConnection);
        return  DatabaseConnectionFactory.databaseConnection.indexOf(databaseConnection);
    }

    public static IDatabaseConnection getDatabaseConnection(int id){
        return DatabaseConnectionFactory.databaseConnection.get(id).clone();
    }
}
