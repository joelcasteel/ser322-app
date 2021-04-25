package app;

public class MonsterEntry {

    private String mName;
    private String mSource;

    private Monster monster;


    private String alias;
    private String notes;

    public MonsterEntry(String pMName, String pMSource, Monster pMonster, String pAlias, String pNotes) {
        mName = pMName;
        mSource = pMSource;
        monster = pMonster;
        alias = pAlias;
        notes = pNotes;
    }


    public String getMName() {
        return this.mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getMSource() {
        return this.mSource;
    }

    public void setMSource(String mSource) {
        this.mSource = mSource;
    }


    public Monster getMonster() {
        return this.monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
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
