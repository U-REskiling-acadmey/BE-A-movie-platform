package Main;

import dao.UserDAO;

import javax.swing.*;

public class NextScrean extends JFrame {
    private UserDAO userdao;

    public NextScrean(String username) {
        setTitle("Next Screen - " + username);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        JLabel nextLabel = new JLabel("This is the next screen", SwingConstants.CENTER);
        getContentPane().add(nextLabel);

        setVisible(true);
    }

    // 프레임이 닫힐 때 DB 연결을 닫음
    @Override
    public void dispose(){
        super.dispose();
        userdao.close();
    }
}
