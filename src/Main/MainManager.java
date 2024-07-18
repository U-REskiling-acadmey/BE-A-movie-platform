package Main;

import GUI.LoginGUI;
import GUI.RegisterGUI;
import color.UserColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainManager 클래스는 애플리케이션의 메인 화면을 설정합니다.
 * 여기서 사용자는 회원가입 또는 로그인을 선택할 수 있습니다.
 */
public class MainManager extends JFrame{
    // Frame은 자바 GUI의 모든 구성요소를 담는 컨테이너로 우리가 흔히 보는 프로그램 창 자체를 의미합니다.
    public MainManager() {
        setTitle("좌석 예약 프로젝트"); // 프레임 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 프레임 초기 크기 설정
        setSize(600, 500); // 화면 크기 변경

        // 프로그램 제목창 아래의 컨텡이너 공간을 컨텐트 팬이라 하며 버튼과 같은 컴포넌트들이 부착합니다.
        Container pane = getContentPane(); // 프레임에서 컨텐트팬 받아오기

        // 레이아웃은 컨테이너 내의 배치 관리방법입니다. FlowLayout, BorderLayout, GridLayout, AbsoluteLayout 등 다양한 배칙 방법이 있습니다/
        // FlowLayout : 컴포넌트를 왼쪽에서 오른쪽으로, 위에서 아래 순서대로 배치합니다.
        // BoardLayout : 컨테이너를 다섯 개의 구역으로 나눠서 배치합니다. [ 북(North) , 남(South), 동(East), 서(West), 중앙(Center)]
        // GridLayout : 컴포넌트를 격자 형태로 배치합니다.
        // AbsoluteLayout : 컴포넌트를 어느 위치에든 좌표를 정해서 붙일 수 있습니다. [AbsoluteLayout 설정 contentPane.setLayout(null)]
        pane.setLayout(new BorderLayout());

        // 이미지와 레이블을 포함하는 패널 설정
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(Color.WHITE);

        // 메인 레이블 설정
        JLabel label = new JLabel("!주의사항!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(UserColor.CALENDAR_RED);// 주의사항 텍스트 색상 설정
        topPanel.add(label);

        // topPanel을 프레임의 북쪽에 추가
        pane.add(topPanel, BorderLayout.NORTH);

        // 주의사항 설명 추가
        JTextArea cautionArea = new JTextArea();
        cautionArea.setEditable(false);
        cautionArea.setLineWrap(true);
        cautionArea.setWrapStyleWord(true);
        cautionArea.setBackground(Color.WHITE);  // 배경색을 흰색으로 설정
        cautionArea.setFont(new Font("Dialog", Font.PLAIN, 12));
        cautionArea.setForeground(Color.BLACK);  // 글자색을 검은색으로 설정
        cautionArea.setPreferredSize(new Dimension(500, 100));  // 크기 설정
        cautionArea.setText("1. 가입을 하지 않았다면 로그인이 되지 않습니다.\n"
                + "2. 현 프로젝트는 비밀번호 초기화 기능이 아직 없습니다. 비밀번호를 잘 기억해주세요.\n"
                + "3. 프로젝트의 목적 : 영화 표를 예매하는 기능을 제공하여 사용자가 관리할 수 있는 시스템 구축\n"
                + "4. 기술 스택 : Java, Swing GUI, Mysql"
        );

        JScrollPane scrollPane = new JScrollPane(cautionArea);
        scrollPane.setBorder(new EmptyBorder(40, 20, 20, 20));

        pane.add(scrollPane, BorderLayout.CENTER);

        // 버튼 패널 설정
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // 회원가입 버튼 설정
        JButton registerButton = new JButton("회원가입");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 메인 화면 닫기
                new RegisterGUI(); // 회원가입 화면 열기
            }
        });

        // 로그인 버튼 설정
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 메인 화면 닫기
                new LoginGUI(); // 로그인 화면 열기
            }
        });

        // 버튼 패널에 버튼 추가
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        // 버튼 패널을 프레임의 남쪽에 추가
        buttonPanel.setPreferredSize(new Dimension(500, 50));

        pane.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // 화면 중앙 생성
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainManager();
            }
        });
    }
}