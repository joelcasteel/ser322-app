package ui;

import java.awt.BorderLayout;

import javax.swing.*;

public class MainPage {
    JFrame frame;

    public MainPage() {
        frame = new JFrame("Encounter Builder");

        JPanel mid = new JPanel();
        mid.setLayout(new BoxLayout(mid, BoxLayout.Y_AXIS));
        frame.add(mid, BorderLayout.CENTER);

        mid.add(new EncounterMonsterList());
        mid.add(new PlayerList());

        EncounterInfo encounterInfo = new EncounterInfo();
        frame.add(encounterInfo, BorderLayout.NORTH);

        frame.add(new MonsterSearch(), BorderLayout.WEST);


        frame.setSize(900, 600);
        frame.setVisible(true);
        

    }
}
