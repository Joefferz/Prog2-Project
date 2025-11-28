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
        return movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public String isRentable() {
        return rentable ? "Available" : "Unavailable";
    }

    public void updateAvailability(){
        this.rentable = !this.rentable;
    }

    public void show() {
        System.out.println("Movie ID: " + movieID + "\nMovie Name: " + movieName + "\nStatus: " + isRentable());
    }
}
