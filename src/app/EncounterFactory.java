package app;

import java.sql.*;

public class EncounterFactory {
    public static Encounter createEncounter(String eName, String username) {
        Encounter encounter = new Encounter();
        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();

        setEncounterBase(conn, encounter, eName, username);
        addMonsterEntries(conn, encounter, eName, username);

        } catch(Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                conn.close();

            } catch(SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        return encounter;
    }

    private static void setEncounterBase(Connection conn, Encounter encounter, String eName, String username) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM ENCOUNTER WHERE ENCOUNTER.EName = ? AND ENCOUNTER.Username = ?");
            stmt.setString(1, eName);
            stmt.setString(2, username);

            rs = stmt.executeQuery();
            rs.next();

            encounter.setTitle(rs.getString("EName"), rs.getString("Username"));

            encounter.setDescription(rs.getString("Description"));
            encounter.setNotes(rs.getString("Notes"));
            encounter.setDifficulty(rs.getString("Difficulty"));

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

    private static void addMonsterEntries(Connection conn, Encounter encounter, String name, String username) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT MName, MSource, Alias, Notes FROM CONSISTS_OF WHERE EName = ? AND Username = ?");
            stmt.setString(1, name);
            stmt.setString(2, username);
            rs = stmt.executeQuery();

            while(rs.next()) {
                String mName = rs.getString("MName");
                String mSource = rs.getString("MSource");
                Monster monster = encounter.getMonster(mName, mSource);

                if(monster== null) {
                    monster = MonsterFactory.createMonster(rs.getString("MName"), rs.getString("MSource"));
                    encounter.addMonster(monster);
                }

                encounter.addMonsterEntry(new MonsterEntry(
                    mName, mSource, monster, rs.getString("Alias"), rs.getString("Notes")
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
