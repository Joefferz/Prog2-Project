import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Rental implements Payment {
    private String customerRenterID;
    private String movieRentedID;
    private LocalDate dateBorrowed;
    private LocalDate dateReturned;
    private double fee;

    Rental(String customerID, String movieID, LocalDate dateBorrowed) {
        this.customerRenterID = customerID;
        this.movieRentedID = movieID;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = null;
    }
    public String getCustomerRenterID() {
        return customerRenterID;
    }
    public String getMovieRentedID() {
        return customerRenterID;
    }
    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }
    public int getNightsRented() {
        LocalDate borrowDate = LocalDate.parse(dateBorrowed.toString());
        LocalDate returnDate = LocalDate.parse(dateReturned.toString());
        long daysDifference = ChronoUnit.DAYS.between(borrowDate, returnDate);
        return Math.toIntExact(Math.abs(daysDifference));
    }
    public void details() {
        System.out.println("Customer ID: " + customerRenterID);
        System.out.println("Movie rented ID: " + movieRentedID);
        System.out.println("Borrowed on: " + dateBorrowed.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }
    public void fullDetails() {
        System.out.println("Customer ID: " + customerRenterID);
        System.out.println("Movie rented ID: " + movieRentedID);
        System.out.println("Borrowed on: " + dateBorrowed.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        if (dateReturned == null) {
            System.out.println("Not returned.");
        } else {
            System.out.println("Returned on: " + dateReturned.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }
    }
    public double calculate(String membership) {
        if (membership.equalsIgnoreCase("student")) {
            this.fee = getNightsRented() - 7 + STUDENT_FEE;
        } else {
            this.fee = getNightsRented() - 7 + EXTERNAL_MEMBER_FEE;
        }
        return this.fee;
    }
}