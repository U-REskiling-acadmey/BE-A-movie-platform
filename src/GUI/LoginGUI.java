package GUI;

import Main.MainManager;
import Main.NextScrean;
import dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI() {
        setTitle("로그인");
        setSize(300, 150); // Increased size to accommodate the new button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));

        // 사용자명 입력 필드
        inputPanel.add(new JLabel("Username :"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        // 비밀번호 입력 필드
        inputPanel.add(new JLabel("Password :"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        pane.add(inputPanel, BorderLayout.CENTER);

        // 로그인 버튼과 돌아가기 버튼을 담을 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                UserDAO userDao = new UserDAO();
                if (userDao.login(username, password)) {
                    JOptionPane.showMessageDialog(null, "로그인 성공!");
                    dispose();
                    new NextScrean(username); // 로그인 성공 시 메인 화면으로 이동
                } else {
                    JOptionPane.showMessageDialog(null, "로그인 실패!");
                }
            }
        });

        JButton backButton = new JButton("돌아가기");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                new MainManager(); // Open MainManager
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        pane.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // 화면 중앙 생성
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}
