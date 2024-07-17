package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    // Mysql 정보
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/moviedb";
    static final String USER = "root"; // MySQL 사용자 이름
    static final String PASS = ""; // MySQL 비밀번호

    // 데이터베ㅣ스 연결 객체 반환 메서드
    public static Connection getConnection() throws SQLException , ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL,USER,PASS);
    }
}
