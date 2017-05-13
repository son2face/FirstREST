package test.Initial;

/**
 * Created by Admin on 4/13/2017.
 */
public class ClawerLinkVBPL {
//    public static void main(String[] args) throws SQLException {
//        DatabaseModel databaseModel = new DatabaseModel(0, 0, "112.137.129.95", "Legal", "nguyenson", "root");
//        IDatabaseConnection connection = new MySQLConnection(databaseModel);
//        LegalInfoEntitys.setDatabaseConnection(connection);
//        DataLinkEntitys.setDatabaseConnection(connection);
//        LegalLinkEntitys.setDatabaseConnection(connection);
//        DocumentLinkEntitys.setDatabaseConnection(connection);
//        temp();
//        String baseLink = "http://vbpl.vn/TW/Pages/vbpq-toanvan.aspx?ItemID=";
//        for (int i = 98564; i < 130000; i++) {
//            System.out.println("i = " + Integer.toString(i));
//            ILegalProcess processLegal1 = new LinkProcess(baseLink + Integer.toString(i));
//            LegalRelationshipModel legalInfoModel = processLegal1.getInfo();
//            LegalInfoEntitys legalInfoEntity1 = new LegalInfoEntitys(legalInfoModel);
//            legalInfoEntity1.insert();
//            DataLinkEntitys dataLinkEntity1 = new DataLinkEntitys(1, baseLink + Integer.toString(i), processLegal1.getData());
//            dataLinkEntity1.insert();
//            List<LegalRelationshipModel> legalInfoModels = (List<LegalRelationshipModel>) (List<?>) legalInfoEntity1.select(legalInfoModel.number);
//            List<DataLinkModel> dataLinkModels = (List<DataLinkModel>) (List<?>) dataLinkEntity1.select(baseLink + Integer.toString(i));
//            LegalLinkEntitys legalLinkEntity1 = new LegalLinkEntitys(1, legalInfoModels.get(0).id, dataLinkModels.get(0).id);
//            legalLinkEntity1.insert();
////            System.out.println(legalInfoModels.get(0).toString());
////            System.out.println(dataLinkModels.get(0).toString());
////            try {
////                File t = new File("data/" + Integer.toString(i) + ".zip");
////                FileUtils.copyURLToFile(new URL(downloadallfile(Integer.toString(i))), t);
////                DocumentLinkEntitys documentLinkEntity1 = new DocumentLinkEntitys(1, dataLinkModels.get(0).id, "total","data/" + Integer.toString(i) + ".zip");
////                documentLinkEntity1.insert();
//////                System.out.println(documentLinkEntity1.documentLink.toString());
////            } catch (Exception x) {
////
////            }
//        }
//    }
//
//    static void temp() throws SQLException {
//        ILegalProcess processLegal = new LinkProcess("http://vbpl.vn/tw/Pages/vbpq-toanvan.aspx?ItemID=1");
//        LegalRelationshipModel res = processLegal.getInfo();
//        LegalInfoEntitys legalInfoEntity = new LegalInfoEntitys(res);
//        legalInfoEntity.drop();
//        legalInfoEntity.create();
//        DataLinkEntitys dataLinkEntity = new DataLinkEntitys(1, "2", "2");
//        dataLinkEntity.drop();
//        dataLinkEntity.create();
//        DocumentLinkEntitys documentLinkEntity = new DocumentLinkEntitys(1, 1, "2", "");
//        documentLinkEntity.drop();
//        documentLinkEntity.create();
//        LegalLinkEntitys legalLinkEntity = new LegalLinkEntitys(1, 2, 3);
//        legalLinkEntity.drop();
//        legalLinkEntity.create();
//    }
//
//    static String downloadallfile(String itemid) {
//        String url = "http://vbpl.vn/VBQPPL_UserControls/Publishing_22/pActiontkeFile.aspx?do=getallfile&ItemID=" + itemid;
//        return url;
//    }


}