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

    JScrollPane scroll;
    JPanel self;

    public MonsterCardholder(Encounter encounter, JScrollPane scroller, JPanel panel, MonsterCard card, MonsterEntry entry) {
        sourceEncounter = encounter;
        containerPanel = panel;
        monsterCard = card;
        scroll = scroller;
        self = this;


        JButton removeButton = new JButton("Remove");
        
        removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                encounter.removeMonsterEntry(entry);

                if(containerPanel.getComponents().length == 1) {
                    self.setVisible(false);
                    containerPanel.add(new JLabel("No Monsters Added"));
                }
                    containerPanel.remove(self);
                containerPanel.revalidate();
			}
		});
        this.add(removeButton);

    }
}
