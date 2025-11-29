public class Movie {
    private String movieID;
    private String movieName;
    private boolean rentable;

    Movie(String movID, String movName) {
        this.movieID = movID;
        this.movieName = movName;
        this.rentable = true;
    }

    public String getMovieID() {
        return this.movieID;
    }

    public String getMovieName() {
        return this.movieName;
    }

    public String isRentable() {
        return this.rentable ? "Available" : "Unavailable";
    }

    public void updateAvailability(){
        this.rentable = !this.rentable;
    }

    public void show() {
        System.out.println("Movie ID: " + getMovieID() + "\nMovie Name: " + getMovieName() + "\nStatus: " + isRentable());
    }
}
