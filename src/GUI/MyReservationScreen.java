package GUI;

import javax.swing.*;

public class MyReservationScreen extends JFrame {
    public MyReservationScreen() {
        setTitle("예매 확인");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("예매 확인 화면", SwingConstants.CENTER));
        setLocationRelativeTo(null);
    }
}

