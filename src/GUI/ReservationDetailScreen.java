package GUI;

import Main.NextScreen;
import dao.ReservationDAO;
import dto.ReservationDTO;

import javax.swing.*;
import java.awt.*;

import static common.DBConnect.username;

public class ReservationDetailScreen extends JFrame {
    private ReservationDTO reservation;

    public ReservationDetailScreen(ReservationDTO reservation) {
        this.reservation = reservation;
        setTitle(String.valueOf(reservation.getMovieId()));
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("영화 제목:"));
        add(new JLabel(String.valueOf(reservation.getMovieId())));
        add(new JLabel("상영 일시:"));
        add(new JLabel(reservation.getReserveDate().toString() + " " + reservation.getReserveTime().toString()));
        add(new JLabel("예매 인원:"));
        add(new JLabel(String.valueOf(reservation.getReserveCnt())));
        add(new JLabel("좌석 번호:"));
        add(new JLabel(reservation.getSeat()));
        add(new JLabel("결제 금액:"));
        add(new JLabel(String.valueOf(reservation.getPrice()) + "원"));
        add(new JLabel("예매 일자:"));
        add(new JLabel(reservation.getInsDt().toString()));

        JButton cancelButton = new JButton("예매 취소");
        cancelButton.addActionListener(e -> cancelReservation());
        add(cancelButton);

        // 이전 버튼 설정
        JButton backButton = new JButton("이전으로");
        backButton.addActionListener(e -> goBack());
        add(backButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void goBack() {
        // Dispose the current frame and open MyReservationScreen
        dispose();
        new NextScreen(username);
    }

    private void cancelReservation() {
        int confirmation = JOptionPane.showConfirmDialog(this, "정말로 예매를 취소하시겠습니까?", "예매 취소", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            ReservationDAO reservationDAO = new ReservationDAO();
            boolean success = reservationDAO.cancelReservation(reservation.getId());
            if (success) {
                JOptionPane.showMessageDialog(this, "예매가 취소되었습니다.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "예매 취소에 실패했습니다.");
            }
        }
    }
}
