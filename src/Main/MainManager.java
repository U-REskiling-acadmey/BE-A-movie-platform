package Main;

import javax.swing.*;

public class MainManager extends JFrame{
    // Frame은 자바 GUI의 모든 구성요소를 담는 컨테이너로 우리가 흔히 보는 프로그램 창 자체를 의미합니다.
    public MainManager() {
        setTitle("Frame Setting 500 x 300 프레임 만들기"); // 프레임 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300); // 프레임 크기 설정

        setVisible(true); // 화면에 프레임 출력

    }

    public static void main(String[] args) {
        MainManager frame = new MainManager();
    }
}
