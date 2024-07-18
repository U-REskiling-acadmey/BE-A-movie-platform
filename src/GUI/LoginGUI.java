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
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(3, 2));

        // 사용자명 입력 필드
        pane.add(new JLabel("Username :"));
        usernameField = new JTextField();
        pane.add(usernameField);

        // 비밀번호 입력 필드
        pane.add(new JLabel("Password :"));
        passwordField = new JPasswordField();
        pane.add(passwordField);

        // 로그인 버튼
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
        pane.add(loginButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}
