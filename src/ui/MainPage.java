package ui;

import javax.swing.*;

public class MainPage {
    JFrame frame;

    public MainPage() {
        frame = new JFrame();


        JButton button = new JButton("Hello There");
        frame.add(button);

        frame.setSize(900, 600);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setVisible(true);
        

    }
}
