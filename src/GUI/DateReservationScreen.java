package GUI;

import javax.swing.*;

public class DateReservationScreen extends JFrame {
    public DateReservationScreen() {
        setTitle("영화별 예매");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("영화별 예매 화면", SwingConstants.CENTER));
        setLocationRelativeTo(null);
    }
}
