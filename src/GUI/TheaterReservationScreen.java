package GUI;

import javax.swing.*;

public class TheaterReservationScreen extends JFrame {
    public TheaterReservationScreen() {
        setTitle("상영관별 예매");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("상영관별 예매 화면", SwingConstants.CENTER));
        setLocationRelativeTo(null);
    }
}
