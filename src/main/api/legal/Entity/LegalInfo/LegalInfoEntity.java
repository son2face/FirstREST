package legal.Entity.LegalInfo;

import legal.Interface.DatabaseCommunication.IDatabaseEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Model.DataLink.DataLinkModel;
import legal.Model.LegalInfo.LegalInfoModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Son on 4/11/2017.
 */
public class LegalInfoEntity implements IDatabaseEntity {
    private static final String tableName = "LegalInfo";
    private static IDatabaseConnection DATABASECONNECTION;
    LegalInfoModel legalInfo;
    private IDatabaseConnection databaseConnection;

    public LegalInfoEntity(LegalInfoModel legalInfo) {
        this.legalInfo = legalInfo;
        databaseConnection = DATABASECONNECTION.clone();
    }

    public LegalInfoEntity() {
        databaseConnection = DATABASECONNECTION.clone();
    }

    public static void setDatabaseConnection(IDatabaseConnection databaseConnection) {
        LegalInfoEntity.DATABASECONNECTION = databaseConnection;
    }

    @Override
    public void insert() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("INSERT INTO " + tableName + "(" +
                "number ,dateCreated  ,title , dateExecute , standing, confirmation , institution, type, status, position" +
                ") VALUES (N'" + legalInfo.number + "','" + legalInfo.dateCreated + "',N'" + legalInfo.title + "','" + legalInfo.dateExecute + "',N'" + legalInfo.standing + "',N'" + legalInfo.confirmation + "',N'" + legalInfo.institution + "',N'" + legalInfo.type + "',N'" + legalInfo.status + "',N'" + legalInfo.position + "');");
    }

    public List<Object> select(String number) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE number = N'" + number + "';");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new LegalInfoModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));
        }
        return result;
    }

    public List<Object> selectLimit(String title, int limit, Date date) throws SQLException {
        Statement statement = databaseConnection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT TOP(" + limit + ") * FROM " + tableName + " WHERE TYPE LIKE N'%" + number + "%' AND dateExecute < '" + date + "';");
//        System.out.println("SELECT * FROM " + tableName + " WHERE TITLE LIKE '%" + number + "%' AND dateCreated < '" + date + "' LIMIT " + limit + ";");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE title LIKE '%" + title + "%' AND dateCreated < '" + date + "' LIMIT " + limit + ";");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new LegalInfoModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));
        }
        return result;
    }

    @Override
    public List<Object> select(int id) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id + ";");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new LegalInfoModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));
        }
        return result;
    }

    @Override
    public void update() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("UPDATE " + tableName + "SET number = N'" + legalInfo.number + "',dateCreated = '" + legalInfo.dateCreated + "' ,title = N'" + legalInfo.title + "', dateExecute = '" + legalInfo.dateExecute + "', standing = N'" + legalInfo.standing + "', confirmation = N'" + legalInfo.confirmation + "', institution = N'" + legalInfo.institution + "', type = N'" + legalInfo.type + "', status = N'" + legalInfo.status + "' WHERE id = " + legalInfo.id + ";");
    }

    @Override
    public void delete() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + legalInfo.id + ";");
    }

    @Override
    public List<Object> select() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new LegalInfoModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));
        }
        return result;
    }

    @Override
    public void insert(List<Object> data) throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute(databaseConnection.getDatabaseDictionary().beginTransaction());
        for (DataLinkModel dataLink : (List<DataLinkModel>) (List<?>) data) {
            statement.executeUpdate("INSERT INTO " + tableName + "(" +
                    "number ,dateCreated  ,title , dateExecute , standing, confirmation , institution, type, status" +
                    ") VALUES (N'" + legalInfo.number + "','" + legalInfo.dateCreated + "',N'" + legalInfo.title + "','" + legalInfo.dateExecute + "',N'" + legalInfo.standing + "',N'" + legalInfo.confirmation + "',N'" + legalInfo.institution + "',N'" + legalInfo.type + "',N'" + legalInfo.status + "');");
        }
        statement.execute(databaseConnection.getDatabaseDictionary().endTransaction());
    }

    @Override
    public void create() throws SQLException {
        Statement statement = databaseConnection.createStatement();
        statement.execute("CREATE TABLE " + tableName + "(id INT " + databaseConnection.getDatabaseDictionary().autoIncrement() + " PRIMARY KEY," +
                "number NVARCHAR(100),dateCreated DATE ,title NVARCHAR(1000), dateExecute DATE , standing NVARCHAR(1000), confirmation NVARCHAR(1000), institution NVARCHAR(1000), type NVARCHAR(1000), status NVARCHAR(1000), position NVARCHAR(1000));");
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
