
import ui.guiDev;

import java.sql.*;

import javax.swing.WindowConstants;

import app.*;

public class Main {

    public static void main(String[] args) {
        String _url = args[0];
        String _user = args[1];
        String _pwd = args[2];
        String _driver = args[3];

        try {

            ConnectionFactory.setupInfo(_url, _user, _pwd, _driver);

        } catch(Exception exception) {
            exception.printStackTrace();
        }

            Monster monster = MonsterFactory.createMonster("Goblin", "MM");
            monster.getCon();

            Encounter encounter = EncounterFactory.getEncounter("Two Much Pudding", "emagoffi");
            encounter.getDescription();

            

        guiDev gui = new guiDev();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}