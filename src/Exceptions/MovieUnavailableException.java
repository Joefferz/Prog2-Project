package Exceptions;

public class MovieUnavailableException extends RuntimeException {
    public MovieUnavailableException(String msg) {
        super(msg);
    }
}
