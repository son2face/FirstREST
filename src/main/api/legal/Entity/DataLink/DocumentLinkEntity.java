package legal.Entity.DataLink;

import legal.Interface.DatabaseCommunication.IDatabaseEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Model.DataLink.DocumentLinkModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Son on 4/10/2017.
 */
public class DocumentLinkEntity implements IDatabaseEntity {
    private static final String tableName = "documentlink";
    private static IDatabaseConnection DATABASECONNECTION;
    public DocumentLinkModel documentLink;
    private IDatabaseConnection databaseConnection;

    public DocumentLinkEntity(int id, int linkId, String name, String documentLink) {
        this.documentLink = new DocumentLinkModel(id, linkId, name, documentLink);
        databaseConnection = DATABASECONNECTION.clone();
    }

    public DocumentLinkEntity(DocumentLinkEntity documentLinkEntity) {
        this.documentLink.id = documentLinkEntity.documentLink.id;
        this.documentLink.linkId = documentLinkEntity.documentLink.linkId;
        this.documentLink.name = documentLinkEntity.documentLink.name;
        this.documentLink.documentLink = documentLinkEntity.documentLink.documentLink;
        databaseConnection = DATABASECONNECTION.clone();
    }

    public static void setDatabaseConnection(IDatabaseConnection databaseConnection) {
        DocumentLinkEntity.DATABASECONNECTION = databaseConnection;
    }

    public static String getTableName() {
        return tableName;
    }

    @Override
    public void insert() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("INSERT INTO " + tableName + "(link,name,documentLink) VALUES (" + documentLink.linkId + ",'" + documentLink.name + "','" + documentLink.documentLink + "');");
    }

    @Override
    public void update() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("UPDATE " + tableName + "SET link = " + documentLink.linkId + ", name = '" + documentLink.name + "', documentLink = '" + documentLink.documentLink + "' WHERE id = " + documentLink.id + ";");
    }

    @Override
    public void delete() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + documentLink.id + ";");
    }

    @Override
    public List<Object> select() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new DocumentLinkModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4)));
        }
        return result;
    }

    public List<Object> select(String link) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE link = '" + link + "';");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new DocumentLinkModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4)));
        }
        return result;
    }

    @Override
    public List<Object> select(int id) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id + ";");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new DocumentLinkModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4)));
        }
        return result;
    }

    @Override
    public void insert(List<Object> data) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute(databaseConnection.getDatabaseDictionary().beginTransaction());
        for (DocumentLinkModel documentLink : (List<DocumentLinkModel>) (List<?>) data) {
            statement.executeUpdate("INSERT INTO " + tableName + "(link,name,documentLink) VALUES (" + documentLink.linkId + ",'" + documentLink.name + "','" + documentLink.documentLink + "');");
        }
        statement.execute(databaseConnection.getDatabaseDictionary().endTransaction());
    }

    @Override
    public void create() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute("CREATE TABLE " + tableName + "(id INT " + databaseConnection.getDatabaseDictionary().autoIncrement() + " PRIMARY KEY," +
                "link VARCHAR(1000),name VARCHAR (1000), documentLink VARCHAR (1000));");
    }

    @Override
    public void truncate() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute("TRUNCATE TABLE " + tableName + ";");
    }

    @Override
    public void drop() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute("DROP TABLE " + tableName + ";");
    }

}
