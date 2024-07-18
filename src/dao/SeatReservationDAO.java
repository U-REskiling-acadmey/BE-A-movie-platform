package dao;

import common.DBConnect;
import dto.SeatReservationDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatReservationDAO {
    private Connection conn;

    public SeatReservationDAO() {
        conn = DBConnect.getConnection();
    }

    public boolean reserveSeats(SeatReservationDTO reservation) {
        // 먼저 place_id의 유효성을 직접 검사
        if (!isValidPlaceId(reservation.getPlaceId())) {
            System.out.println("Invalid place_id: " + reservation.getPlaceId());
            return false;
        }

        String query = "INSERT INTO reserve (username, movie_id, place_id, reserve_date, reserve_time, reserve_cnt, seat, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, reservation.getUsername());
            preparedStatement.setInt(2, reservation.getMovieId());
            preparedStatement.setInt(3, reservation.getPlaceId());
            preparedStatement.setDate(4, java.sql.Date.valueOf(reservation.getReserveDate()));
            preparedStatement.setTime(5, java.sql.Time.valueOf(reservation.getReserveTime()));
            preparedStatement.setInt(6, reservation.getReserveCnt());
            preparedStatement.setString(7, reservation.getSeat());
            preparedStatement.setInt(8, reservation.getPrice());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean isValidPlaceId(int placeId) {
        String query = "SELECT COUNT(*) FROM place WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, placeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error checking place_id: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    public void close() {
        DBConnect.closeConnnection();
    }
}
