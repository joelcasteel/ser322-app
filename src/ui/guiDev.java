package ui;

import javax.swing.*;

import app.*;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class guiDev extends JFrame {
    private HintTextField txtMonsterName;
    private HintTextField txtMonsterSource;
    private HintTextField txtMonsterAlias;
    private HintTextField txtMonsterNotes;
    private HintTextField txtEncounterName;
    private HintTextField textUserName;
    private HintTextField textDifficulty;
    private HintTextField textDescription;
    private HintTextField textEncounterNotes;
    private HintTextField textField;

    private Encounter encounter = null;
    private MonsterEntry monsterEntry = null;

    public void run() {
    }

    public guiDev() {

        getContentPane().setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1156, 585);
        getContentPane().add(mainPanel);
        mainPanel.setLayout(null);

        JPanel monsterSearchPanel = new JPanel();
        monsterSearchPanel.setBounds(0, 0, 527, 570);
        mainPanel.add(monsterSearchPanel);
        monsterSearchPanel.setLayout(new BorderLayout(0, 0));

        JPanel MonsterSearchBarPanel = new JPanel();
        monsterSearchPanel.add(MonsterSearchBarPanel, BorderLayout.NORTH);
        MonsterSearchBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        txtMonsterName = new HintTextField("Monster Name");
        txtMonsterName.setColumns(10);
        MonsterSearchBarPanel.add(txtMonsterName);

        txtMonsterSource = new HintTextField("Monster Source");
        txtMonsterSource.setColumns(10);
        MonsterSearchBarPanel.add(txtMonsterSource);

        JButton btnMonsterSearch = new JButton("Search Monster");
        btnMonsterSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtMonsterName.getText();
                String source = txtMonsterSource.getText();

                Monster monster = MonsterFactory.createMonster(name, source);
                // monster was not found
                if (monster.getName() == null) {
                    JOptionPane.showMessageDialog(null, "Monster was not found");
                    monsterEntry = null;
                }
                // monster found
                else {
                    monsterEntry = new MonsterEntry(monster.getName(), monster.getSource(), monster,
                            txtMonsterAlias.getText(), txtMonsterNotes.getText());
                    encounter.addMonsterEnty(monsterEntry);
                }
            }
        });
        MonsterSearchBarPanel.add(btnMonsterSearch);

        JPanel monsterCardPanel = new JPanel();
        monsterSearchPanel.add(monsterCardPanel, BorderLayout.CENTER);

        JPanel addMonsterPanel = new JPanel();
        monsterSearchPanel.add(addMonsterPanel, BorderLayout.SOUTH);

        txtMonsterAlias = new HintTextField("Monster Alias");
        addMonsterPanel.add(txtMonsterAlias);
        txtMonsterAlias.setColumns(10);

        txtMonsterNotes = new HintTextField("Monster Notes");
        addMonsterPanel.add(txtMonsterNotes);
        txtMonsterNotes.setColumns(10);

        JButton btnNewButton = new JButton("Add Monster to Encounter");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (encounter == null) {
                    JOptionPane.showMessageDialog(null, "No Encounters");
                } else if (monsterEntry == null) {
                    JOptionPane.showMessageDialog(null, "No Monster Selected");
                } else {
                    encounter.addMonsterEnty(monsterEntry);
                }
            }
        });
        addMonsterPanel.add(btnNewButton);

        JPanel encounterPanel = new JPanel();
        encounterPanel.setBounds(526, 0, 630, 61);
        mainPanel.add(encounterPanel);
        encounterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        txtEncounterName = new HintTextField("Encounter Name");
        txtEncounterName.setColumns(10);
        encounterPanel.add(txtEncounterName);

        textUserName = new HintTextField("Username");
        textUserName.setColumns(10);
        encounterPanel.add(textUserName);

        textDifficulty = new HintTextField("Difficulty");
        textDifficulty.setColumns(10);
        encounterPanel.add(textDifficulty);

        textDescription = new HintTextField("Description");
        textDescription.setColumns(10);
        encounterPanel.add(textDescription);

        textEncounterNotes = new HintTextField("Notes");
        textEncounterNotes.setColumns(10);
        encounterPanel.add(textEncounterNotes);

        JButton btnSearchEncounter = new JButton("Search Encounter");
        btnSearchEncounter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtEncounterName.getText();
                String username = textUserName.getText();
                
                if(name == "" || username == "") {
                    JOptionPane.showMessageDialog(null, "Need username and Encounter name");
                    return;
                }

                Encounter result = EncounterFactory.getEncounter(name, username);
                if (result != null) {
                    encounter = result;
                    textDescription.setText(encounter.getDescription());
                    textDifficulty.setText(encounter.getDifficulty());
                    textEncounterNotes.setText(encounter.getNotes());
                } else {

                    encounter = EncounterFactory.createEncounter(name, username, textDescription.getText(),
                            textEncounterNotes.getText());
                }
            }
        });
        encounterPanel.add(btnSearchEncounter);

        JButton btnSaveEncounter = new JButton("Save Encounter");
        btnSaveEncounter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = txtEncounterName.getText();
                String username = textUserName.getText();
                String description = textDescription.getText();
                String notes = textEncounterNotes.getText();
                String difficulty = textDifficulty.getText();
                

                if (encounter == null) {
                    JOptionPane.showMessageDialog(null, "Please Search/Create an encounter first");
                }else {
                    encounter.setTitle(name, username);
                    encounter.setDescription(description);
                    encounter.setNotes(notes);
                    encounter.setDifficulty(difficulty);
                    EncounterFactory.saveEncounter(encounter);
                }


            }
        });
        encounterPanel.add(btnSaveEncounter);

        JPanel monsterCardsList = new JPanel();
        monsterCardsList.setAutoscrolls(true);

        JScrollPane monstersInEncounterScrollPane = new JScrollPane(monsterCardsList);
        monstersInEncounterScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        monstersInEncounterScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        monstersInEncounterScrollPane.setBounds(526, 60, 604, 510);

        mainPanel.add(monstersInEncounterScrollPane);

        getContentPane().add(mainPanel);
        this.setVisible(true);
        this.setSize(1162, 618);
        this.setResizable(false);
    }
}

// source: https://stackoverflow.com/questions/1738966/java-jtextfield-with-input-hint
class HintTextField extends JTextField implements FocusListener {

    private final String hint;
    private boolean showingHint;

    public HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            showingHint = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText(hint);
            showingHint = true;
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}
