package app;

public class LegendaryAction extends Action {
    private int cost;

    public LegendaryAction(String pActionName, String pDescription, int pCost) {
        super(pActionName, pDescription);
        cost = pCost;
    }

    public int getCost() {
        return cost;
        
    }
}
