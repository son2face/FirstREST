package test;

import legal.Entity.DataLink.DataLinkEntity;
import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Interface.LegalProcess.ILegalProcess;
import legal.Model.DataLink.DataLinkModel;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Service.DatabaseConnection.MySQLConnection;
import legal.Service.LegalProcess.LinkProcess;
import manager.Model.DatabaseModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Son on 4/10/2017.
 */
public class TestDB {
    public static void main(String[] args) throws SQLException {
        DatabaseModel databaseModel = new DatabaseModel(0, 0, "112.137.129.95", "Legal", "nguyenson", "root");
        IDatabaseConnection connection = new MySQLConnection(databaseModel);
        LegalInfoEntity.setDatabaseConnection(connection);
        ILegalProcess processLegal = new LinkProcess("http://vbpl.vn/bacgiang/Pages/vbpq-toanvan.aspx?ItemID=112233");
        LegalInfoModel res = processLegal.getInfo();
        LegalInfoEntity test = new LegalInfoEntity(res);
        test.create();
        test.insert();
        List<LegalInfoModel> t = (List<LegalInfoModel>) (List<?>) test.select(res.number);
        System.out.println(t.get(0).toString());
        System.out.println(processLegal.getData());
        test.drop();
//        testLegalInfoEntity();
    }

    static void testDataLinkEntity() throws SQLException {
        DatabaseModel databaseModel = new DatabaseModel(0, 0, "112.137.129.95", "Legal", "nguyenson", "root");
        IDatabaseConnection connection = new MySQLConnection(databaseModel);
        DataLinkEntity.setDatabaseConnection(connection);
        DataLinkEntity test = new DataLinkEntity(1, "2", "2");
        test.create();
        test.insert();
        List<DataLinkModel> t = (List<DataLinkModel>) (List<?>) test.select("2");
        for (DataLinkModel x : t) {
            System.out.println(x.link);
        }
        test.drop();
    }

    static void testLegalInfoEntity() throws SQLException {
        DatabaseModel databaseModel = new DatabaseModel(0, 0, "112.137.129.95", "Legal", "nguyenson", "root");
        IDatabaseConnection connection = new MySQLConnection(databaseModel);
        LegalInfoEntity.setDatabaseConnection(connection);
        LinkProcess link = new LinkProcess("http://vbpl.vn/botuphap/Pages/vbpq-toanvan.aspx?ItemID=118077&dvid=41");
        LegalInfoEntity test = new LegalInfoEntity(link.getInfo());
        test.insert();
        List<LegalInfoModel> t = (List<LegalInfoModel>) (List<?>) test.select(1);
        for (LegalInfoModel x : t) {
            System.out.println(x.toString());
        }
    }
}
