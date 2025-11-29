import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Rental implements Payment {
    private String customerRenterID;
    private String movieRenterID;
    private LocalDate dateBorrowed;
    private LocalDate dateReturned;
    private double fee;

    Rental(String customerID, String movieID, LocalDate dateBorrowed) {
        this.customerRenterID = customerID;
        this.movieRenterID = movieID;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = null;
    }

    public String getCustomerRenterID() {
        return customerRenterID;
    }

    public String getMovieRentedID() {
        return this.movieRenterID;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public LocalDate getDateBorrowed() {
        return this.dateBorrowed;
    }

    public LocalDate getDateReturned() {
        return this.dateReturned;
    }

    public int getNightsRented() {
        if (dateReturned.isBefore(dateBorrowed)) {
            throw new IllegalStateException("Return date cannot be before borrow date.");
        }

        long daysDifference = ChronoUnit.DAYS.between(dateBorrowed, dateReturned);
        return Math.toIntExact(daysDifference);
    }

    public void details() {
        System.out.println("Customer ID: " + customerRenterID);
        System.out.println("Movie rented ID: " + movieRenterID);
        System.out.println("Borrowed on: " + dateBorrowed.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }

    public void fullDetails() {
        System.out.println("Customer ID: " + customerRenterID);
        System.out.println("Movie rented ID: " + movieRenterID);
        System.out.println("Borrowed on: " + dateBorrowed.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        if (dateReturned == null) {
            System.out.println("Not returned.");
        }
        else
        {
            System.out.println("Returned on: " + dateReturned.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }
    }

    public double calculate(String membership) {
        int nights = getNightsRented();
        int extraNights = Math.max(0, nights - 7);

        if (membership.equalsIgnoreCase("student")) {
            this.fee = 5 + (extraNights);
        }
        else
        {
            this.fee = 10 + (extraNights * 2);
        }

        return this.fee;
    }
}