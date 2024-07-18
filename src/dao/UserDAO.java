package dao;


import common.DBConnect;
import dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static common.DBConnect.getConnection;

/*
  * UserDAO 클래스는 데이터베이스와 상호작용하여 사용자 데이터를 처리합니다.
 */
public class UserDAO {
    private Connection conn;

    public UserDAO() {
        conn = getConnection();
    }

    // 회원가입 메서드
    public boolean register(UserDTO user) {
        String query = "INSERT INTO users (username, password, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 로그인 메서드
    public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사용자 프로필 가져오기 메서드
    public UserDTO getUserByUsername(String username) {
        UserDTO user = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        System.out.println("Attempting to fetch user: " + username); // 디버깅용 로그

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            System.out.println("Executing SQL: " + pstmt.toString()); // SQL 쿼리 로깅

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("User found in database"); // 사용자 찾음 로그
                    user = new UserDTO(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("age")
                    );
                    System.out.println("User details: " + user); // 생성된 객체 정보 출력
                } else {
                    System.out.println("No user found with username: " + username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }

        return user;
    }




    public void close(){
        DBConnect.closeConnnection();
    }
}

