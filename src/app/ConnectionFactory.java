package app;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Manages the connection parameters and creates new connection objects when needed
 */
public class ConnectionFactory {

    private static String url;
    private static String username;
    private static String password;

    /**
     * Sets the setup info
     * 
     * @param _url
     * @param _username
     * @param _password
     * @param _driver
     * @throws Exception
     */
    public static void setupInfo(String _url, String _username, String _password, String _driver) throws Exception{
        Class.forName(_driver);
        url = _url;
        username =_username;
        password = _password;
    }

    /**
     * Gets a new Connection object
     * 
     * 
     */
    public static Connection getConnection() throws Exception {
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        return conn;
    }

    
}
