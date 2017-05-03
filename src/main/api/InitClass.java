import legal.Entity.DataLink.DataLinkEntity;
import legal.Entity.DataLink.DocumentLinkEntity;
import legal.Entity.DataLink.LegalLinkEntity;
import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import manager.Entity.DatabaseEntity;
import manager.Interface.IDatabaseService;
import manager.Service.DatabaseService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class InitClass implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        DatabaseEntity.setFileDir("database.txt");
        DatabaseEntity.loadData();
        IDatabaseService databaseService = new DatabaseService();
        IDatabaseConnection connection = databaseService.getDatabaseConnection();
        LegalInfoEntity.setDatabaseConnection(connection);
        DataLinkEntity.setDatabaseConnection(connection);
        DocumentLinkEntity.setDatabaseConnection(connection);
        LegalLinkEntity.setDatabaseConnection(connection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            DatabaseEntity.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}