package Main;

import dao.MovieDao;

import javax.swing.*;

public class NextScrean extends JFrame {
    private MovieDao dao;

    public NextScrean() {
        setTitle("Next Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        JLabel nextLabel = new JLabel("This is the next screen", SwingConstants.CENTER);
        getContentPane().add(nextLabel);

        //DAO 객체 생성
        dao = new MovieDao();

        //DB 연결 상태 확인
        boolean isConnected = dao.checkDBConnection();

        setVisible(true);
    }

    // 프레임이 닫힐 때 DB 연결을 닫음
    @Override
    public void dispose(){
        super.dispose();
        dao.close();
    }
}
