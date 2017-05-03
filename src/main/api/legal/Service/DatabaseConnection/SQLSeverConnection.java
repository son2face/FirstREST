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
public class SQLSeverConnection implements IDatabaseConnection {
    public IDatabaseDictionary databaseDictionary = new SQLSeverDictionary();
    private Connection connection;
    private Statement statement;
    private DatabaseModel databaseModel;

    public SQLSeverConnection(DatabaseModel databaseModel) {
        this.databaseModel = new DatabaseModel(databaseModel.id, databaseModel.typeDB, databaseModel.url, databaseModel.databaseName, databaseModel.userName, databaseModel.passWord);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection("jdbc:sqlserver://" + this.databaseModel.url + ";" +
                    "databaseName=" + this.databaseModel.databaseName + ";user=" + this.databaseModel.userName + ";password=" + this.databaseModel.passWord);
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
        return new SQLSeverConnection(this.databaseModel);
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
