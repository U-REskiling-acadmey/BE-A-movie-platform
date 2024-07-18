package GUI;

import Main.NextScreen;
import dao.ReservationDAO;
import dto.ReservationDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MyReservationScreen extends JFrame {
    private String username;
    private JList<ReservationDTO> reservationList;

    public MyReservationScreen(String username) {
        this.username = username;
        setTitle("예매 확인");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 예매 목록을 가져와서 JList에 표시
        ReservationDAO reservationDAO = new ReservationDAO();
        List<ReservationDTO> reservations = reservationDAO.getReservationsByUsername(username);

        reservationList = new JList<>(new DefaultListModel<>());
        DefaultListModel<ReservationDTO> listModel = (DefaultListModel<ReservationDTO>) reservationList.getModel();
        for (ReservationDTO reservation : reservations) {
            listModel.addElement(reservation);
        }

        reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reservationList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    ReservationDTO selectedReservation = reservationList.getSelectedValue();
                    new ReservationDetailScreen(selectedReservation);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(reservationList);
        add(scrollPane, BorderLayout.CENTER);

        // Back button 설정
        JButton backButton = new JButton("이전으로");
        backButton.addActionListener(e -> goBack());
        add(backButton, BorderLayout.SOUTH); // 위치를 남쪽(SOUTH)으로 설정

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void goBack() {
        // Logic to go back to the previous screen
        dispose();
        new NextScreen(username);
    }
}
