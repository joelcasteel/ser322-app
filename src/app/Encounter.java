package app;

import java.util.ArrayList;
import java.util.List;

public class Encounter {
    private String eName;
    private String username;
    private String difficulty;
    private String description;
    private String notes;


    private List<MonsterEntry> monsterEntries;
    private List<Monster> monsters;

    public Encounter() {
        monsterEntries = new ArrayList<>();
        monsters = new ArrayList<>();
    }

    public Monster getMonster(String mName, String mSource) {
        for(int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);

            if(monster.getName().equals(mName) && monster.getSource().equals(mSource)) {
                return monster;
            }
        }

        return null;

    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
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
        monsterEntries.add(monsterEntry);
    }

    public MonsterEntry getMonsterEntry(int i) {
        return monsterEntries.get(i);
    }

    public int getNumberEntries() {
        return monsterEntries.size();
    }

}
