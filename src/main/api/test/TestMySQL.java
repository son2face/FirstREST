package test;

import legal.Entity.DataLink.DataLinkEntity;
import legal.Entity.DataLink.DocumentLinkEntity;
import legal.Entity.DataLink.LegalLinkEntity;
import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Interface.LegalProcess.ILegalProcess;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Service.DatabaseConnection.DatabaseConnectionFactory;
import legal.Service.DatabaseConnection.MySQLConnection;
import legal.Service.LegalProcess.LinkProcess;

import java.sql.SQLException;

/**
 * Created by Son on 4/14/2017.
 */
public class TestMySQL {
    public static void main(String[] args) throws SQLException {
        int idConnection = DatabaseConnectionFactory.addDatabaseConnection(new MySQLConnection("127.0.0.1:3306", "test", "root","root"));
        IDatabaseConnection connection = DatabaseConnectionFactory.getDatabaseConnection(idConnection);
        LegalInfoEntity.setDatabaseConnection(connection);
        DataLinkEntity.setDatabaseConnection(connection);
        LegalLinkEntity.setDatabaseConnection(connection);
        DocumentLinkEntity.setDatabaseConnection(connection);
        ILegalProcess processLegal = new LinkProcess("http://vbpl.vn/tw/Pages/vbpq-toanvan.aspx?ItemID=1");
        LegalInfoModel res = processLegal.getInfo();
        LegalInfoEntity legalInfoEntity = new LegalInfoEntity(res);
        legalInfoEntity.dropTable();
        legalInfoEntity.createTable();
        DataLinkEntity dataLinkEntity = new DataLinkEntity(1, "2", "2");
        dataLinkEntity.dropTable();
        dataLinkEntity.createTable();
        DocumentLinkEntity documentLinkEntity = new DocumentLinkEntity(1, 1, "2", "");
        documentLinkEntity.dropTable();
        documentLinkEntity.createTable();
        LegalLinkEntity legalLinkEntity = new LegalLinkEntity(1, 2, 3);
        legalLinkEntity.dropTable();
        legalLinkEntity.createTable();
    }
}
