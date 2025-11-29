import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

/**
 * Represents a movie rental by a customer.
 * Stores borrowing and returning dates and the IDs of both the customer
 * and the rented movie, and provides methods to calculate fees.
 *
 * <p>This class implements the {@link Payment} interface.</p>
 */
public class Rental implements Payment {
    private String customerRenterID;
    private String movieRentedID;
    private LocalDate dateBorrowed;
    private LocalDate dateReturned;

    /**
     * Constructs a Rental object containing rental details.
     * Movies are not returned by default.
     *
     * @param customerID   the ID of the customer renting the movie
     * @param movieID      the ID of the rented movie
     * @param dateBorrowed the date the movie was borrowed
     */
    Rental(String customerID, String movieID, LocalDate dateBorrowed) {
        this.customerRenterID = customerID;
        this.movieRentedID = movieID;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = null;
    }

    /**
     * Returns the customer ID of the renter.
     *
     * @return the customer renter ID
     */
    public String getCustomerRenterID() {
        return customerRenterID;
    }

    /**
     * Returns the ID of the rented movie.
     *
     * @return the movie ID
     */
    public String getMovieRentedID() {
        return this.movieRentedID;
    }

    /**
     * Sets the return date for the rental.
     *
     * @param dateReturned the date the movie was returned
     */
    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    /**
     * Returns the date the movie was borrowed.
     *
     * @return the borrow date
     */
    public LocalDate getDateBorrowed() {
        return this.dateBorrowed;
    }

    /**
     * Returns the date the movie was returned.
     * Is null if the movie has not yet been returned.
     *
     * @return the return date or null
     */
    public LocalDate getDateReturned() {
        return this.dateReturned;
    }

    /**
     * Calculates how many nights the movie has been rented for.
     * Requires that the movie has already been returned.
     *
     * @return the number of nights between borrowed and returned dates
     * @throws IllegalStateException if the return date is before the borrow date
     */
    public int getNightsRented() {
        if (dateReturned.isBefore(dateBorrowed)) {
            throw new IllegalStateException("Return date cannot be before borrow date.");
        }

        long daysDifference = ChronoUnit.DAYS.between(dateBorrowed, dateReturned);
        return Math.toIntExact(daysDifference);
    }

    /**
     * Prints detailed information about the rental,
     * including borrow and return dates (if returned or not).
     */
    public void details() {
        System.out.println("Customer ID: " + customerRenterID);
        System.out.println("Movie rented ID: " + movieRentedID);
        System.out.println("Borrowed on: " + dateBorrowed.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        if (dateReturned == null) {
            System.out.println("Not returned.");
        }
        else
        {
            System.out.println("Returned on: " + dateReturned.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }
    }

    /**
     * Calculates the rental fee based on membership type and duration.
     * The first 7 nights are included in the base fee.
     * Additional nights charges extra.
     *
     * <p>Student: base fee = $5, +$1 per extra night.<br>
     * External: base fee = $10, +$2 per extra night.</p>
     *
     * @param membership the type of membership (Student/External)
     * @return the calculated rental fee
     */
    @Override
    public double calculate(String membership) {
        double fee;
        int nights = getNightsRented();
        int extraNights = Math.max(0, nights - 7);

        if (membership.equalsIgnoreCase("student")) {
            fee = STUDENT_FEE + (extraNights);
        }
        else
        {
            fee = EXTERNAL_MEMBER_FEE + (extraNights * 2);
        }

        return fee;
    }
}