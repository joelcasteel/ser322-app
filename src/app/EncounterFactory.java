package app;

import java.sql.*;

public class EncounterFactory {
    public static Encounter createEncounter(Connection conn, String eName, String username) {
        Encounter encounter = new Encounter();

        setEncounterBase(conn, encounter, eName, username);
        addMonsterEntries(conn, encounter, eName, username);

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
            encounter.setDifficulty("None");

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
                Monster monster = MonsterFactory.createMonster(conn, rs.getString("MName"), rs.getString("MSource"));

                encounter.addMonsterEntry(new MonsterEntry(
                    monster, rs.getString("Alias"), rs.getString("Notes")
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
