package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect {

    private static final String CONFIG_FILE = "./src/config/config.properties";
    private static Properties prop;
    private static Connection con = null;
    private static String url;
    private static String username;
    private static String password;

    // 정적 초기화 블록에서 config.properties 파일을 읽어와서 변수에 할당
    static {
        prop = new Properties();
        try {
            File file = new File(CONFIG_FILE);
            System.out.println("Config file exists: " + file.exists());
            System.out.println("Config file path: " + file.getAbsolutePath());

            try (InputStream input = new FileInputStream(file)) {
                prop.load(input);
                url = prop.getProperty("db.url");
                username = prop.getProperty("db.username");
                password = prop.getProperty("db.password");

                System.out.println("Loaded URL: " + url);
                System.out.println("Loaded Username: " + username);
                System.out.println("Loaded Password: " + password);

                if (url == null || username == null || password == null) {
                    throw new IllegalStateException("One or more database properties are null");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Failed to load config.properties: " + ex.getMessage());
        }
    }

    // DB 연결을 가져오는 메서드
    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                // JDBC 드라이버 로드
                Class.forName("com.mysql.cj.jdbc.Driver");
                // DB 연결
                con = DriverManager.getConnection(url, username, password);
                System.out.println("DB 연결 성공!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("DB 연결 실패...");
        }
        return con;
    }

    // DB 연결을 종료하는 메서드
    public static void closeConnnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("DB 연결 종료");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
