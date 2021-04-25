package app;

public class MonsterEntry {
    private Monster monster;

    private String alias;
    private String notes;

    public MonsterEntry(Monster pMonster, String pAlias, String pNotes) {
        monster = pMonster;
        alias = pAlias;
        notes = pNotes;
    }


    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster pMonster) {
        monster = pMonster;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
