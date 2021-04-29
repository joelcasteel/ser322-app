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

    private List<String> damageImmunities;
    private List<String> damageVulnerabilities;

    private List<Action> actions;
    private List<LegendaryAction> legendaryActions;
    private List<Passive> passives;

    private List<Skill> skills;
    private List<Skill> saves;

    public Monster() {
        stats = new HashMap<>();
        
        senses = new ArrayList<>();
        condImmunities = new ArrayList<>();
        damageImmunities = new ArrayList<>();
        damageVulnerabilities = new ArrayList<>();
        
        languages = new ArrayList<>();

        actions = new ArrayList<>();
        legendaryActions = new ArrayList<>();
        passives = new ArrayList<>();

        skills = new ArrayList<>();
        saves = new ArrayList<>();
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

    public List<String> getSenseList() {
        return this.senses;
    }

    public void addSense(String sense) {
        this.senses.add(sense);
    }

    public List<String> getCondImmunity() {
        return this.condImmunities;
    }

    public void addCondImmunity(String cond) {
        this.condImmunities.add(cond);
    }

    public List<String> getLanguage() {
        return this.languages;
    }

    public void addLanguage(String lang) {
        this.languages.add(lang);
    }
    
    public void addAction(Action action) {
        actions.add(action);
    }

    public List<Action> getAction() {
        return actions;
    }

    public void addLegendaryAction(LegendaryAction legendaryAction) {
        legendaryActions.add(legendaryAction);
    }

    public List<LegendaryAction> gLegendaryAction() {
        return legendaryActions;
    }

    public void addPassive(Passive passive) {
        passives.add(passive);
    }

    public List<Passive> getPassive() {
        return passives;
    }

    public void addDamageVulnerability(String damage) {
        damageVulnerabilities.add(damage);
    }

    public List<String> getDamageVulnerability() {
        return damageVulnerabilities;
    }

    public void addDamageImmunity(String damage) {
        damageImmunities.add(damage);
    }

    public List<String> getDamageImmunity() {
        return damageImmunities;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public List<Skill> getSkill() {
        return skills;
    }

    public void addSave(Skill save) {
        saves.add(save);
    }

    public List<Skill> getSave() {
        return saves;
    }
    

}
