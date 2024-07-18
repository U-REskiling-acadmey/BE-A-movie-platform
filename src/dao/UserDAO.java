package dao;


import common.DBConnect;
import dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
  * UserDAO 클래스는 데이터베이스와 상호작용하여 사용자 데이터를 처리합니다.
 */
public class UserDAO {
    private Connection conn;

    public UserDAO() {
        conn = DBConnect.getConnection();
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

    // 사용자 이름 가져오기 메서드
    public String getUsername(String username) {
        String sql = "SELECT username FROM users WHERE username = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("username");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null; // 사용자가 없는 경우 null 반환
    }

    public void close(){
        DBConnect.closeConnnection();
    }
}

