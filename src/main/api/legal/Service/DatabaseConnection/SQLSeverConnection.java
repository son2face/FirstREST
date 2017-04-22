package legal.Service.DatabaseConnection;

import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Interface.DatabaseConnection.IDatabaseDictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Son on 4/10/2017.
 */
public class SQLSeverConnection implements IDatabaseConnection{
    public IDatabaseDictionary databaseDictionary = new SQLSeverDictionary();
    private Connection connection;
    private Statement statement;
    private String url;
    private String databaseName;
    private String userName;
    private String passWord;

    public SQLSeverConnection(String url, String databaseName, String userName, String passWord) {
        this.url = url;
        this.databaseName = databaseName;
        this.userName = userName;
        this.passWord = passWord;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection("jdbc:sqlserver://" + url + ";" +
                    "databaseName=" + databaseName + ";user=" + userName + ";password=" + passWord);
            this.statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLSeverConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLSeverConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Statement getStatement() {
        return statement;
    }

    @Override
    public IDatabaseConnection clone() {
        return new SQLSeverConnection(this.url, this.databaseName, this.userName, this.passWord);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Statement createStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IDatabaseDictionary getDatabaseDictionary() {
        return databaseDictionary;
    }
}
