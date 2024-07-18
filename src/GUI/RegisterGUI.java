package GUI;

import Main.MainManager;
import dao.UserDAO;
import dto.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField ageField;

    public RegisterGUI() {
        setTitle("회원가입");
        setSize(300, 200); // Increased size to accommodate the new button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        // 사용자명 입력 필드
        inputPanel.add(new JLabel("Username :"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        // 비밀번호 입력 필드
        inputPanel.add(new JLabel("Password :"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        // 비밀번호 확인 입력 필드
        inputPanel.add(new JLabel("Confirm Password :"));
        confirmPasswordField = new JPasswordField();
        inputPanel.add(confirmPasswordField);

        // 나이 입력 필드
        inputPanel.add(new JLabel("Age :"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        pane.add(inputPanel, BorderLayout.CENTER);

        // 회원가입 버튼과 돌아가기 버튼을 담을 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton registerButton = new JButton("회원가입");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String ageText = ageField.getText();

                // 나이 필드 유효성 검사
                int age;
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "나이는 숫자로 입력해야 합니다!");
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!");
                    return;
                }

                UserDTO user = new UserDTO();
                user.setUsername(username);
                user.setPassword(password);
                user.setAge(age);

                UserDAO userDao = new UserDAO();
                if (userDao.register(user)) {
                    JOptionPane.showMessageDialog(null, "회원가입 성공!");
                    dispose();
                    new LoginGUI(); // 회원가입 성공 시 로그인 창으로 이동
                } else {
                    JOptionPane.showMessageDialog(null, "회원가입 실패!");
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

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        pane.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // 화면 중앙 생성
        setVisible(true);
    }

    public static void main(String[] args) {
        new RegisterGUI();
    }
}
