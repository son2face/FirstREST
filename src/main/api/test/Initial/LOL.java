package test.Initial;

import legal.Entity.DataLink.DataLinkEntity;
import legal.Entity.DataLink.DocumentLinkEntity;
import legal.Entity.DataLink.LegalLinkEntity;
import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Interface.LegalProcess.ILegalProcess;
import legal.Model.DataLink.DataLinkModel;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Service.DatabaseConnection.DatabaseConnectionFactory;
import legal.Service.DatabaseConnection.MySQLConnection;
import legal.Service.LegalProcess.DocFileProcessService;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Son on 4/18/2017.
 */
public class LOL {
    public static String baseFolderExtract = "G:\\FirstREST\\data";
    public static String baseFolderZip = "G:\\FirstREST\\temp5";
    public static void main(String[] args) {
        int idConnection = DatabaseConnectionFactory.addDatabaseConnection(new MySQLConnection("127.0.0.1", "test", "root", "root"));
        IDatabaseConnection connection = DatabaseConnectionFactory.getDatabaseConnection(idConnection);
        LegalInfoEntity.setDatabaseConnection(connection);
        DataLinkEntity.setDatabaseConnection(connection);
        LegalLinkEntity.setDatabaseConnection(connection);
        DocumentLinkEntity.setDatabaseConnection(connection);
        final File folder = new File(baseFolderExtract);
        listFilesForFolder(folder);

    }

    public static void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String fileName = fileEntry.getName();
                String[] t = fileName.split("\\.");
                String fileExtension = t[t.length-1];
                if("doc".equals(fileExtension.toLowerCase())){
                    ILegalProcess processLegal = null;
                    try {
                        processLegal = new DocFileProcessService(new FileInputStream(fileEntry));
                        LegalInfoModel legalInfoModel = processLegal.getInfo();
                        if(legalInfoModel != null){
                            LegalInfoEntity legalInfoEntity = new LegalInfoEntity(legalInfoModel);
                            legalInfoEntity.insert();
                            List<LegalInfoModel> legalInfoModels = (List<LegalInfoModel>)(List<?>)legalInfoEntity.select(legalInfoModel.number);
                            if(!legalInfoModels.isEmpty()){
                                String[] arr = folder.getPath().split(Pattern.quote(System.getProperty("file.separator")));
                                String path = arr[arr.length-2] + System.getProperty("file.separator") + arr[arr.length-1];

                                LegalInfoModel legalInfoModel1 = legalInfoModels.get(0);
                                DataLinkEntity dataLinkEntity = new DataLinkEntity(1,path,"");
                                List<DataLinkModel> dataLinkModels = (List<DataLinkModel>)(List<?>)dataLinkEntity.select(path);
                                if(dataLinkModels.isEmpty()) {
                                    dataLinkEntity.insert();
                                    dataLinkModels = (List<DataLinkModel>) (List<?>) dataLinkEntity.select(path);
                                }
                                DataLinkModel dataLinkModel = dataLinkModels.get(0);
                                LegalLinkEntity legalLinkEntity = new LegalLinkEntity(1,legalInfoModel1.id,dataLinkModel.id);
                                legalLinkEntity.insert();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(fileEntry);
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
