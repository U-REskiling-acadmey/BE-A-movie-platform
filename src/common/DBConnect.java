package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect {

    private static final String PROP_FILE = "config/config.properties";
    private static Properties prop;

    static {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream(PROP_FILE)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Mysql 정보
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = prop.getProperty("db_url");
    static final String USER = prop.getProperty("db_user");
    static final String PASS = prop.getProperty("db_password");

    // 데이터베이스 연결 객체 반환 메서드
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
