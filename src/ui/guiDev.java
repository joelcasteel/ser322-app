package ui;

import javax.imageio.ImageIO;
import javax.swing.*;

import app.*;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;
import java.awt.Dimension;


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

	private Encounter encounter = null;
	
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
		
		JPanel monsterCardPanel = new JPanel();
		monsterCardPanel.setBackground(Color.GREEN);
		
		JButton btnMonsterSearch = new JButton("Search Monster");
		btnMonsterSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtMonsterName.getText();
				String source = txtMonsterSource.getText();

				Monster monster = MonsterFactory.createMonster(name, source);
				monster.getAc();
				
				if (monster != null) {
					System.out.println("Adding searched monster panel...");

					//Remove all previous elements from the panel
					monsterCardPanel.removeAll();

					//Add the statblock label
					String statsBlock = "STR: " + monster.getStr() + "\nDEX: " + monster.getDex() + "\nCON: " + monster.getCon() + "\nINT: " + monster.getIntl() +
							"\nWIS: " + monster.getWis() + "\nCHA: " + monster.getCha();
					JLabel statBlock = new JLabel(statsBlock);
					monsterCardPanel.add(statBlock);
					
					//Add the hp, armor class, challenge rating, and legendary count label
					String hpAcCr = "HP: " + monster.getHp() + "\nArmor Class: " + monster.getAc() + "\nChallenge Rating: " + monster.getCr() + 
							"\nLegendary Action Count: " + monster.getLegendCount();
					JLabel hpBlock = new JLabel(hpAcCr);
					monsterCardPanel.add(hpBlock);
					
					String descriptors = "Type: " + monster.getType() + "\nSize: " + monster.getSize() + "\nSpeed: " + monster.getSpeed() + "\nAlignment: " + monster.getAlignment();
					JLabel descriptorBlock = new JLabel(descriptors);
					monsterCardPanel.add(descriptorBlock);
					
					List<String> senses = monster.getSenseList();
					String senseStr = "Senses: ";
					for (String s : senses) {
						senseStr += s + "\n";
					}
					JLabel sensesLabel = new JLabel(senseStr);
					monsterCardPanel.add(sensesLabel);
					
					
					monsterCardPanel.updateUI();

				}
			}
		});
		MonsterSearchBarPanel.add(btnMonsterSearch);
		
	

		
		
		
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

				Encounter result = EncounterFactory.getEncounter(name, username);
				if(result != null) {
					encounter = result;
					textDescription.setText(encounter.getDescription());
					textDifficulty.setText(encounter.getDifficulty());
					textEncounterNotes.setText(encounter.getNotes());
				}

				encounter = EncounterFactory.createEncounter(name, username, textDescription.getText(), textEncounterNotes.getText());
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

				if(encounter == null) {
					encounter = EncounterFactory.createEncounter(name, username, description, notes);

				} else {
					encounter.setTitle(name, username);
					encounter.setDescription(description);
					encounter.setNotes(notes);
					encounter.setDifficulty(difficulty);

				}
					

					EncounterFactory.saveEncounter(encounter);
					
			}
		});
		encounterPanel.add(btnSaveEncounter);
		
		JPanel monsterCardsList = new JPanel();
		monsterCardsList.setLayout(new BoxLayout(monsterCardsList, BoxLayout.Y_AXIS));

		
		
		JPanel monsterCard1 = new JPanel();
		JPanel monsterCard2 = new JPanel();
		JPanel monsterCard3 = new JPanel();

		JLabel testtxt = new JLabel("test");



		monsterCard1.setBackground(Color.BLACK);
		monsterCard1.setSize(604, 2000);
		monsterCard2.setBackground(Color.BLUE);

		monsterCard3.setBackground(Color.cyan);


		monsterCardsList.add(monsterCard1);
		monsterCardsList.add(monsterCard2);
		monsterCardsList.add(monsterCard3);


		monsterCardsList.setAutoscrolls(true);
		
		JScrollPane monstersInEncounterScrollPane = new JScrollPane(monsterCardsList);
		monstersInEncounterScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		monstersInEncounterScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		monstersInEncounterScrollPane.setBounds(526, 60, 604, 510);
		
		
		mainPanel.add(monstersInEncounterScrollPane);
		
		
		getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setSize(1162, 618);
		this.setResizable(false);
	}
}
