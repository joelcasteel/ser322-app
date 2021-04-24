package app;

import java.sql.*;

public class MonsterFactory {
    
    /**
     * This will give us a way to dump monsters into an object.
     */
    public static Monster createMonster(Connection conn, String name, String source) throws Exception{

        Monster monster = new Monster();

        setMonsterBase(conn, monster, name, source);
        addMonsterSenses(conn, monster, name, source);


        return monster;
    }

    private static void setMonsterBase(Connection conn, Monster monster, String name, String source) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM MONSTER WHERE MONSTER.Name = ? AND MONSTER.Source = ?");
            stmt.setString(1, name);
            stmt.setString(2, source);

            rs = stmt.executeQuery();
            rs.next();

            monster.setTitle(rs.getString("Name"), rs.getString("Source"));

            monster.setStat("str", rs.getInt("STR"));
            monster.setStat("dex", rs.getInt("DEX"));
            monster.setStat("con", rs.getInt("CON"));
            monster.setStat("intel", rs.getInt("INTEL"));
            monster.setStat("wis", rs.getInt("WIS"));
            monster.setStat("cha", rs.getInt("CHA"));

            monster.setHp(rs.getInt("HP"));
            monster.setAc(rs.getInt("ArmorClass"));
            monster.setCr(rs.getLong("ChallengeRating"));

            monster.setType(rs.getString("Type"));
            monster.setSize(rs.getString("Size"));
            monster.setSpeed(rs.getString("Speed"));
            monster.setAlignment(rs.getString("Alignment"));

        } catch(Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                rs.close();
                stmt.close();

            } catch(SQLException sqlException) {
                sqlException.printStackTrace();

            }
            
        }
    }

    private static void addMonsterSenses(Connection conn, Monster monster, String name, String source) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT Sense FROM SENSES WHERE MName = ? AND MSource = ?");
            stmt.setString(1, name);
            stmt.setString(2, source);
            rs = stmt.executeQuery();

            while(rs.next()) {
                monster.addSense(rs.getString("Sense"));
        }

        } catch(Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                rs.close();
                stmt.close();

            } catch(SQLException sqlException) {
                sqlException.printStackTrace();

            }
            
        }

    }
}
