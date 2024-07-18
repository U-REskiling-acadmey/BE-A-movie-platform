package dto;

public class SeatReservationDTO {
    private String username;
    private int movieId;
    private int placeId;
    private String reserveDate;
    private String reserveTime;
    private int reserveCnt;
    private String seat;
    private int price;

    public SeatReservationDTO(String username, int movieId, int placeId, String reserveDate, String reserveTime, int reserveCnt, String seat, int price) {
        this.username = username;
        this.movieId = movieId;
        this.placeId = placeId;
        this.reserveDate = reserveDate;
        this.reserveTime = reserveTime;
        this.reserveCnt = reserveCnt;
        this.seat = seat;
        this.price = price;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public int getReserveCnt() {
        return reserveCnt;
    }

    public void setReserveCnt(int reserveCnt) {
        this.reserveCnt = reserveCnt;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
