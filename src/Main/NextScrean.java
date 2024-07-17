package Main;

import javax.swing.*;

public class NextScrean extends JFrame {
    public NextScrean() {
        setTitle("Next Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        JLabel nextLabel = new JLabel("This is the next screen", SwingConstants.CENTER);
        getContentPane().add(nextLabel);

        setVisible(true);
    }
}
