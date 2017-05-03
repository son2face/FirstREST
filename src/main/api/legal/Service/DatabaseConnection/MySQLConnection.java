package legal.Service.DatabaseConnection;

import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Interface.DatabaseConnection.IDatabaseDictionary;
import manager.Model.DatabaseModel;

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
    protected DatabaseModel databaseModel;

    public MySQLConnection(DatabaseModel databaseModel) {
        this.databaseModel = new DatabaseModel(databaseModel.id, databaseModel.typeDB, databaseModel.url, databaseModel.databaseName, databaseModel.userName, databaseModel.passWord);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(this.databaseModel.url);
            System.out.println(this.databaseModel.databaseName);
            System.out.println(this.databaseModel.userName);
            System.out.println(this.databaseModel.passWord);

            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.databaseModel.url + "/" + this.databaseModel.databaseName + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&autoReconnect=true", this.databaseModel.userName, this.databaseModel.passWord);
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
        return new MySQLConnection(this.databaseModel);
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
