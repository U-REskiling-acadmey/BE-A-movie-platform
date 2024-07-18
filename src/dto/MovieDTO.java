package dto;

public class MovieDTO {
    private int id;
    private String title;
    private int price;
    private int ageLimit;
    private int runningTime;

    public MovieDTO(int id,String title, int price, int ageLimit, int runningTime) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.ageLimit = ageLimit;
        this.runningTime = runningTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }
}
