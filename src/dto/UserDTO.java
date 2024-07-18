package dto;

/*
    UserDTO 클래스는 사용자 정보를 캡슐화하여 데이터 전송을 돕는 역할을 합니다.
 */
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private int age;

    // 기본 생성자
    public UserDTO() {}

    // 매개변수가 있는 생성자
    public UserDTO(int id, String username, String password, int age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    // getter & setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 패스워드 확인 메서드
    public boolean isPasswordMatching(String confirmPassword) {
        return this.password.equals(confirmPassword);
    }
}
