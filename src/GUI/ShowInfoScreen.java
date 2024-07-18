package GUI;

import dao.UserDAO;
import dto.UserDTO;

import javax.swing.*;
import java.awt.*;

public class ShowInfoScreen extends JFrame {
    private UserDAO userDAO;
    private String username;

    public ShowInfoScreen(String username) {
        this.username = username;
        this.userDAO = new UserDAO();

        // 기본 프레임 설정
        setTitle("내 정보 보기");
        setSize(300, 400);
        setLayout(new BorderLayout());

        //헤더 패널(상단 파란색 부분)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(65, 105, 225)); // 로얄 블루 색상
        headerPanel.setPreferredSize(new Dimension(300, 50));
        add(headerPanel, BorderLayout.NORTH);

        //메인 패널(사용자 정보를 표시할 중앙 부분)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //프로필 이미지 추가
        ImageIcon profileIcon = new ImageIcon("./src/Main/resources/images/profile.png"); // 실제 이미지 경로
        Image image = profileIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // 이미지 크기 설정
        JLabel profileImage = new JLabel(new ImageIcon(image));
        profileImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(profileImage);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 이미지와 텍스트 사이 간격

        // 사용자 정보 표시
        UserDTO user = new UserDTO(); // 데이터베이스에서 사용자 정보 가져오기
        String[] labels = {"유저","나이"};
        String[] values = {user.getUsername(), String.valueOf(user.getAge())};

        // 각 정보를 레이블로 표시
        for(int i=0; i<labels.length; i++){
            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel(labels[i] + " : ");
            JLabel value = new JLabel(values[i]);
            label.setPreferredSize(new Dimension(80, 20));
            infoPanel.add(label);
            infoPanel.add(value);
            mainPanel.add(infoPanel);
        }

        add(mainPanel, BorderLayout.CENTER);

        // 메인으로 돌아가는 버튼
        JButton mainButton = new JButton("메인으로");
        mainButton.setBackground(new Color(65, 105, 225));
        mainButton.setForeground(Color.BLACK);
        mainButton.addActionListener(e -> {
            dispose(); // 현재 창 닫기
            new Main.NextScreen(username); // 메인 화면으로 돌아가기
        });
        add(mainButton, BorderLayout.SOUTH);

        // 프레임 설정 마무리
        setLocationRelativeTo(null); // 화면 중앙에 표시
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫으면 이 화면만 종료
    }

    // 데이터베이스에서 사용자 정보를 가져오는 메소드
    private String getUserInfo() {
        return userDAO.getUsername(username);
    }
}
