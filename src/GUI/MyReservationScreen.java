package GUI;

import javax.swing.*;

public class MyReservationScreen extends JFrame {
    public MyReservationScreen() {
        setTitle("예매 확인");
        setSize(300, 200);

        // 프로그램 종료 설정 추가
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램이 종료됨

        add(new JLabel("예매 확인 화면", SwingConstants.CENTER));
        setLocationRelativeTo(null);
    }
}

