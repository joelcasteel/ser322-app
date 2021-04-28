package ui;

import app.*;
import javax.swing.*;

public class MonsterCard extends JPanel {
    private Monster monster;

    public MonsterCard(Monster pMonster) {
        monster = pMonster;
    }

    public Monster getMonster() {
        return monster;
    }
    
}
