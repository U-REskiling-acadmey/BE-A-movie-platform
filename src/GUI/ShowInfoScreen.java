package GUI;

import javax.swing.*;

public class ShowInfoScreen extends JFrame {
    public ShowInfoScreen() {
        setTitle("내 정보 보기");
        setSize(300, 200);

        // 프로그램 종료 설정 추가
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램이 종료됨

        add(new JLabel("내 정보 보기 화면", SwingConstants.CENTER));
        setLocationRelativeTo(null);
    }
}
