package ui;

import javax.swing.*;
/**
 * This should generate a list of monsters along with their nicknames
 * 
 * 
 */
public class EncounterMonsterList extends JPanel {
    public EncounterMonsterList() {
        this.add(new JLabel("List of monsters + nickname "));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    }
}
