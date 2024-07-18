package GUI;

import javax.swing.*;

public class ShowInfoScreen extends JFrame {
    public ShowInfoScreen() {
        setTitle("내 정보 보기");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("내 정보 보기 화면", SwingConstants.CENTER));
        setLocationRelativeTo(null);
    }
}
