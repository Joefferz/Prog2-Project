package Exceptions;

/**
 * Exception thrown when attempting to rent a movie
 * that is currently unavailable (already rented out).
 */
public class MovieNotFoundException extends RuntimeException {

    /**
     * Constructs a new MovieUnavailableException with the specified detail message.
     *
     * @param message the detail message explaining the exception
     */
    public MovieNotFoundException(String msg) {
        super(msg);
    }
}

