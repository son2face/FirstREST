package test.Initial;

/**
 * Created by Son on 4/13/2017.
 */
public class ProcessDoc {
//    public static String baseFolderExtract = "G://FirstREST/data";
//    public static String baseFolderZip = "G://FirstREST/splitzipdata/temp";
//
//    public static void main(String[] args) throws SQLException, IOException {
//        DatabaseModel databaseModel = new DatabaseModel(0, 0, "112.137.129.95", "Legal", "nguyenson", "root");
//        IDatabaseConnection connection = new MySQLConnection(databaseModel);
//        LegalInfoEntitys.setDatabaseConnection(connection);
//        DataLinkEntitys.setDatabaseConnection(connection);
//        LegalLinkEntitys.setDatabaseConnection(connection);
//        DocumentLinkEntitys.setDatabaseConnection(connection);
//        temp();
//        String baseLink = "http://vbpl.vn/TW/Pages/vbpq-toanvan.aspx?ItemID=";
//        FileService.createFolder(baseFolderExtract);
//        List<String> fileUrls = FileService.read(baseFolderZip);
//        for (String fileUrl : fileUrls) {
//            System.out.println(fileUrl);
//        }
//        Scanner x = new Scanner(System.in);
//        for (String fileUrl : fileUrls) {
////            x.next();
//            List<DataZipEntity> dataZipEntities = ZipReaderService.read(fileUrl);
//            if (!dataZipEntities.isEmpty()) {
//                String fileName = fileUrl.split(Pattern.quote(System.getProperty("file.separator")))[1];
//                String link = baseLink + fileName.split("\\.")[0];
//                DataLinkEntitys dataLinkEntity1 = new DataLinkEntitys(1, link, "");
//                dataLinkEntity1.insert();
//                List<DataLinkModel> dataLinkModels = (List<DataLinkModel>) (List<?>) dataLinkEntity1.select(link);
//                if (!dataLinkModels.isEmpty()) {
//                    DataLinkModel dataLinkModel = dataLinkModels.get(0);
//                    String folderUrl = baseFolderExtract + System.getProperty("file.separator") + fileName.split("\\.")[0];
//                    FileService.createFolder(folderUrl);
//                    for (DataZipEntity dataZipEntity : dataZipEntities) {
//                        String path = folderUrl + System.getProperty("file.separator") + dataZipEntity.name;
//                        FileService.writeFile(path, dataZipEntity.data);
//                        DocumentLinkEntitys documentLinkEntity = new DocumentLinkEntitys(1, dataLinkModel.id, dataZipEntity.name, path.replace("\\", "\\\\"));
//                        documentLinkEntity.insert();
//                        ILegalProcess processLegal = new DocFileProcessService(dataZipEntity.data);
//                        LegalRelationshipModel legalInfoModel = processLegal.getInfo();
//                        if (legalInfoModel != null) {
//                            LegalInfoEntitys legalInfoEntity = new LegalInfoEntitys(legalInfoModel);
//                            legalInfoEntity.insert();
//                            List<LegalRelationshipModel> legalInfoModels = (List<LegalRelationshipModel>) (List<?>) legalInfoEntity.select(legalInfoModel.number);
//                            if (!legalInfoModels.isEmpty()) {
//                                LegalRelationshipModel legalInfoModel1 = legalInfoModels.get(0);
//                                LegalLinkEntitys legalLinkEntity = new LegalLinkEntitys(1, legalInfoModel1.id, dataLinkModel.id);
//                                legalLinkEntity.insert();
//                            }
//                        }
//                    }
//                }
//            }
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

}
