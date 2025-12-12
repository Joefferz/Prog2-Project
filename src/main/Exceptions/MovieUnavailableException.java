package main.Exceptions;

/**
 * Exception thrown when attempting to rent a movie
 * that is currently unavailable (already rented out).
 */
public class MovieUnavailableException extends RuntimeException {

    /**
     * Constructs a new MovieUnavailableException with the specified detail message.
     *
     * @param msg the detail message explaining the exception
     */
    public MovieUnavailableException(String msg) {
        super(msg);
    }
}


