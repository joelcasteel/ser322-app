package app;

public class Action {
    private String name;
    private String description;

    public Action(String pName, String pDescription) {
        name = pName;
        description = pDescription;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
