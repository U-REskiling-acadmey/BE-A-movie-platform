package dao;

import common.DBConnect;
import dto.SeatReservationDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeatReservationDAO {
    private Connection conn;

    public SeatReservationDAO() {
        conn = DBConnect.getConnection();
    }

    public boolean reserveSeats(SeatReservationDTO reservation) {
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

    public void close() {
        DBConnect.closeConnnection();
    }
}
