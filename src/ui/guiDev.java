package ui;

import javax.swing.*;

import app.*;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;


public class guiDev extends JFrame {
	private JTextField txtMonsterName;
	private JTextField txtMonsterSource;
	private JTextField txtMonsterAlias;
	private JTextField txtMonsterNotes;
	private JTextField txtEncounterName;
	private JTextField textUserName;
	private JTextField textDifficulty;
	private JTextField textDescription;
	private JTextField textEncounterNotes;
	private JTextField textField;
	
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
		
		txtMonsterName = new JTextField();
		txtMonsterName.setText("Monster Name");
		txtMonsterName.setColumns(10);
		MonsterSearchBarPanel.add(txtMonsterName);
		
		txtMonsterSource = new JTextField();
		txtMonsterSource.setText("Monster Source");
		txtMonsterSource.setColumns(10);
		MonsterSearchBarPanel.add(txtMonsterSource);
		
		JButton btnMonsterSearch = new JButton("Search Monster");
		btnMonsterSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtMonsterName.getText();
				String source = txtMonsterSource.getText();

				Monster monster = MonsterFactory.createMonster(name, source);
				monster.getAc();
			}
		});
		MonsterSearchBarPanel.add(btnMonsterSearch);
		
		JPanel monsterCardPanel = new JPanel();
		monsterSearchPanel.add(monsterCardPanel, BorderLayout.CENTER);
		
		JPanel addMonsterPanel = new JPanel();
		monsterSearchPanel.add(addMonsterPanel, BorderLayout.SOUTH);
		
		txtMonsterAlias = new JTextField();
		txtMonsterAlias.setText("Monster Alias");
		addMonsterPanel.add(txtMonsterAlias);
		txtMonsterAlias.setColumns(10);
		
		txtMonsterNotes = new JTextField();
		txtMonsterNotes.setText("Monster Notes");
		addMonsterPanel.add(txtMonsterNotes);
		txtMonsterNotes.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Monster to Encounter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		addMonsterPanel.add(btnNewButton);
		
		JPanel encounterPanel = new JPanel();
		encounterPanel.setBounds(526, 0, 630, 61);
		mainPanel.add(encounterPanel);
		encounterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtEncounterName = new JTextField();
		txtEncounterName.setText("Encounter Name");
		txtEncounterName.setColumns(10);
		encounterPanel.add(txtEncounterName);
		
		textUserName = new JTextField();
		textUserName.setText("Username");
		textUserName.setColumns(10);
		encounterPanel.add(textUserName);
		
		textDifficulty = new JTextField();
		textDifficulty.setText("Difficulty");
		textDifficulty.setColumns(10);
		encounterPanel.add(textDifficulty);
		
		textDescription = new JTextField();
		textDescription.setText("Description");
		textDescription.setColumns(10);
		encounterPanel.add(textDescription);
		
		textEncounterNotes = new JTextField();
		textEncounterNotes.setText("Notes");
		textEncounterNotes.setColumns(10);
		encounterPanel.add(textEncounterNotes);
		
		JButton btnSearchEncounter = new JButton("Search Encounter");
		btnSearchEncounter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtEncounterName.getText();
				String username = textUserName.getText();

				Encounter encounter = EncounterFactory.getEncounter(name, username);

				textDescription.setText(encounter.getDescription());
				textDifficulty.setText(encounter.getDifficulty());
				textEncounterNotes.setText(encounter.getNotes());
			}
		});
		encounterPanel.add(btnSearchEncounter);
		
		JButton btnSaveEncounter = new JButton("Save Encounter");
		btnSaveEncounter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
