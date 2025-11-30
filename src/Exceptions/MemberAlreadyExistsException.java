package Exceptions;

/**
 * Exception thrown when attempting to add a member
 * whose customer ID already exists in the system.
 */
public class MemberAlreadyExistsException extends Exception {

    /**
     * Constructs a new MemberAlreadyExistsException with the specified detail message.
     *
     * @param msg the detail message explaining the exception
     */
    public MemberAlreadyExistsException(String msg) {
        super(msg);
    }

}

