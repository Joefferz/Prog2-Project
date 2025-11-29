package Exceptions;

public class MemberAlreadyExistsException extends Exception {
    public MemberAlreadyExistsException(String msg) {
        super(msg);
    }
}