package dao;

import common.DBConnect;
import dto.MovieDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static common.DBConnect.getConnection;

public class MovieDAO {
    private Connection conn;

    public MovieDAO() {
        conn = getConnection();
    }

    public List<MovieDTO> getMoviesForDate(String date) {
        List<MovieDTO> movies = new ArrayList<>();
        String sql = "SELECT DISTINCT m.id, m.title, m.price, m.age_limit, m.running_time " +
                "FROM movie m " +
                "JOIN screen s ON m.id = s.movie_id " +
                "WHERE ? BETWEEN s.start_date AND s.end_date";

        System.out.println("Attempting to fetch movies for date: " + date); // 디버깅용 로그

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            System.out.println("Executing SQL: " + pstmt.toString()); // SQL 쿼리 로깅

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MovieDTO movie = new MovieDTO(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("price"),
                            rs.getInt("age_limit"),
                            rs.getInt("running_time")
                    );
                    movies.add(movie);
                    System.out.println("Added movie: " + movie);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }

        System.out.println("Total movies found: " + movies.size()); // 총 찾은 영화 수 로깅
        return movies;
    }

    public void close() {
        DBConnect.closeConnnection();
    }
}