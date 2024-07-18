package GUI;

import dao.MovieDAO;
import dto.MovieDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MovieListScreen extends JFrame {
    private String username;
    private String selectedDate;
    private JPanel movieListPanel;
    private MovieDAO movieDAO;

    public MovieListScreen(String username, String selectedDate) {
        this.username = username;
        this.selectedDate = selectedDate;
        this.movieDAO = new MovieDAO();

        setTitle("영화 예매 프로그램 ver1.0");
        setSize(400, 600); // 창 크기 줄임
        setLayout(new BorderLayout());

        // 헤더 패널 설정
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(65, 105, 225));
        headerPanel.setPreferredSize(new Dimension(300, 20)); // 헤더 크기 감소
        add(headerPanel, BorderLayout.NORTH);

        // 영화 목록 패널 설정
        movieListPanel = new JPanel();
        movieListPanel.setLayout(new BoxLayout(movieListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(movieListPanel);
        add(scrollPane, BorderLayout.CENTER);

        // 영화 목록 표시
        displayMovieList();

        // 이전 버튼 설정
        JButton backButton = new JButton("이전으로");
        backButton.addActionListener(e -> {
            dispose();
            new DateReservationScreen(username);
        });
        add(backButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void displayMovieList() {
        List<MovieDTO> movies = movieDAO.getMoviesForDate(selectedDate);

        for (MovieDTO movie : movies) {
            JPanel moviePanel = new JPanel(new BorderLayout());
            moviePanel.setPreferredSize(new Dimension(380, 40)); // 높이를 40으로 줄임
            moviePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel ageLabel = new JLabel(String.valueOf(movie.getAgeLimit()));
            ageLabel.setOpaque(true);
            ageLabel.setHorizontalAlignment(JLabel.CENTER);
            ageLabel.setPreferredSize(new Dimension(40, 40)); // 너비와 높이를 40으로 설정
            setAgeLimitColor(ageLabel, movie.getAgeLimit());

            JPanel infoPanel = new JPanel(new BorderLayout());
            JLabel titleLabel = new JLabel(movie.getTitle());
            titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

            JLabel runtimeLabel = new JLabel(movie.getRunningTime() + "분");
            runtimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            runtimeLabel.setHorizontalAlignment(JLabel.RIGHT);

            infoPanel.add(titleLabel, BorderLayout.CENTER);
            infoPanel.add(runtimeLabel, BorderLayout.EAST);

            moviePanel.add(ageLabel, BorderLayout.WEST);
            moviePanel.add(infoPanel, BorderLayout.CENTER);

            moviePanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    new TheaterListScreen(username, selectedDate, movie);
                }
            });

            movieListPanel.add(moviePanel);
            movieListPanel.add(Box.createRigidArea(new Dimension(0, 5))); // 항목 사이 간격
        }
    }

    private void setAgeLimitColor(JLabel label, int ageLimit) {
        if (ageLimit == 0) {
            label.setBackground(Color.GREEN);
        } else if (ageLimit == 12) {
            label.setBackground(Color.YELLOW);
        } else if (ageLimit == 15) {
            label.setBackground(Color.ORANGE);
        } else if (ageLimit == 19) {
            label.setBackground(Color.RED);
        } else {
            label.setBackground(Color.BLUE); // 전체 관람가
            label.setText("전체");
        }
    }
}