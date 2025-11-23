public class Rental {
    private int customerRenterID;
    private int movieRentedID;
    private String dateBorrowed;
    private String dateReturned;
    Rental(int customerID, int movieID, String dateBorrowed) {
        this.customerRenterID = customerID;
        this.movieRentedID = movieID;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = null;
    }
    public int getCustomerRenterID() {
        return customerRenterID;
    }
    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
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
