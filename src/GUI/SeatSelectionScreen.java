package GUI;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import dao.SeatReservationDAO;
import dto.SeatReservationDTO;

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

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        // 예매하기 버튼
        JButton reserveButton = new JButton("예매하기");
        reserveButton.addActionListener(e -> reserveSeats());
        buttonPanel.add(reserveButton);

        // 이전으로 버튼
        JButton backButton = new JButton("이전으로");
        backButton.addActionListener(e -> dispose());
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

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

        String seats = String.join(",", selectedSeats);
        int price = selectedCount * 10000; // Assuming each ticket costs 10000 won

        // 시간을 "HH:mm:ss" 형식으로 포맷팅
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = LocalTime.now().format(timeFormatter);

        SeatReservationDTO reservation = new SeatReservationDTO(
                username,
                movieId,
                theaterId,
                LocalDate.now().toString(),
                currentTime,
                selectedCount,
                seats,
                price
        );

        SeatReservationDAO reservationDAO = new SeatReservationDAO();
        boolean success = reservationDAO.reserveSeats(reservation);
        reservationDAO.close();

        if (success) {
            JOptionPane.showMessageDialog(this, "예매가 완료되었습니다.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "예매에 실패했습니다.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SeatSelectionScreen("testuser", 1, 1, "2024-07-18", "18:00"));
    }
}
