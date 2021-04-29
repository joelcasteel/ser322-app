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
        JTextArea text = new JTextArea();
        String name = entry.getMName();
        String source = entry.getMSource();
        String alias = entry.getAlias();
        String notes = entry.getNotes();
        text.setText("\nMonster Alias: " + alias
                + "\nMonster Name: " + name
                + "\nMonster Source: " + source
                + "\nNotes: " + notes);
        text.setEditable(false);
        text.setBackground(this.getBackground());
        text.setSize(100, 500);
        
        removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                encounter.removeMonsterEntry(entry);
                containerPanel.remove(self);

                if(containerPanel.getComponents().length == 0) {
                    containerPanel.add(new JLabel("No Monsters Added"));
                }
                containerPanel.revalidate();
			}
		});
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(600,600);
        this.add(text);
        this.add(removeButton);


    }
}
