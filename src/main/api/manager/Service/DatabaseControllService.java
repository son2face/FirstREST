package manager.Service;


import legal.Entity.DataLink.DatalinkEntity;
import legal.Entity.DataLink.DocumentlinkEntity;
import legal.Entity.DataLink.LegallinkEntity;
import legal.Entity.LegalInfo.LegalinfoEntity;
import manager.Entity.DatabaseEntity;
import manager.Interface.IDatabaseControllService;
import manager.Model.DatabaseModel;
import org.hibernate.cfg.Configuration;

/**
 * Created by Son on 5/12/2017.
 */

public class DatabaseControllService implements IDatabaseControllService {
    public boolean setActive(int id) {
        if (id >= 0 && id < DatabaseEntity.getDatabaseModels().size()) {
            DatabaseEntity.setActive(id);
            return true;
        }
        return false;
    }

    @Override
    public Configuration createConfiguration(DatabaseModel databaseModel) {
        Configuration cfg;
        switch (databaseModel.typeDB) {
            case 0:
                cfg = new Configuration()
                        .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                        .setProperty("hibernate.connection.url", "jdbc:mysql://" + databaseModel.url + "/" + databaseModel.databaseName)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .setProperty("hibernate.connection.username", databaseModel.userName)
                        .setProperty("hibernate.connection.password", databaseModel.passWord)
                        .addAnnotatedClass(DatalinkEntity.class)
                        .addAnnotatedClass(DocumentlinkEntity.class)
                        .addAnnotatedClass(LegalinfoEntity.class)
                        .addAnnotatedClass(LegallinkEntity.class);
                break;
            default:
                cfg = new Configuration()
                        .setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
                        .setProperty("hibernate.connection.url", "jdbc:sqlserver:://" + databaseModel.url + "/" + databaseModel.databaseName)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect")
                        .setProperty("hibernate.connection.username", databaseModel.userName)
                        .setProperty("hibernate.connection.password", databaseModel.passWord)
                        .addAnnotatedClass(DatalinkEntity.class)
                        .addAnnotatedClass(DocumentlinkEntity.class)
                        .addAnnotatedClass(LegalinfoEntity.class)
                        .addAnnotatedClass(LegallinkEntity.class);
                break;
        }
        return cfg;
    }
}
