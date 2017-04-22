package legal.Interface.DatabaseCommunication;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Son on 4/10/2017.
 */
public interface IDatabaseEntity {
    void insert() throws SQLException;
    void update() throws SQLException;
    void delete() throws SQLException;
    List<Object> select() throws SQLException;
    void insert(List<Object> data) throws SQLException;
    void createTable() throws SQLException;
    void truncateTable() throws SQLException;
    void dropTable() throws SQLException;

    /**
     *
     * @param id
     * @return
     */
    List<Object> select(int id) throws SQLException;
}
