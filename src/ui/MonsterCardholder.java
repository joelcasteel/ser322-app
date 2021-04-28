package ui;

import app.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Component;

public class MonsterCardholder extends JPanel {
    Encounter sourceEncounter;
    JPanel containerPanel;
    MonsterCard monsterCard;
    MonsterEntry monsterEntry;

    JPanel self;

    public MonsterCardholder(Encounter encounter, JPanel panel, MonsterCard card, MonsterEntry entry) {
        sourceEncounter = encounter;
        containerPanel = panel;
        monsterCard = card;
        self = this;


        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                encounter.removeMonsterEntry(entry);
                containerPanel.remove(self);
                revalidate();
			}
		});
        this.add(removeButton, BorderLayout.NORTH);

    }
}
