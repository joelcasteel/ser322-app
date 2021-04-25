
import ui.guiDev;

import java.sql.*;

import app.*;

public class Main {

    public static void main(String[] args) {
        String _url = args[0];
        String _user = args[1];
        String _pwd = args[2];
        String _driver = args[3];

        Connection conn = null;
        try {
            Class.forName(_driver);

            conn = DriverManager.getConnection(_url, _user, _pwd);

            Monster monster = MonsterFactory.createMonster(conn, "Goblin", "MM");
            monster.getCon();
        
        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                conn.close();

            } catch(SQLException sqlException) {
                sqlException.printStackTrace();

            }
        }
        guiDev gui = new guiDev();
        

    }

}