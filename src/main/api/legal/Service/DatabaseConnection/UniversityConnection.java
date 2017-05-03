package legal.Service.DatabaseConnection;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Son on 4/12/2017.
 */
public class UniversityConnection extends MySQLConnection {
    public UniversityConnection(String url, String databaseName, String userName, String passWord) {
        int lport=3306;
        String rhost="112.137.129.120";
        String host="112.137.129.120";
        int rport=3306;
        String user="sonnv_60";
        this.databaseModel.userName = user;
        String password="abcd1234";
        this.databaseModel.passWord = password;
        String dbuserName = "root";
        this.databaseModel.url = "localhost:" + lport;
        this.databaseModel.databaseName = "test";
        String dbpassword = "root";
        String urll = "jdbc:mysql://127.0.0.1:"+lport+ "/test";
        String driverName="com.mysql.cj.jdbc.Driver";
        Connection conn = null;
        Session session= null;
        try{
            //Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");
            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
            System.out.println("Port Forwarded");
            //mysql database connectivity
            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection (urll, dbuserName, dbpassword);
            System.out.println ("Database connection established");
            System.out.println("DONE");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(conn != null && !conn.isClosed()){
                    System.out.println("Closing Database Connection");
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(session !=null && session.isConnected()){
                System.out.println("Closing SSH Connection");
                session.disconnect();
            }
        }

    }
}
