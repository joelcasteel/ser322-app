package ui;

import javax.swing.*;

/**
 * Should display a list of players that are part of an ecounter and a name field to add them.
 */
public class PlayerList extends JPanel {
    public PlayerList() {
        this.add(new JLabel("Player List + field for adding username"));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
}
