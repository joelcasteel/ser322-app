package app;

import java.util.List;
import java.util.ArrayList;

public class Monster {
    //Title Attrribute (PK)
    private String name;
    private string source;

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

    }

    /**
     * Yeah, we could have done this with a hashmap, but i don't want to get into that
     * 
     * @param stat
     * @param value
     */
    public void setAttributes(String stat, int value) {
        switch(stat) {
            case "str":
                str = value;
            break;

            case "dex":
                dex = value;
            break;

            case "con":
                con = value;
            break;

            case "wis":
                wis = value;
            break;

            case "intl":
            case "int":
                intl = value;
            break;

            case "cha":
                cha = value;
            break;

            default:
            break;



        }
    }

    
}
