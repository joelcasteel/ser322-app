package app;

import java.util.ArrayList;
import java.util.List;

public class Encounter {
    private String eName;
    private String username;
    private String difficulty;
    private String description;
    private String notes;

    private List<MonsterEntry> monsters;

    public Encounter() {
        monsters = new ArrayList<>();
    }

    public void setTitle(String pEName, String pUsername) {
        eName = pEName;
        username = pUsername;
    }

    public String getEName() {
        return this.eName;
    }


    public String getUsername() {
        return this.username;
    }


    public String getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void addMonsterEntry(MonsterEntry monsterEntry) {
        monsters.add(monsterEntry);
    }

    public MonsterEntry getMonsterEntry(int i) {
        return monsters.get(i);
    }

    public int getNumberEntries() {
        return monsters.size();
    }

}
