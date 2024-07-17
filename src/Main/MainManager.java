package Main;

import javax.swing.*;
import java.awt.*;

public class MainManager extends JFrame{
    // Frame은 자바 GUI의 모든 구성요소를 담는 컨테이너로 우리가 흔히 보는 프로그램 창 자체를 의미합니다.
    public MainManager() {
        setTitle("Frame Setting 500 x 300 프레임 만들기"); // 프레임 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300); // 프레임 크기 설정

        // 프로그램 제목창 아래의 컨텡이너 공간을 컨텐트 팬이라 하며 버튼과 같은 컴포넌트들이 부착합니다.
        Container pane = getContentPane(); // 프레임에서 컨텐트팬 받아오기

        // 레이아웃은 컨테이너 내의 배치 관리방법입니다. FlowLayout, BorderLayout, GridLayout, AbsoluteLayout 등 다양한 배칙 방법이 있습니다/
        // FlowLayout : 컴포넌트를 왼쪽에서 오른쪽으로, 위에서 아래 순서대로 배치합니다.
        // BoardLayout : 컨테이너를 다섯 개의 구역으로 나눠서 배치합니다. [ 북(North) , 남(South), 동(East), 서(West), 중앙(Center)]
        // GridLayout : 컴포넌트를 격자 형태로 배치합니다.
        // AbsoluteLayout : 컴포넌트를 어느 위치에든 좌표를 정해서 붙일 수 있습니다. [AbsoluteLayout 설정 contentPane.setLayout(null)]
        pane.setLayout(new BorderLayout());

        JLabel label = new JLabel("좌석 예약 프로젝트",SwingConstants.CENTER); // 레이블 생성
        label.setHorizontalAlignment(SwingConstants.CENTER); // 수평 가운데 정렬
        label.setVerticalAlignment(SwingConstants.CENTER); // 수직 가운데 정렬
        pane.add(label, BorderLayout.CENTER); // 중앙에 레이블 추가

        setVisible(true); // 화면에 프레임 출력

    }

    public static void main(String[] args) {
        MainManager frame = new MainManager();
    }
}
