package Main;

import GUI.DateReservationScreen;
import GUI.MyReservationScreen;
import GUI.ShowInfoScreen;
import GUI.TheaterReservationScreen;
import dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NextScreen extends JFrame {
    private UserDAO userDAO;
    private String username;

    public NextScreen(String username) {
        this.username = username;
        setTitle("영화 예매 프로그램 ver1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(65, 105, 225)); // 로얄 블루 색상
        headerPanel.setForeground(Color.BLACK);  // 글자색을 검은색으로 설정
        headerPanel.setPreferredSize(new Dimension(400, 50));
        add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton dateReservationBtn = createButton("영화별 예매", e -> new DateReservationScreen(username).setVisible(true));
        JButton theaterReservationBtn = createButton("상영관별 예매", e -> new TheaterReservationScreen().setVisible(true));
        JButton myReservationBtn = createButton("예매 확인", e -> new MyReservationScreen().setVisible(true));
        JButton showInfoBtn = createButton("내 정보 보기", e -> new ShowInfoScreen(username).setVisible(true));

        buttonPanel.add(dateReservationBtn);
        buttonPanel.add(theaterReservationBtn);
        buttonPanel.add(myReservationBtn);
        buttonPanel.add(showInfoBtn);

        add(buttonPanel, BorderLayout.CENTER);

        JButton logoutBtn = new JButton("로그아웃");
        logoutBtn.addActionListener(e -> {
            dispose();
            new MainManager();
        });
        add(logoutBtn, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(new Color(65, 105, 225));
        button.setForeground(Color.BLACK);  // 글자색을 검은색으로 변경
        button.addActionListener(action);
        return button;
    }

    @Override
    public void dispose() {
        super.dispose();
        if (userDAO != null) {
            userDAO.close();
        }
    }
}
