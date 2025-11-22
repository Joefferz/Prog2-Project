public class Movie {
    private int movieID;
    private String movieName;
    private boolean rentable;
    Movie(int movID, String movName) {
        this.movieID = movID;
        this.movieName = movName;
        this.rentable = true;
    }
    public int getMovieID() {
        return movieID;
    }
    public String getMovieName() {
        return movieName;
    }
    public String isRentable() {
        if (this.rentable) {
            return "Available";
        } else {
            return "Rented";
        }
    }
    public void updateAvailability(){
        if (this.rentable) {
            this.rentable = false;
        }
        else {
            this.rentable = true;
        }
    }
    public void show() {
        System.out.println("Movie ID: " + movieID + "\nMovie Name: " + movieName + "\nStatus: " + isRentable());
    }
}
