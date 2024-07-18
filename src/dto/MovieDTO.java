package dto;

public class MovieDTO {
    private int movieId;
    private String title;
    private int ageLimit;
    private int runtime;

    public MovieDTO(int movieId, String title, int ageLimit, int runtime) {
        this.movieId = movieId;
        this.title = title;
        this.ageLimit = ageLimit;
        this.runtime = runtime;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
}
