package app;

public class Action {
    private String actionName;
    private String description;

    public Action(String pActionName, String pDescription) {
        actionName = pActionName;
        description = pDescription;
    }

    public String getActiionName() {
        return actionName;
    }

    public String getDescription() {
        return description;
    }
}
