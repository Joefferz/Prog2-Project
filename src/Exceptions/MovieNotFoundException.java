package Exceptions;

/**
 * Exception thrown when a movie with the specified ID
 * cannot be found in the system.
 */
public class MovieNotFoundException extends RuntimeException {

    /**
     * Constructs a new MovieUnavailableException with the specified detail message.
     *
     * @param msg the detail message explaining the exception
     */
    public MovieNotFoundException(String msg) {
        super(msg);
    }
}



