package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class DateReservationScreen extends JFrame {
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private Calendar cal;

    public DateReservationScreen() {
        setTitle("영화별 예매");
        setSize(300, 200);

        //헤더 패널(상단 파란색 부분)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(65,105,225));
        headerPanel.setPreferredSize(new Dimension(300,50));
        add(headerPanel, BorderLayout.NORTH);

        // 프로그램 종료 설정 추가
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램이 종료됨

        add(new JLabel("영화별 예매 화면", SwingConstants.CENTER));
        setLocationRelativeTo(null);
    }
}
