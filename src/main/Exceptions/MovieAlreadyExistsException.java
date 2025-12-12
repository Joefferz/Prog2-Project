package main.Exceptions;

/**
 * Exception thrown when attempting to add a movie
 * whose ID already exists in the system.
 */
public class MovieAlreadyExistsException extends Exception {

    /**
     * Constructs a new MovieAlreadyExistsException with the specified detail message.
     *
     * @param msg the detail message explaining the exception
     */
    public MovieAlreadyExistsException(String msg) {
        super(msg);
    }

}

