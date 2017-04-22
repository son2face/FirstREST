package legal.Interface.DatabaseConnection;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Son on 4/10/2017.
 */
public interface IDatabaseConnection {
    Connection getConnection();

    Statement createStatement();

    Statement getStatement();

    IDatabaseConnection clone();

    IDatabaseDictionary getDatabaseDictionary();
}
