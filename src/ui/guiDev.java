package ui;

import javax.imageio.ImageIO;
import javax.swing.*;

import app.*;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
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
		ImageIcon icon = new ImageIcon("src\\resources\\monsterCardBG.png");
		JLabel thumb = new JLabel();
		thumb.setIcon(icon);
		monsterCardPanel.add(thumb);
		
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
		monsterCardsList.setSize(604, 2000);
		JLabel thumb2 = new JLabel();
		thumb2.setIcon(icon);
		JLabel thumb3 = new JLabel();
		thumb3.setIcon(icon);
		JLabel specialIcon = new JLabel();
		ImageIcon icon2 = new ImageIcon("src\\resources\\damagetype\\acid.png");
		specialIcon.setIcon(icon2);
		
		
		JPanel monsterCard1 = new JPanel();
		JPanel monsterCard2 = new JPanel();
		JPanel monsterCard3 = new JPanel();

		JLabel testtxt = new JLabel("test");

		//JPanel monsterCard2 = new JPanel();
		
		Painter p = new Painter();
		p.addText();
		Painter p2 = new Painter();
		Painter p3 = new Painter();

		//monsterCard1.add(p, BorderLayout.CENTER);
		//monsterCard1.setSize(604,510);
		//monsterCard1.setBackground(Color.BLACK);
		monsterCard2.add(p2);
		monsterCard3.add(p3);

		//p.setNextLayer();
		
		//monsterCard2 = p.getPanel();
		//monsterCard2.add(testtxt);
	
		//monsterCard1.add(thumb2, JLayeredPane.DEFAULT_LAYER);

		//monsterCard1.add(testtxt, 1);
		//monsterCard1.add(thumb3, 0);

		
		//monsterCard2.add(thumb3);
		//monsterCard2.add(specialIcon);

		monsterCardsList.add(monsterCard1);
		monsterCardsList.add(monsterCard2);
		monsterCardsList.add(monsterCard3);


		monsterCardsList.setAutoscrolls(true);
		
		JScrollPane monstersInEncounterScrollPane = new JScrollPane(monsterCard1);
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


class Painter extends JPanel {
	private Image img;
	
	public Painter() {
		try {
			img = ImageIO.read(new File("src\\resources\\monsterCardBG.png"));
		}
		catch (Exception E) {
			E.printStackTrace();
		}
		this.setBackground(Color.RED);
		this.setMinimumSize(new Dimension(604, 510));
	}
	
	public void addText() {
		JLabel txt = new JLabel("asdf;lkajsdfoasjdfo;asjdfoasjdflskajdflaksj");
		this.add(txt);
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g); 
	    g.drawImage(img, 0, 0, this);   
	}
}
