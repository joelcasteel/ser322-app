package app;

import java.sql.*;
import java.util.List;

/**
 * EcnounterFactory
 *  This class manages and creates the Encounters.
 * 
 * 
 */
public class EncounterFactory {

    /**
     * Gets an encounter based of the encounter name and username.
     * If an encounter is not found in the database, null is returned
     * 
     * @param eName
     * @param username
     * @return
     */
    public static Encounter getEncounter(String eName, String username) {
        Encounter encounter = null;
        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();
            if (checkExists(conn, eName, username)) {
                encounter = new Encounter();
                setEncounterBase(conn, encounter, eName, username);
                addMonsterEntries(conn, encounter, eName, username);
            }

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                conn.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        return encounter;
    }

    /**
     * Sets the base attributes for the encounter from the DB
     * 
     * @param conn
     * @param encounter
     * @param eName
     * @param username
     */
    private static void setEncounterBase(Connection conn, Encounter encounter, String eName, String username) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn
                    .prepareStatement("SELECT * FROM ENCOUNTER WHERE ENCOUNTER.EName = ? AND ENCOUNTER.Username = ?");
            stmt.setString(1, eName);
            stmt.setString(2, username);

            rs = stmt.executeQuery();
            rs.next();

            encounter.setTitle(rs.getString("EName"), rs.getString("Username"));

