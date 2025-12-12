package main.java.com.movierental.test;

/**
 * Represents a movie that can be rented.
 * Each movie has an ID, name, and a status.
 */
public class Movie {
    private String movieID;
    private String movieName;
    private boolean rentable;

    /**
     * Creates a new Movie object with the given ID and name.
     * Movies are rentable by default when created.
     *
     * @param movID   unique ID
     * @param movName title
     */
    Movie(String movID, String movName) {
        this.movieID = movID;
        this.movieName = movName;
        this.rentable = true;
    }

    /**
     * Returns the movie's ID.
     *
     * @return the movie ID
     */
    public String getMovieID() {
        return this.movieID;
    }

    /**
     * Returns the movie's title.
     *
     * @return the movie name
     */
    public String getMovieName() {
        return this.movieName;
    }

    /**
     * Returns the rental availability of the movie as a String.
     *
     * @return "Available" if rentable is true, "Unavailable" otherwise
     */
    public String isRentable() {
        return this.rentable ? "Available" : "Unavailable";
    }

    /**
     * Changes rentable status of the movie.
     * If available, it becomes unavailable; and vice versa.
     */
    public void updateAvailability(){
        this.rentable = !this.rentable;
    }

    /**
     * Prints the movie's details (ID, name, availability status).
     */
    public void show() {
        System.out.println("Movie ID: " + getMovieID() + "\nMovie Name: " + getMovieName() + "\nStatus: " + isRentable());
    }
}
