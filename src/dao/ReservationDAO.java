package dao;

import common.DBConnect;
import dto.ReservationDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection conn;

    public ReservationDAO() {
        conn = DBConnect.getConnection();
    }

    public List<ReservationDTO> getReservationsByUsername(String username) {
        List<ReservationDTO> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reserve WHERE username = ? AND delete_fg = 'N'";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    reservations.add(new ReservationDTO(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getInt("movie_id"),
                            rs.getInt("place_id"),
                            rs.getDate("reserve_date"),
                            rs.getTime("reserve_time"),
                            rs.getInt("reserve_cnt"),
                            rs.getString("seat"),
                            rs.getInt("price"),
                            rs.getTimestamp("ins_dt")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public boolean cancelReservation(int reservationId) {
        String sql = "UPDATE reserve SET delete_fg = 'Y', del_dt = NOW() WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
