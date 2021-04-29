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
    private List<MonsterEntry> monstersToAdd;
    private List<MonsterEntry> monstersToRemove;


    private List<Monster> monsters;

    public Encounter() {
        monsterEntries = new ArrayList<>();
        monsters = new ArrayList<>();

        monstersToAdd = new ArrayList<>();
        monstersToRemove = new ArrayList<>();
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

    public void addMonsterEnty(MonsterEntry monster) {
        monsterEntries.add(monster);
        monsters.add(monster.getMonster());

        if(monstersToRemove.contains(monster)) {
            monstersToRemove.remove(monster);

        } else {
            monstersToAdd.add(monster);

        }
    }

    public List<MonsterEntry> getAddList() {
        return monstersToAdd;
    }

    public void removeMonsterEntry(MonsterEntry monster) {
        monsterEntries.remove(monster);

        if(monstersToAdd.contains(monster)) {
            monstersToAdd.remove(monster);

        } else {
            monstersToRemove.add(monster);

        }
    }

    public List<MonsterEntry> getRemoveList() {
        return monstersToRemove;
    }

    public List<MonsterEntry> getMonsterEntryList() {
        return monsterEntries;
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

    public void addMonsterEntryFromDB(MonsterEntry monsterEntry) {
        monsterEntries.add(monsterEntry);
    }

    public MonsterEntry getMonsterEntry(int i) {
        return monsterEntries.get(i);
    }

    public int getNumberEntries() {
        return monsterEntries.size();
    }

}
