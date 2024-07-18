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

    }
}
