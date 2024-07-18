package dao;

import common.DBConnect;
import dto.TheaterDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static common.DBConnect.getConnection;

public class TheaterDAO {
    private Connection conn;

    public TheaterDAO() {
        conn = getConnection();
    }

    public List<TheaterDTO> getTheatersForMovie(int movieId, String showDate) {
        List<TheaterDTO> theaters = new ArrayList<>();
        String sql = "SELECT s.id, p.name, s.movie_id, s.start_date, s.start_time, " +
                "(SELECT COUNT(*) FROM reserve r WHERE r.movie_id = s.movie_id AND r.place_id = s.place_id AND r.reserve_date = ?) AS reserved_seats " +
                "FROM screen s " +
                "JOIN place p ON s.place_id = p.id " +
                "WHERE s.movie_id = ? AND ? BETWEEN s.start_date AND s.end_date";

        System.out.println("Attempting to fetch theaters for movie ID: " + movieId + " and date: " + showDate);

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, showDate);
            pstmt.setInt(2, movieId);
            pstmt.setString(3, showDate);
            System.out.println("Executing SQL: " + pstmt.toString());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TheaterDTO theater = new TheaterDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("movie_id"),
                            rs.getString("start_date"),
                            rs.getString("start_time"),
                            100, // 임의의 총 좌석 수
                            100 - rs.getInt("reserved_seats") // 남은 좌석 수 계산
                    );
                    theaters.add(theater);
                    System.out.println("Added theater: " + theater);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }

        System.out.println("Total theaters found: " + theaters.size());
        return theaters;
    }

    public boolean updateRemainingSeats(int theaterId, int newRemainingSeats) {
        // 이 메서드는 현재 데이터베이스 구조에서는 사용하지 않습니다.
        // 대신 예약 정보를 reserve 테이블에 추가하는 메서드를 구현해야 합니다.
        return false;
    }

    public void close() {
        DBConnect.closeConnnection();
    }
}