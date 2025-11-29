package Exceptions;

public class MovieAlreadyExistsException extends Exception {
    public MovieAlreadyExistsException(String msg) {
        super(msg);
    }
}