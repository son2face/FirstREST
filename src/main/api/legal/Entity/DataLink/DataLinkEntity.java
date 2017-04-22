package legal.Entity.DataLink;

import legal.Interface.DatabaseCommunication.IDatabaseEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Model.DataLink.DataLinkModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Son on 4/10/2017.
 */
public class DataLinkEntity implements IDatabaseEntity {
    DataLinkModel dataLink;
    private static final String tableName = "datalink";
    private static IDatabaseConnection databaseConnection;

    public static void setDatabaseConnection(IDatabaseConnection databaseConnection) {
        DataLinkEntity.databaseConnection = databaseConnection;
    }

    public DataLinkEntity(int id, String link, String data) {
        this.dataLink = new DataLinkModel(id, link, data);
    }

    public DataLinkEntity(DataLinkEntity dataLinkEntity) {
        this.dataLink.id = dataLinkEntity.dataLink.id;
        this.dataLink.link = dataLinkEntity.dataLink.link;
        this.dataLink.data = dataLinkEntity.dataLink.data;
    }

    public static String getTableName() {
        return tableName;
    }

    @Override
    public void insert() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("INSERT INTO " + tableName + "(link,data) VALUES (N'" + dataLink.link + "',N'" + dataLink.data + "');");
    }

    @Override
    public void update() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("UPDATE " + tableName + "SET link = N'" + dataLink.link + "', data = N'" + dataLink.data + "' WHERE id = " + dataLink.id + ";");
    }

    @Override
    public void delete() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + dataLink.id + ";");
    }

    @Override
    public List<Object> select() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new DataLinkModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return result;
    }

    public List<Object> select(String link) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE link = N'" + link + "';");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new DataLinkModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return result;
    }

    @Override
    public List<Object> select(int id) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id + ";");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new DataLinkModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return result;
    }

    @Override
    public void insert(List<Object> data) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute(databaseConnection.getDatabaseDictionary().beginTransaction());
        for (DataLinkModel dataLink : (List<DataLinkModel>) (List<?>) data) {
            statement.executeUpdate("INSERT INTO " + tableName + "(link,data) VALUES (N'" + dataLink.link + "',N'" + dataLink.data + "');");
        }
        statement.execute(databaseConnection.getDatabaseDictionary().endTransaction());
    }

    @Override
    public void createTable() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute("CREATE TABLE " + tableName + "(id INT " + databaseConnection.getDatabaseDictionary().autoIncrement() + " PRIMARY KEY," +
                "link NVARCHAR(1000),data TEXT);");
    }

    @Override
    public void truncateTable() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute("TRUNCATE TABLE " + tableName + ";");
    }

    @Override
    public void dropTable() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute("DROP TABLE " + tableName + ";");
    }

}
