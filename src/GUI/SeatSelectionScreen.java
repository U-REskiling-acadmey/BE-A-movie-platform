package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SeatSelectionScreen extends JFrame {
    private String username;
    private int movieId;
    private int theaterId;
    private String showDate;
    private String showTime;
    private JPanel seatPanel;
    private List<JToggleButton> seatButtons;
    private JComboBox<Integer> ticketCountComboBox;

    public SeatSelectionScreen(String username, int movieId, int theaterId, String showDate, String showTime) {
        this.username = username;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.showDate = showDate;
        this.showTime = showTime;
        this.seatButtons = new ArrayList<>();

        setTitle("영화 예매 프로그램 ver1.0");
        setSize(700, 700);
        setLayout(new BorderLayout());

        // 헤더 패널
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(65, 105, 225));
        headerPanel.setPreferredSize(new Dimension(400, 50));
        add(headerPanel, BorderLayout.NORTH);

        // 예매 인원 선택
        JPanel ticketPanel = new JPanel();
        JLabel ticketLabel = new JLabel("예매 인원:");
        Integer[] ticketCounts = {1, 2, 3, 4, 5};
        ticketCountComboBox = new JComboBox<>(ticketCounts);
        ticketPanel.add(ticketLabel);
        ticketPanel.add(ticketCountComboBox);
        add(ticketPanel, BorderLayout.NORTH);

        // 좌석 패널
        seatPanel = new JPanel(new GridLayout(4, 5, 10, 10));
        createSeatButtons();
        JScrollPane scrollPane = new JScrollPane(seatPanel);
        add(scrollPane, BorderLayout.CENTER);

        // 예매하기 버튼
        JButton reserveButton = new JButton("예매하기");
        reserveButton.addActionListener(e -> reserveSeats());
        add(reserveButton, BorderLayout.SOUTH);

        // 이전으로 버튼
        JButton backButton = new JButton("이전으로");
        backButton.addActionListener(e -> dispose());
        add(backButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void createSeatButtons() {
        for (int i = 1; i <= 20; i++) {
            JToggleButton seatButton = new JToggleButton(String.valueOf(i));
            seatButtons.add(seatButton);
            seatPanel.add(seatButton);
        }
    }

    private void reserveSeats() {
        int selectedCount = (int) ticketCountComboBox.getSelectedItem();
        List<String> selectedSeats = new ArrayList<>();

        for (JToggleButton button : seatButtons) {
            if (button.isSelected()) {
                selectedSeats.add(button.getText());
            }
        }

        if (selectedSeats.size() != selectedCount) {
            JOptionPane.showMessageDialog(this, "선택한 좌석 수와 예매 인원이 일치하지 않습니다.");
            return;
        }

        JOptionPane.showMessageDialog(this, "예매가 완료되었습니다.");
        dispose();
    }
}