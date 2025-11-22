public class Rental {
    private int customerRenterID;
    private int movieRentedID;
    private String dateBorrowed;
    private String dateReturned;
    Rental(int customerID, int movieID) {
        this.customerRenterID = customerID;
        this.movieRentedID = movieID;
    }
    public int getCustomerRenterID() {
        return customerRenterID;
    }
    /*public int getNightsRented(){
        String[]str = dateBorrowed.split("-");
        int date1 = Integer.valueOf(str[1]);
        String[] str2 = dateReturned.split("-");
        int date2 = Integer.valueOf(str2[1]);
    }*/
    public void details(){
        System.out.println("Customer ID: " + customerRenterID);
        System.out.println("Movie rented ID: " + movieRentedID);
    }
}
