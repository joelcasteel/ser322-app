package app;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Monster {
    //Title Attrribute (PK)
    private String name;
    private String source;

    //Stats
    private int str;
    private int dex;
    private int con;
    private int intl;
    private int wis;
    private int cha;

    private Map<String, Integer> stats;


    //values
    private int hp;
    private int ac;
    private double cr;

    private int legendCount;

    //Descriptors
    private String type;
    private String size;
    private String speed;
    private String alignment;

    //Mutli-Valued
    private List<String> senses;
    private List<String> condImmunities;
    private List<String> languages;

    public Monster() {
        stats = new HashMap<>();
        
        senses = new ArrayList<>();
        condImmunities = new ArrayList<>();
        languages = new ArrayList<>();
    }

    public void setTitle(String pName, String pSource) {
        name = pName;
        source = pSource;
    }

    /**
     * Yeah, we could have done this with a hashmap, but i don't want to get into that
     * 
     * @param stat
     * @param value
     */
    public void setStat(String stat, int value) {
        stats.put(stat, value);
    }

    public int getStat(String stat) {
        return stats.get(stat);
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getStr() {
        return this.str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return this.dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getCon() {
        return this.con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getIntl() {
        return this.intl;
    }

    public void setIntl(int intl) {
        this.intl = intl;
    }

    public int getWis() {
        return this.wis;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getCha() {
        return this.cha;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAc() {
        return this.ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public double getCr() {
        return this.cr;
    }

    public void setCr(double cr) {
        this.cr = cr;
    }

    public int getLegendCount() {
        return this.legendCount;
    }

    public void setLegendCount(int leg) {
        this.legendCount = leg;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSpeed() {
        return this.speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getAlignment() {
        return this.alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getSense(int i) {
        return this.senses.get(i);
    }

    public void addSense(String sense) {
        this.senses.add(sense);
    }

    public String getCondImmunity(int i) {
        return this.condImmunities.get(i);
    }

    public void addCondImmunity(String cond) {
        this.condImmunities.add(cond);
    }

    public String getLanguage(int i) {
        return this.languages.get(i);
    }

    public void addLanguage(String lang) {
        this.languages.add(lang);
    }
    

    
}
