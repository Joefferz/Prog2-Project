import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Rental {
    private int customerRenterID;
    private int movieRentedID;
    private LocalDate dateBorrowed;
    private LocalDate dateReturned;
    Rental(int customerID, int movieID, LocalDate dateBorrowed) {
        this.customerRenterID = customerID;
        this.movieRentedID = movieID;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = null;
    }
    public int getCustomerRenterID() {
        return customerRenterID;
    }
    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }
    public int getNightsRented(){
            LocalDate borrowDate = LocalDate.parse(dateBorrowed.toString());
            LocalDate returnDate = LocalDate.parse(dateReturned.toString());
            long daysDifference = ChronoUnit.DAYS.between(borrowDate, returnDate);
            return Math.toIntExact(Math.abs(daysDifference));
    }
    public void details(){
        System.out.println("Customer ID: " + customerRenterID);
        System.out.println("Movie rented ID: " + movieRentedID);
    }
    public void fullDetails(){
        System.out.println("Customer ID: " + customerRenterID);
        System.out.println("Movie rented ID: " + movieRentedID);
        System.out.println("Borrowed on: " + dateBorrowed);
        if(dateReturned == null){
            System.out.println("Not returned.");
        }
        else {
            System.out.println("Returned on: " + dateReturned);
        }
    }
}
