package GUI;

import dto.MovieDTO;

import dao.TheaterDAO;
import dto.TheaterDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TheaterListScreen extends JFrame {
    private String username;
    private String selectedDate;
    private MovieDTO selectedMovie;
    private JPanel theaterListPanel;
    private TheaterDAO theaterDAO;

    public TheaterListScreen(String username, String selectedDate, MovieDTO selectedMovie) {
        this.username = username;
        this.selectedDate = selectedDate;
        this.selectedMovie = selectedMovie;
        this.theaterDAO = new TheaterDAO();

        setTitle("영화 예매 프로그램 ver1.0");
        setSize(400, 600);
        setLayout(new BorderLayout());

        // 헤더 패널 설정
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(65, 105, 225));
        headerPanel.setPreferredSize(new Dimension(400, 20));
        add(headerPanel, BorderLayout.NORTH);

        // 영화관 목록 패널 설정
        theaterListPanel = new JPanel();
        theaterListPanel.setLayout(new BoxLayout(theaterListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(theaterListPanel);
        add(scrollPane, BorderLayout.CENTER);

        // 영화관 목록 표시
        displayTheaterList();

        // 이전 버튼 설정
        JButton backButton = new JButton("이전으로");
        backButton.addActionListener(e -> dispose());
        add(backButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void displayTheaterList() {
        List<TheaterDTO> theaters = theaterDAO.getTheatersForMovie(selectedMovie.getId(), selectedDate);

        for (TheaterDTO theater : theaters) {
            JPanel theaterPanel = new JPanel(new BorderLayout());
            theaterPanel.setPreferredSize(new Dimension(380, 40));
            theaterPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel theaterNameLabel = new JLabel(theater.getName());
            theaterNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel seatsLabel = new JLabel(theater.getRemainingSeats() + "석");
            JLabel timeLabel = new JLabel(theater.getShowTime());

            infoPanel.add(seatsLabel);
            infoPanel.add(timeLabel);

            theaterPanel.add(theaterNameLabel, BorderLayout.WEST);
            theaterPanel.add(infoPanel, BorderLayout.EAST);

            JButton selectButton = new JButton("선택");
            selectButton.addActionListener(e -> {
                new SeatSelectionScreen(username, selectedMovie.getId(), theater.getId(), selectedDate, theater.getShowTime());
                dispose();
            });

            theaterPanel.add(selectButton, BorderLayout.SOUTH);

            theaterListPanel.add(theaterPanel);
            theaterListPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
    }
}
