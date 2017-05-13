import manager.Entity.DatabaseEntity;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class InitClass implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        DatabaseEntity.setFileDir("database.txt");
        DatabaseEntity.loadData();
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