package app;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static String url;
    private static String username;
    private static String password;

    public static void setupInfo(String _url, String _username, String _password, String _driver) throws Exception{
        Class.forName(_driver);
        url = _url;
        username =_username;
        password = _password;
    }

    public static Connection getConnection() throws Exception {
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        return conn;
    }

    
}
