package test.Initial;

import legal.Entity.DataLink.DataLinkEntity;
import legal.Entity.DataLink.DocumentLinkEntity;
import legal.Entity.DataLink.LegalLinkEntity;
import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Entity.Utility.DataZipEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Interface.LegalProcess.ILegalProcess;
import legal.Model.DataLink.DataLinkModel;
import legal.Model.DataLink.DocumentLinkModel;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Service.DatabaseConnection.DatabaseConnectionFactory;
import legal.Service.DatabaseConnection.MySQLConnection;
import legal.Service.DatabaseConnection.SQLSeverConnection;
import legal.Service.LegalProcess.DocFileProcessService;
import legal.Service.LegalProcess.LinkProcess;
import legal.Service.UtilityService.FileService;
import legal.Service.UtilityService.ZipReaderService;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Son on 4/13/2017.
 */
public class ProcessDoc {
    public static String baseFolderExtract = "G:\\FirstREST\\data";
    public static String baseFolderZip = "G:\\FirstREST\\temp8";
    public static void main(String[] args) throws SQLException {
        int idConnection = DatabaseConnectionFactory.addDatabaseConnection(new MySQLConnection("127.0.0.1", "test", "root", "root"));
        IDatabaseConnection connection = DatabaseConnectionFactory.getDatabaseConnection(idConnection);
        LegalInfoEntity.setDatabaseConnection(connection);
        DataLinkEntity.setDatabaseConnection(connection);
        LegalLinkEntity.setDatabaseConnection(connection);
        DocumentLinkEntity.setDatabaseConnection(connection);
//        temp();
        String baseLink = "http://vbpl.vn/TW/Pages/vbpq-toanvan.aspx?ItemID=";
        FileService.createFolder(baseFolderExtract);
        List<String> fileUrls = FileService.read(baseFolderZip);
        for(String fileUrl : fileUrls){
//            System.out.println(fileUrl);
            List<DataZipEntity> dataZipEntities = ZipReaderService.read(fileUrl);
            if(!dataZipEntities.isEmpty()){
                String[] xx =fileUrl.split(Pattern.quote(System.getProperty("file.separator")));
                String fileName = xx[xx.length-1];
//                System.out.println(fileName);
                String link = baseLink + fileName.split("\\.")[0];
//                System.out.println(link);
                DataLinkEntity dataLinkEntity1 = new DataLinkEntity(1,link , "");
                dataLinkEntity1.insert();
                List<DataLinkModel> dataLinkModels = (List<DataLinkModel>)(List<?>)dataLinkEntity1.select(link);
                if(!dataLinkModels.isEmpty()){
                    DataLinkModel dataLinkModel = dataLinkModels.get(0);
                    String folderUrl = baseFolderExtract + System.getProperty("file.separator") + fileName.split("\\.")[0];
                    FileService.createFolder(folderUrl);
                    for (DataZipEntity dataZipEntity : dataZipEntities){
                        String path = folderUrl + System.getProperty("file.separator") + dataZipEntity.name;
                        FileService.writeFile(path, dataZipEntity.data);
                        DocumentLinkEntity documentLinkEntity = new DocumentLinkEntity(1, dataLinkModel.id, dataZipEntity.name, path.replace("\\","\\\\"));
                        documentLinkEntity.insert();

//                        ILegalProcess processLegal = new DocFileProcessService(dataZipEntity.data);
//                        LegalInfoModel legalInfoModel = processLegal.getInfo();
//                        if(legalInfoModel != null){
//                            LegalInfoEntity legalInfoEntity = new LegalInfoEntity(legalInfoModel);
//                            legalInfoEntity.insert();
//                            List<LegalInfoModel> legalInfoModels = (List<LegalInfoModel>)(List<?>)legalInfoEntity.select(legalInfoModel.number);
//                            if(!legalInfoModels.isEmpty()){
//                                LegalInfoModel legalInfoModel1 = legalInfoModels.get(0);
//                                LegalLinkEntity legalLinkEntity = new LegalLinkEntity(1,legalInfoModel1.id,dataLinkModel.id);
//                                legalLinkEntity.insert();
//                            }
//                        }
                    }
                }
            }
        }
    }
    static void temp() throws SQLException {
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
