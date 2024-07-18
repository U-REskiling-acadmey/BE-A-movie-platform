package dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class ReservationDTO {
    private int id;
    private String username;
    private int movieId;
    private int placeId;
    private Date reserveDate;
    private Time reserveTime;
    private int reserveCnt;
    private String seat;
    private int price;
    private Timestamp insDt;

    public ReservationDTO(int id, String username, int movieId, int placeId, Date reserveDate, Time reserveTime, int reserveCnt, String seat, int price, Timestamp insDt) {
        this.id = id;
        this.username = username;
        this.movieId = movieId;
        this.placeId = placeId;
        this.reserveDate = reserveDate;
        this.reserveTime = reserveTime;
        this.reserveCnt = reserveCnt;
        this.seat = seat;
        this.price = price;
        this.insDt = insDt;
    }

    // Getters and setters for each field

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

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Time getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Time reserveTime) {
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

    public Timestamp getInsDt() {
        return insDt;
    }

    public void setInsDt(Timestamp insDt) {
        this.insDt = insDt;
    }
    // toString() method to display in JList

    @Override
    public String toString() {
        return reserveDate + " " + reserveTime + " - " + seat;
    }
}
