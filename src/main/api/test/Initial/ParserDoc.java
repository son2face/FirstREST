package test.Initial;

import com.sun.org.apache.xerces.internal.util.URI;
import legal.Entity.DataLink.DataLinkEntity;
import legal.Entity.DataLink.DocumentLinkEntity;
import legal.Entity.DataLink.LegalLinkEntity;
import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Service.DatabaseConnection.DatabaseConnectionFactory;
import legal.Service.DatabaseConnection.SQLSeverConnection;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

/**
 * Created by Son on 4/18/2017.
 */
public class ParserDoc {
    public static void main(String[] args) {
        int idConnection = DatabaseConnectionFactory.addDatabaseConnection(new SQLSeverConnection("42.112.212.163:1433", "test", "test", "123456a@"));
        IDatabaseConnection connection = DatabaseConnectionFactory.getDatabaseConnection(idConnection);
        LegalInfoEntity.setDatabaseConnection(connection);
        DataLinkEntity.setDatabaseConnection(connection);
        LegalLinkEntity.setDatabaseConnection(connection);
        DocumentLinkEntity.setDatabaseConnection(connection);
        temp();
        String baseLink = "http://vbpl.vn/TW/Pages/vbpq-toanvan.aspx?ItemID=";
//        for (int i = 98564; i < 130000; i++) {
//            System.out.println("i = " + Integer.toString(i));
//            ILegalProcess processLegal1 = new LinkProcess(baseLink + Integer.toString(i));
//            LegalInfoModel legalInfoModel = processLegal1.getInfo();
//            LegalInfoEntity legalInfoEntity1 = new LegalInfoEntity(legalInfoModel);
//            legalInfoEntity1.insert();
//            DataLinkEntity dataLinkEntity1 = new DataLinkEntity(1, baseLink + Integer.toString(i), processLegal1.getData());
//            dataLinkEntity1.insert();
//            List<LegalInfoModel> legalInfoModels = (List<LegalInfoModel>)(List<?>)legalInfoEntity1.select(legalInfoModel.number);
//            List<DataLinkModel> dataLinkModels = (List<DataLinkModel>)(List<?>)dataLinkEntity1.select(baseLink + Integer.toString(i));
//            LegalLinkEntity legalLinkEntity1 = new LegalLinkEntity(1, legalInfoModels.get(0).id , dataLinkModels.get(0).id);
//            legalLinkEntity1.insert();
////            System.out.println(legalInfoModels.get(0).toString());
////            System.out.println(dataLinkModels.get(0).toString());
////            try {
////                File t = new File("data/" + Integer.toString(i) + ".zip");
////                FileUtils.copyURLToFile(new URL(downloadallfile(Integer.toString(i))), t);
////                DocumentLinkEntity documentLinkEntity1 = new DocumentLinkEntity(1, dataLinkModels.get(0).id, "total","data/" + Integer.toString(i) + ".zip");
////                documentLinkEntity1.insert();
//////                System.out.println(documentLinkEntity1.documentLink.toString());
////            } catch (Exception x) {
////
////            }
//        }
    }

    static void temp(){
//        ILegalProcess processLegal = new LinkProcess("http://vbpl.vn/tw/Pages/vbpq-toanvan.aspx?ItemID=1");
//        LegalInfoModel res = processLegal.getInfo();
//        LegalInfoEntity legalInfoEntity = new LegalInfoEntity(res);
//        legalInfoEntity.dropTable();
//        legalInfoEntity.createTable();
//        DataLinkEntity dataLinkEntity = new DataLinkEntity(1, "2", "2");
//        dataLinkEntity.dropTable();
//        dataLinkEntity.createTable();
//        DocumentLinkEntity documentLinkEntity = new DocumentLinkEntity(1, 1, "2", "");
//        documentLinkEntity.dropTable();
//        documentLinkEntity.createTable();
//        LegalLinkEntity legalLinkEntity = new LegalLinkEntity(1, 2, 3);
//        legalLinkEntity.dropTable();
//        legalLinkEntity.createTable();
    }
}
