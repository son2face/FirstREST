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
public class MySQLConnection implements IDatabaseConnection {
    public IDatabaseDictionary databaseDictionary = new MySQLDictionary();
    protected Connection connection;
    protected Statement statement;
    protected String url;
    protected String databaseName;
    protected String userName;
    protected String passWord;

    public MySQLConnection(String url, String databaseName, String userName, String passWord) {
        this.url = url;
        this.databaseName = databaseName;
        this.userName = userName;
        this.passWord = passWord;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + url + "/" + databaseName + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", userName, passWord);
            this.statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLSeverConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLSeverConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MySQLConnection() {
    }

    @Override
    public IDatabaseConnection clone() {
        return new MySQLConnection(this.url, this.databaseName, this.userName, this.passWord);
    }

    @Override
    public Statement getStatement() {
        return statement;
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


    public IDatabaseDictionary getDatabaseDictionary() {
        return databaseDictionary;
    }
}
