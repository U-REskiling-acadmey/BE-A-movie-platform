package GUI;

import javax.swing.*;

public class TheaterReservationScreen extends JFrame {
    public TheaterReservationScreen() {
        setTitle("상영관별 예매");
        setSize(300, 200);

        // 프로그램 종료 설정 추가
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램이 종료됨

        add(new JLabel("상영관별 예매 화면", SwingConstants.CENTER));
        setLocationRelativeTo(null);
    }
}
