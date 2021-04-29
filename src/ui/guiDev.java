package ui;

import javax.imageio.ImageIO;
import javax.swing.*;


import app.*;
import app.Action;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Dimension;


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

	private JScrollPane monstersInEncounterScrollPane;
	JPanel monsterCardsList;


	private Encounter encounter = null;
	private MonsterEntry monsterEntry = null;
	
	public void run() {
	}
	
	public guiDev() {
		
		getContentPane().setLayout(null);
		
      
      Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (int) ((dimension.getWidth() - this.getWidth()) / 4);
      int y = (int) ((dimension.getHeight() - this.getHeight()) / 4);
      this.setLocation(x, y);

		
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
		
		
	  JPanel monsterCardPanel = new JPanel();
	  monsterCardPanel.setLayout(new BoxLayout(monsterCardPanel, BoxLayout.Y_AXIS));
	  
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
                  
					System.out.println("Adding searched monster panel...");

					//Remove all previous elements from the panel
					monsterCardPanel.removeAll();
					
					//display the statblock label
					String statsBlock = "STR: " + monster.getStr() + "\nDEX: " + monster.getDex() + "\nCON: " + monster.getCon() + "\nINT: " + monster.getIntl() +
							"\nWIS: " + monster.getWis() + "\nCHA: " + monster.getCha() + "\n";
					JTextArea statBlock = new JTextArea(statsBlock);
					statBlock.setEditable(false);
					statBlock.setBackground(monsterCardPanel.getBackground());
					monsterCardPanel.add(statBlock);
					
					//display the hp, armor class, challenge rating, and legendary count label
					String hpAcCr = "HP: " + monster.getHp() + "\nArmor Class: " + monster.getAc() + "\nChallenge Rating: " + monster.getCr() + 
							"\nLegendary Action Count: " + monster.getLegendCount() + "\n";
					JTextArea hpBlock = new JTextArea(hpAcCr);
					hpBlock.setEditable(false);
					hpBlock.setBackground(monsterCardPanel.getBackground());
					monsterCardPanel.add(hpBlock);
					
					//display monster descriptors
					String descriptors = "Type: " + monster.getType() + "\nSize: " + monster.getSize() + "\nSpeed: " + monster.getSpeed() + "\nAlignment: "
					+ monster.getAlignment() + "\n";
					JTextArea descriptorBlock = new JTextArea(descriptors);
					descriptorBlock.setEditable(false);
					descriptorBlock.setBackground(monsterCardPanel.getBackground());
					monsterCardPanel.add(descriptorBlock);
					
					//display monster senses
					List<String> senses = monster.getSenseList();
					String senseStr = "Senses\n";
					if (senses.size() > 0) {
						for (String s : senses) {
							senseStr += s + "\n";
						}
						JTextArea sensesBlock = new JTextArea(senseStr);
						sensesBlock.setBackground(monsterCardPanel.getBackground());
						sensesBlock.setEditable(false);
						monsterCardPanel.add(sensesBlock);
					}
					
					// display monster conditional immunities
					String condImmStr = "Conditional Immunities\n";
					List<String> condImm = monster.getCondImmunity();
					if (condImm.size() > 0) {
						for (String s : condImm) {
							condImmStr += s + "\n";
						}
						JTextArea condImmBlock = new JTextArea(condImmStr);
						condImmBlock.setBackground(monsterCardPanel.getBackground());
						condImmBlock.setEditable(false);
						monsterCardPanel.add(condImmBlock);
					}
					
					String langStr = "Languages\n";
					List<String> languages = monster.getLanguage();
					if (languages.size() > 0) {
						for (String s : languages) {
							langStr += s + "\n";
						}
						JTextArea langBlock = new JTextArea(langStr);
						langBlock.setBackground(monsterCardPanel.getBackground());
						langBlock.setEditable(false);
						monsterCardPanel.add(langBlock);
					}
					
					String dmgImm = "Damage Immunities\n";
					List<String> dmgImmList = monster.getDamageImmunity();
					if (dmgImmList.size() > 0) {
						for (String s : dmgImmList) {
							dmgImm += s + "\n";
						}
						JTextArea dmgImmBlock = new JTextArea(dmgImm);
						dmgImmBlock.setBackground(monsterCardPanel.getBackground());
						dmgImmBlock.setEditable(false);
						monsterCardPanel.add(dmgImmBlock);
					}
					
					String dmgVuln = "Damage Vulnerabilities\n";
					List<String> dmgVulList = monster.getDamageVulnerability();
					if (dmgVulList.size() > 0) {
						for (String s : dmgVulList) {
							dmgVuln += s + "\n";
						}
						JTextArea dmgVulnBlock = new JTextArea(dmgVuln);
						dmgVulnBlock.setBackground(monsterCardPanel.getBackground());
						dmgVulnBlock.setEditable(false);
						monsterCardPanel.add(dmgVulnBlock);
					}
					
					String actionStr = "Actions\n";
					List<Action> actions = monster.getAction();
					if (actions.size() > 0) {
						for (Action a : actions) {
							actionStr += "Action Name: " + a.getName() + "\nDescription: " + 
						a.getDescription() + "\n\n";
						}
						JTextArea actionBlock = new JTextArea(actionStr);
						actionBlock.setBackground(monsterCardPanel.getBackground());
						actionBlock.setEditable(false);
						monsterCardPanel.add(actionBlock);
					}
					
					String legActionStr = "Legendary Actions\n";
					List<LegendaryAction> legActions = monster.gLegendaryAction();
					if (legActions.size() > 0) {
						for (LegendaryAction a : legActions) {
							legActionStr += "Action Name: " + a.getName() + "\nDescription: " + 
						a.getDescription() + "\n\n";
						}
						JTextArea legActionBlock = new JTextArea(legActionStr);
						legActionBlock.setBackground(monsterCardPanel.getBackground());
						legActionBlock.setEditable(false);
						monsterCardPanel.add(legActionBlock);
					}
					
					String passives = "Passives\n";
					List<Passive> passivesList = monster.getPassive();
					if (passivesList.size() > 0) {
						for (Passive p : passivesList) {
							passives += "Passive Name: " + p.getName() + "\nDescription: " + 
						p.getDescription() + "\n\n";
						}
						JTextArea passivesBlock = new JTextArea(passives);
						passivesBlock.setBackground(monsterCardPanel.getBackground());
						passivesBlock.setEditable(false);
						monsterCardPanel.add(passivesBlock);
					}
					
					
					String skillsStr = "Skills\n";
					List<Skill> skillList = monster.getSkill();
					if (skillList.size() > 0) {
						for (Skill s : skillList) {
							skillsStr += "Skill Name: " + s.getName() + "\nScore: " + 
						s.getScore() + "\n\n";
						}
						JTextArea skillsBlock = new JTextArea(skillsStr);
						skillsBlock.setBackground(monsterCardPanel.getBackground());
						skillsBlock.setEditable(false);
						monsterCardPanel.add(skillsBlock);
					}
					
					
					String savesStr = "Saves\n";
					List<Skill> savesList = monster.getSave();
					if (savesList.size() > 0) {
						for (Skill s : savesList) {
							savesStr += "Save Name: " + s.getName() + "\nScore: " + 
						s.getScore() + "\n\n";
						}
						JTextArea savesBlock = new JTextArea(savesStr);
						savesBlock.setBackground(monsterCardPanel.getBackground());
						savesBlock.setEditable(false);
						monsterCardPanel.add(savesBlock);
					}
					
					monsterCardPanel.updateUI();
					monsterCardPanel.repaint();

              }
          }
      });
      
		MonsterSearchBarPanel.add(btnMonsterSearch);
		
		JScrollPane monsterCardScroller = new JScrollPane(monsterCardPanel);
		monsterCardScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		monsterCardScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		
		monsterSearchPanel.add(monsterCardScroller, BorderLayout.CENTER);
		
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
              } else if (txtMonsterAlias.getText() == "") {
                  JOptionPane.showMessageDialog(null, "No Alias Selected");
              } else {
                  
                  monsterEntry.setAlias(txtMonsterAlias.getText());
                  monsterEntry.setNotes(txtMonsterNotes.getText());
                  encounter.addMonsterEnty(monsterEntry);
                  populateMonsterList(encounter.getMonsterEntryList());
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
                  textDescription.setHintText(encounter.getDescription());
                  
                  textDifficulty.setText(encounter.getDifficulty());
                  textDifficulty.setHintText(encounter.getDifficulty());
                  
                  textEncounterNotes.setText(encounter.getNotes());
                  textEncounterNotes.setHintText(encounter.getNotes());
                  populateMonsterList(encounter.getMonsterEntryList());
              } else {

                  encounter = EncounterFactory.createEncounter(name, username, textDescription.getText(),
                          textEncounterNotes.getText());
                  JOptionPane.showMessageDialog(null, "Created New Encounter");
   
              }
          }
      });

		encounterPanel.add(btnSearchEncounter);
		
      JButton btnSaveEncounter = new JButton("Save Encounter");
      btnSaveEncounter.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

              String name = txtEncounterName.getForceText();
              String username = textUserName.getForceText();
              String description = textDescription.getForceText();
              String notes = textEncounterNotes.getForceText();
              String difficulty = textDifficulty.getForceText();
              

              if (encounter == null) {
                  JOptionPane.showMessageDialog(null, "Please Search/Create an encounter first");
              }else if(AliasCheck(encounter.getMonsterEntryList())) {
                  JOptionPane.showMessageDialog(null, "Monsters must have unique aliases!");
              }
                  else {
                  encounter.setTitle(name, username);
                  encounter.setDescription(description);
                  encounter.setNotes(notes);
                  encounter.setDifficulty(difficulty);
                  EncounterFactory.saveEncounter(encounter);
                  JOptionPane.showMessageDialog(null, "Encounter Saved!");
              }


          }
      });
		encounterPanel.add(btnSaveEncounter);
		
		monsterCardsList = new JPanel();
		monsterCardsList.add(new JLabel("Search an Encounter to see Monsters in it."));
		//monsterCardsList.setAutoscrolls(true);
		
		monstersInEncounterScrollPane = new JScrollPane(monsterCardsList);
		monstersInEncounterScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		monstersInEncounterScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		monstersInEncounterScrollPane.setBounds(526, 60, 604, 510);
		
		
		mainPanel.add(monstersInEncounterScrollPane);
		
		
		getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setSize(1162, 618);
		this.setResizable(false);
	}

	public void populateMonsterList(List<MonsterEntry> list) {

		JPanel newList = new JPanel();
		BoxLayout layout = new BoxLayout(newList, BoxLayout.Y_AXIS);
		newList.setLayout(layout);
		//newList.add(new JLabel("Monsters"));

		Encounter e = encounter;

		e.getClass();
		e.getEName();

		for(MonsterEntry entry: list) {
			MonsterCard monsterCard = new MonsterCard(entry.getMonster());
			MonsterCardholder cardHolder = new MonsterCardholder(encounter, monstersInEncounterScrollPane, newList, monsterCard, entry);
			newList.add(cardHolder);
		}

		JPanel p = new JPanel();
		p.add(newList);
		monstersInEncounterScrollPane.setViewportView(p);
		monstersInEncounterScrollPane.revalidate();
		monsterCardsList.revalidate();
		revalidate();

		
	}
	
	public boolean AliasCheck(List<MonsterEntry> list) {
	    ArrayList<String> checklist = new ArrayList<String>();
	    for(MonsterEntry entry: list) {
	         String alias = entry.getAlias();
	         for(String aliasCheck: checklist) {
	             if(aliasCheck.equals(alias)) {
	                 return true;
	             }
	         }
	         checklist.add(alias);
	      }
	    return false;
	}
}



//source: https://stackoverflow.com/questions/1738966/java-jtextfield-with-input-hint
class HintTextField extends JTextField implements FocusListener {

 private String hint;
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
 
 public String getForceText() {
     return super.getText();
 }
 public void setHintText(String str) {
     hint = str;
 }
 }

