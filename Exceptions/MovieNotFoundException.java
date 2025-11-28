package Exceptions;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(String msg) {
        super(msg);
    }
}