            encounter.setDescription(rs.getString("Description"));
            encounter.setNotes(rs.getString("Notes"));
            encounter.setDifficulty(rs.getString("Difficulty"));

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                rs.close();
                stmt.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();

            }

        }
    }

    /**
     * Adds the moster entries associated with an encounter from the database
     * 
     * @param conn
     * @param encounter
     * @param name
     * @param username
     */
    private static void addMonsterEntries(Connection conn, Encounter encounter, String name, String username) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "SELECT MName, MSource, Alias, Notes FROM CONSISTS_OF WHERE EName = ? AND Username = ?");
            stmt.setString(1, name);
            stmt.setString(2, username);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String mName = rs.getString("MName");
                String mSource = rs.getString("MSource");
                Monster monster = encounter.getMonster(mName, mSource);

                if (monster == null) {
                    monster = MonsterFactory.createMonster(rs.getString("MName"), rs.getString("MSource"));
                    encounter.addMonster(monster);
                }

                encounter.addMonsterEntryFromDB(
                        new MonsterEntry(mName, mSource, monster, rs.getString("Alias"), rs.getString("Notes")));

                Encounter e = encounter;
                e.getAddList();
            }

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                rs.close();
                stmt.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();

            }

        }

    }

    /**
     * Creates a brand new encounter
     * 
     * @param name
     * @param username
     * @param description
     * @param notes
     * @return
     */
    public static Encounter createEncounter(String name, String username, String description, String notes) {
        Encounter encounter = new Encounter();
        encounter.setTitle(name, username);
        encounter.setDescription(description);
        encounter.setNotes(notes);
        encounter.setDifficulty("None");

        return encounter;
    }

    /**
     * Saves an encounter to the database
     *  If the encouter is already present -> UPDATE
     *  If the encounter is not present -> INSERT
     * 
     *  If the user associated with the encounter is not present -> INSERT
     * 
     * @param encounter
     * @return
     */
    public static boolean saveEncounter(Encounter encounter) {
        Connection conn = null;
        boolean success = false;

        try {
            conn = ConnectionFactory.getConnection();

            if (!checkUserExists(conn, encounter.getUsername())) {
                insertUser(conn, encounter.getUsername());
            }

            if (checkExists(conn, encounter.getEName(), encounter.getUsername())) {

                success = updateEncounter(conn, encounter.getEName(), encounter.getDescription(), encounter.getNotes(),
                        encounter.getDifficulty(), encounter.getUsername());
                if (encounter.getAddList().size() > 0) {
                    addMonsterEntries(conn, encounter);
                }

                if (encounter.getRemoveList().size() > 0) {
                    removerMonsterEntries(conn, encounter);
                }

            } else {
                success = insertEncounter(conn, encounter.getEName(), encounter.getDescription(), encounter.getNotes(),
                        encounter.getDifficulty(), encounter.getUsername());
                if (encounter.getAddList().size() > 0) {
                    addMonsterEntries(conn, encounter);
                }

            }

            if (success) {
                conn.commit();

            } else {
                conn.rollback();

            }
        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                conn.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        return success;
    }

    /**
     * Checks whether an encounter exists in the database
     * 
     * @param conn
     * @param eName
     * @param username
     * @return
     */
    private static boolean checkExists(Connection conn, String eName, String username) {
        ResultSet rs = null;
        PreparedStatement stmt = null;

        boolean exists = false;

        try {
            stmt = conn.prepareStatement("SELECT EName, Username FROM ENCOUNTER WHERE EName = ? AND Username = ?");
            stmt.setString(1, eName);
            stmt.setString(2, username);
            rs = stmt.executeQuery();

            exists = rs.next();

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                rs.close();
                stmt.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();

            }

        }
        return exists;
    }

    /**
     * Checks whether a user exists in the database
     * 
     * @param conn
     * @param username
     * @return
     */
    private static boolean checkUserExists(Connection conn, String username) {
        ResultSet rs = null;
        PreparedStatement stmt = null;

        boolean exists = false;

        try {
            stmt = conn.prepareStatement("SELECT Username FROM USER WHERE Username = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            exists = rs.next();

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                rs.close();
                stmt.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();

            }

        }
        return exists;
    }

    /**
     * Inserts a user into the database
     * 
     * @param conn
     * @param username
     * @return
     */
    private static boolean insertUser(Connection conn, String username) {
        PreparedStatement stmt = null;

        boolean saved = false;

        try {
            stmt = conn.prepareStatement("INSERT into USER(Username) VALUES (?);");
            stmt.setString(1, username);
            int count = stmt.executeUpdate();

            saved = (count > 0);

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                stmt.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();

            }

        }
        return saved;
    }

    /**
     * Inserts an encounter into the database
     * 
     * @param conn
     * @param name
     * @param description
     * @param notes
     * @param difficulty
     * @param username
     * @return
     */
    private static boolean insertEncounter(Connection conn, String name, String description, String notes, String difficulty, String username ) {
        PreparedStatement stmt = null;

        boolean saved = false;

        try {
            stmt = conn.prepareStatement(
                    "INSERT into ENCOUNTER(EName, Description, Notes, Difficulty, Username) VALUES (?, ?, ?, ?, ?);");
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, notes);
            stmt.setString(4, difficulty);
            stmt.setString(5, username);
            int count = stmt.executeUpdate();

            saved = (count > 0);

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                stmt.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();

            }

        }
        return saved;
    }

    /**
     * Updates an existing encounter in the database
     * 
     * @param conn
     * @param name
     * @param description
     * @param notes
     * @param difficulty
     * @param username
     * @return
     */
    private static boolean updateEncounter(Connection conn, String name, String description, String notes, String difficulty, String username) {
        PreparedStatement stmt = null;

        boolean saved = false;

        try {
            stmt = conn.prepareStatement(
                    "UPDATE ENCOUNTER SET Description=?, Notes=?, Difficulty=? WHERE EName=? AND Username=?");
            stmt.setString(1, description);
            stmt.setString(2, notes);
            stmt.setString(3, difficulty);

            stmt.setString(4, name);
            stmt.setString(5, username);
            int count = stmt.executeUpdate();

            saved = (count > 0);

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                stmt.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();

            }

        }
        return saved;
    }

    /**
     * Adds any new monster entries to the database
     * 
     * @param conn
     * @param encounter
     * @return
     */
    private static boolean addMonsterEntries(Connection conn, Encounter encounter) {
        PreparedStatement stmt = null;

        boolean saved = false;

        try {
            stmt = conn.prepareStatement(
                    "INSERT INTO CONSISTS_OF(MName, MSource, EName, Alias, Notes, Username) VALUES (?, ?, ?, ?, ?, ?)");

            for (MonsterEntry monster : encounter.getAddList()) {
                stmt.setString(1, monster.getMName());
                stmt.setString(2, monster.getMSource());
                stmt.setString(3, encounter.getEName());
                stmt.setString(4, monster.getAlias());
                stmt.setString(5, monster.getNotes());
                stmt.setString(6, encounter.getUsername());

                stmt.addBatch();
            }

            int count = stmt.executeUpdate();

            saved = (count > 0);

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                stmt.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();

            }

        }
        return saved;
    }

    /**
     * Removes any deleted monster entries from the database
     * 
     * @param conn
     * @param encounter
     * @return
     */
    private static boolean removerMonsterEntries(Connection conn, Encounter encounter) {
        PreparedStatement stmt = null;

        boolean saved = false;

        try {
            stmt = conn.prepareStatement(
                    "DELETE FROM CONSISTS_OF WHERE MName = ? AND MSource = ? AND EName = ? AND Username = ? AND Alias = ?");

            for (MonsterEntry monster : encounter.getRemoveList()) {
                stmt.setString(1, monster.getMName());
                stmt.setString(2, monster.getMSource());
                stmt.setString(3, encounter.getEName());
                stmt.setString(4, encounter.getUsername());
                stmt.setString(5, monster.getAlias());

                stmt.addBatch();
            }

            int count = stmt.executeUpdate();

            saved = (count > 0);

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            try {
                stmt.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();

            }

        }
        return saved;
    }

}
