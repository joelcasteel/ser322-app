package app;

import java.sql.*;

public class MonsterFactory {
    
    /**
     * This will give us a way to dump monsters into an object.
     */
    public static Monster createMonster(String name, String source) {

        Monster monster = new Monster();
        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();

            setMonsterBase(conn, monster, name, source);
            addMonsterSenses(conn, monster, name, source);
            addMonsterCondImmunities(conn, monster, name, source);
            addMonsterLanguages(conn, monster, name, source);
            addMonsterActions(conn, monster, name, source);
            addMonsterLegendaryActions(conn, monster, name, source);
            addMonsterPassives(conn, monster, name, source);

        } catch(Exception exception) {

        } finally {
            try {
                conn.close();
            } catch(SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        


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

            monster.setStr(rs.getInt("STR"));
            monster.setDex(rs.getInt("DEX"));
            monster.setCon(rs.getInt("CON"));

            monster.setIntl(rs.getInt("INTEL"));
            monster.setWis(rs.getInt("WIS"));
            monster.setCha(rs.getInt("CHA"));

            monster.setHp(rs.getInt("HP"));
            monster.setAc(rs.getInt("ArmorClass"));
            monster.setCr(rs.getLong("ChallengeRating"));
            monster.setLegendCount(rs.getInt("LegendCount"));

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

    private static void addMonsterCondImmunities(Connection conn, Monster monster, String name, String source) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT Immunity FROM COND_IMMUNITIES WHERE MName = ? AND MSource = ?");
            stmt.setString(1, name);
            stmt.setString(2, source);
            rs = stmt.executeQuery();

            while(rs.next()) {
                monster.addCondImmunity(rs.getString("Immunity"));
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

    private static void addMonsterLanguages(Connection conn, Monster monster, String name, String source) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT LanguageName FROM LANGUAGES WHERE MName = ? AND MSource = ?");
            stmt.setString(1, name);
            stmt.setString(2, source);
            rs = stmt.executeQuery();

            while(rs.next()) {
                monster.addLanguage(rs.getString("LanguageName"));
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

    private static void addMonsterActions(Connection conn, Monster monster, String name, String source) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT ActionName, Description FROM ACTIONS WHERE MName = ? AND MSource = ?");
            stmt.setString(1, name);
            stmt.setString(2, source);
            rs = stmt.executeQuery();

            while(rs.next()) {
                monster.addAction(new Action(rs.getString("ActionName"), rs.getString("Description")));
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

    private static void addMonsterLegendaryActions(Connection conn, Monster monster, String name, String source) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT ActionName, Description, Cost FROM LEGENDARY_ACTIONS WHERE MName = ? AND MSource = ?");
            stmt.setString(1, name);
            stmt.setString(2, source);
            rs = stmt.executeQuery();

            while(rs.next()) {
                monster.addLegendaryAction(
                    new LegendaryAction(rs.getString("ActionName"),
                    rs.getString("Description"),
                    rs.getInt("Cost")
                ));
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

    private static void addMonsterPassives(Connection conn, Monster monster, String name, String source) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT PassiveName, Description FROM PASSIVES WHERE MName = ? AND MSource = ?");
            stmt.setString(1, name);
            stmt.setString(2, source);
            rs = stmt.executeQuery();

            while(rs.next()) {
                monster.addPassive(
                    new Passive(rs.getString("PassiveName"),
                    rs.getString("Description")
                ));
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
