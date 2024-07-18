package dao;

import common.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDao {
    private Connection conn;

    public MovieDao() {
        conn = DBConnect.getConnection();
        // 여기서 conn이 null인지 체크하는 로직이 필요합니다.
        if (conn == null) {
            System.out.println("DB 연결 실패...");
        }
    }

    public boolean checkDBConnection(){
        if(conn!= null){
            try{
                // 간단한 쿼리 실행을 통해 DB 연결 상태 확인
                PreparedStatement ps = conn.prepareStatement("select 1");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    System.out.println("DB 연결 상태 확인 : 연결 성공");
                    return true;
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        System.out.println("DB 연결 상태 확인 : 연결 실패...");
        return false;
    }

    public void close(){
        DBConnect.closeConnnection();
    }
}
