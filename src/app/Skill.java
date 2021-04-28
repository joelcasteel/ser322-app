package app;

public class Skill {
    String name;
    int score;

    public Skill(String pName, int pScore) {
        name = pName;
        score = pScore;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
