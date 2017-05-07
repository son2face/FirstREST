//package test;
//
//import legal.Entity.DataLink.DataLinkEntity;
//import legal.Entity.LegalInfo.LegalInfoEntity;
//import legal.Interface.DatabaseConnection.IDatabaseConnection;
//import legal.Interface.LegalProcess.ILegalProcess;
//import legal.Model.DataLink.DataLinkModel;
//import legal.Model.LegalInfo.LegalInfoModel;
//import legal.Service.DatabaseConnection.DatabaseConnectionFactory;
//import legal.Service.DatabaseConnection.SQLSeverConnection;
//import legal.Service.LegalProcess.LinkProcess;
//
//import java.util.List;
//
///**
// * Created by Son on 4/10/2017.
// */
//public class TestDB {
//    public static void main(String[] args) {
//        int idConnection = DatabaseConnectionFactory.addDatabaseConnection(new SQLSeverConnection("42.112.212.163:1433", "test", "test","123456a@"));
//        IDatabaseConnection connection = DatabaseConnectionFactory.getDatabaseConnection(idConnection);
//        LegalInfoEntity.setDatabaseConnection(connection);
//        ILegalProcess processLegal = new LinkProcess("http://vbpl.vn/bacgiang/Pages/vbpq-toanvan.aspx?ItemID=112233");
//        LegalInfoModel res = processLegal.getInfo();
//        LegalInfoEntity test = new LegalInfoEntity(res);
//        test.createTable();
//        test.insert();
//        List<LegalInfoModel> t = (List<LegalInfoModel>)(List<?>)test.select(res.number);
//        System.out.println(t.get(0).toString());
//        System.out.println(processLegal.getData());
//        test.dropTable();
////        testLegalInfoEntity();
//    }
//
//    static  void testDataLinkEntity(){
//        int idConnection = DatabaseConnectionFactory.addDatabaseConnection(new SQLSeverConnection("42.112.212.163:1433", "test", "test","123456a@"));
//        IDatabaseConnection connection = DatabaseConnectionFactory.getDatabaseConnection(idConnection);
//        DataLinkEntity.setDatabaseConnection(connection);
//        DataLinkEntity test = new DataLinkEntity(1,"2", "2");
//        test.createTable();
//        test.insert();
//        List<DataLinkModel> t = (List<DataLinkModel>)(List<?>)test.select("2");
//        for(DataLinkModel x : t){
//            System.out.println(x.link);
//        }
//        test.dropTable();
//    }
//
//    static void testLegalInfoEntity(){
//        int idConnection = DatabaseConnectionFactory.addDatabaseConnection(new SQLSeverConnection("42.112.212.163:1433", "test", "test","123456a@"));
//        IDatabaseConnection connection = DatabaseConnectionFactory.getDatabaseConnection(idConnection);
//        LegalInfoEntity.setDatabaseConnection(connection);
//        LinkProcess link = new LinkProcess("http://vbpl.vn/botuphap/Pages/vbpq-toanvan.aspx?ItemID=118077&dvid=41");
//        LegalInfoEntity test = new LegalInfoEntity(link.getInfo());
//        test.insert();
//        List<LegalInfoModel> t = (List<LegalInfoModel>)(List<?>)test.select(1);
//        for(LegalInfoModel x : t){
//            System.out.println(x.toString());
//        }
//    }
//}
