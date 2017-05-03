package legal.Entity.DataLink;

import legal.Interface.DatabaseCommunication.IDatabaseEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Model.DataLink.LegalLinkModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Son on 4/10/2017.
 */
public class LegalLinkEntity implements IDatabaseEntity {
    private static final String tableName = "LegalLink";
    private static IDatabaseConnection DATABASECONNECTION;
    LegalLinkModel legalLink;
    private IDatabaseConnection databaseConnection;

    public LegalLinkEntity(int id, int idLegal, int idLink) {
        this.legalLink = new LegalLinkModel(id, idLegal, idLink);
        databaseConnection = DATABASECONNECTION.clone();
    }

    public LegalLinkEntity(LegalLinkEntity legalLinkEntity) {
        this.legalLink.id = legalLinkEntity.legalLink.id;
        this.legalLink.idLegal = legalLinkEntity.legalLink.idLegal;
        this.legalLink.idLink = legalLinkEntity.legalLink.idLink;
        databaseConnection = DATABASECONNECTION.clone();
    }

    public static void setDatabaseConnection(IDatabaseConnection databaseConnection) {
        LegalLinkEntity.DATABASECONNECTION = databaseConnection;
    }

    public static String getTableName() {
        return tableName;
    }

    @Override
    public void insert() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("INSERT INTO " + tableName + "(idLegal,idLink) VALUES (" + legalLink.idLegal + "," + legalLink.idLink + ");");
    }

    @Override
    public void update() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("UPDATE " + tableName + "SET idLegal = " + legalLink.idLegal + ", idLink = " + legalLink.idLink + " WHERE id = " + legalLink.id + ";");
    }

    @Override
    public void delete() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + legalLink.id + ";");
    }

    @Override
    public List<Object> select() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new LegalLinkModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
        }
        return result;
    }

    public List<Object> select(String link) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE link = " + link + ";");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new LegalLinkModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
        }
        return result;
    }

    @Override
    public List<Object> select(int id) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id + ";");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new LegalLinkModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
        }
        return result;
    }

    @Override
    public void insert(List<Object> data) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute(databaseConnection.getDatabaseDictionary().beginTransaction());
        for (LegalLinkModel legalLink : (List<LegalLinkModel>) (List<?>) data) {
            statement.executeUpdate("INSERT INTO " + tableName + "(idLegal,idLink) VALUES (" + legalLink.idLegal + "," + legalLink.idLink + ");");
        }
        statement.execute(databaseConnection.getDatabaseDictionary().endTransaction());
    }

    @Override
    public void create() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute("CREATE TABLE " + tableName + "(id INT " + databaseConnection.getDatabaseDictionary().autoIncrement() + " PRIMARY KEY," +
                "idLegal INT,idLink INT);");
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
