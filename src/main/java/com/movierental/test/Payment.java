package main.java.com.movierental.test;

/**
 * Represents a payment calculation system.
 * Implementing classes must define how the rental fee is calculated based on a customer's membership type.
 */
interface Payment {
    /** The rental fee charged to student members. */
    public final double STUDENT_FEE = 5;

    /** The rental fee charged to external members. */
    public final double EXTERNAL_MEMBER_FEE = 10;

    /**
     * Calculates the rental fee according to the given membership type.
     * Classes that implements this class must provide their own implementation of this method (override).
     *
     * @param membership the type of membership (Student/External)
     * @return the rental fee for that membership type
     */
    public abstract double calculate(String membership);
}