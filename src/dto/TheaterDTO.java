package dto;

public class TheaterDTO {
    private int id;
    private String name;
    private int movieId;
    private String showDate;
    private String showTime;
    private int totalSeats;
    private int remainingSeats;

    // 생성자
    public TheaterDTO(int id, String name, int movieId, String showDate, String showTime, int totalSeats, int remainingSeats) {
        this.id = id;
        this.name = name;
        this.movieId = movieId;
        this.showDate = showDate;
        this.showTime = showTime;
        this.totalSeats = totalSeats;
        this.remainingSeats = remainingSeats;
    }

    // Getter 메서드
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getShowDate() {
        return showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    // Setter 메서드
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }
}