import java.util.*;

class Movie {
    private int movieID;
    private String movieName;
    private boolean rentable;
    Movie(int movID, String movName) {
        this.movieID = movID;
        this.movieName = movName;
        this.rentable = true;
    }
    public int getMovieID() {
        return movieID;
    }
    public String getMovieName() {
        return movieName;
    }
    public String isRentable() {
        if (this.rentable) {
            return "Available";
        } else {
            return "Rented";
        }
    }
    public void updateAvailability(){
        if (this.rentable) {
            this.rentable = false;
        }
        else {
            this.rentable = true;
        }
    }
    public void show() {
        System.out.println("Movie ID: " + movieID + "\nMovie Name: " + movieName + "\nStatus: " + isRentable());
    }
}

abstract class Person {
    protected String name;
    protected int customerID;
    protected String membership;
    Person(String name, int customerID, String membership) {
        this.name = name;
        this.customerID = customerID;
        this.membership = membership;
    }
    public String toString() {
        return "Name: " + name + "\nID: " + customerID + "\nMembership: " + membership;
    }
    abstract void personalInfo();
    public String getName() {
        return name;
    }
    public int getCustomerID() {
        return customerID;
    }
    public String getMembership() {
        return membership;
    }
}

class Student extends Person {
    private String name;
    private int customerID;
    private String membership;
    private String schoolName;
    private int grade;
    Student(String name, int customerID, String membership, String schoolName, int grade) {
        super(name, customerID, membership);
        this.name = name;
        this.customerID = customerID;
        this.membership = membership;
        this.schoolName = schoolName;
        this.grade = grade;
    }
    public String getName() {
        return super.getName();
    }
    public int getCustomerID() {
        return super.getCustomerID();
    }
    public String getMembership() {
        return super.getMembership();
    }
    public String getSchoolName() {
        return schoolName;
    }
    public int getGrade() {
        return grade;
    }
    public String toString(){
        return super.toString();
    }
    public void personalInfo(){
        System.out.println("School Name: " + schoolName + "\nGrade: " + grade);
    }
}

class ExternalMember extends Person {
    private String name;
    private int customerID;
    private String membership;
    private String job;
    private String organization;
    ExternalMember(String name, int customerID, String membership, String job, String organization) {
        super(name, customerID, membership);
        this.name = name;
        this.customerID = customerID;
        this.membership = membership;
        this.job = job;
        this.organization = organization;
    }
    public String getName() {
        return super.getName();
    }
    public int getCustomerID() {
        return super.getCustomerID();
    }
    public String getMembership() {
        return super.getMembership();
    }
    public String getJob() {
        return job;
    }
    public String getOrganization() {
        return organization;
    }
    public String toString(){
        return super.toString();
    }
    public void personalInfo(){
        System.out.println("Job: " + job + "\nOrganization's name: " + organization);
    }
}

class Rental {
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

interface Payment {
    public final double STUDENT_FEE = 5;
    public final double EXTERNAL_MEMBER_FEE = 10;
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> st = new ArrayList<>();
        ArrayList<ExternalMember> em = new ArrayList<>();
        ArrayList<Movie> mov = new ArrayList<>();
        ArrayList<Rental> r = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("\n1. Add member");
            System.out.println("2. Add movie");
            System.out.println("3. Show students");
            System.out.println("4. Show external members");
            System.out.println("5. Show movies");
            System.out.println("6. Rent movie");
            System.out.println("7. Return a movie");
            System.out.println("8. Exit");
            System.out.print("What would you like to do? (Enter your choice): ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter membership (Enter 'Student' or 'External'): ");
                    String membership = input.next();
                    if(membership.equalsIgnoreCase("student")){
                        System.out.print("Enter student's customer ID: ");
                        int studentID = input.nextInt();
                        System.out.print("Enter student's name: ");
                        String studentName = input.next();
                        System.out.print("Enter student's school name: ");
                        String schoolName = input.next();
                        System.out.print("Enter student's grade: ");
                        int grade = input.nextInt();
                        Student stu = new Student(studentName, studentID, membership, schoolName, grade);
                        st.add(stu);
                    }
                    else if(membership.equalsIgnoreCase("external")){
                        System.out.print("Enter external's customer ID: ");
                        int externalID = input.nextInt();
                        System.out.print("Enter member's name: ");
                        String externalName = input.next();
                        System.out.print("Enter member's job: ");
                        String job = input.next();
                        System.out.print("Enter member's organization name: ");
                        String organization = input.next();
                        ExternalMember exMem = new ExternalMember(externalName, externalID, membership, job, organization);
                        em.add(exMem);
                    }
                    else{
                        System.out.println("Invalid membership");
                    }
                    System.out.println("Customer successfully added.");
                    break;
                case 2:
                    System.out.print("Enter movie name: ");
                    String movieName = input.next();
                    System.out.print("Enter movie ID:");
                    int movieID = input.nextInt();
                    Movie movie = new Movie(movieID, movieName);
                    mov.add(movie);
                    System.out.println("Movie successfully added.");
                    break;
                case 3:
                    System.out.println("List of students:");
                    if(st.isEmpty()){
                        System.out.println("\nNo list of students found.");
                        break;
                    }else {
                        for (Student s : st) {
                            System.out.println(s.toString());
                        }
                    }
                    System.out.print("\nWould you like to check a student's personal info?(yes/no): ");
                    String studentPINFO = input.next();
                    if(studentPINFO.equalsIgnoreCase("yes")){
                        System.out.print("Enter student's id: ");
                        int stID = input.nextInt();
                        for(Student s: st){
                            if(s.getCustomerID() == stID){
                                s.personalInfo();
                            }
                        }
                    }
                    else if(studentPINFO.equalsIgnoreCase("no")){
                        break;
                    }
                    else {
                        System.out.println("Invalid input");
                    }
                    break;
                case 4:
                    System.out.println("List of external members: ");
                    if(em.isEmpty()){
                        System.out.println("No list of external members found.");
                        break;
                    }
                    else {
                        for (ExternalMember ext : em) {
                            System.out.println(ext.toString());
                        }
                    }
                    System.out.print("\nWould you like to check a member's personal info?(yes/no): ");
                    String externalPINFO = input.next();
                    if(externalPINFO.equalsIgnoreCase("yes")){
                        System.out.print("Enter member's id: ");
                        int extID = input.nextInt();
                        for(ExternalMember ext: em){
                            if(ext.getCustomerID() == extID){
                                ext.personalInfo();
                            }
                        }
                    }
                    else if(externalPINFO.equalsIgnoreCase("no")){
                        break;
                    }
                    else {
                        System.out.println("Invalid input");
                    }
                    break;
                case 5:
                    System.out.println("List of movies: ");
                    for(Movie m : mov){
                        m.show();
                    }
                    break;
                case 6:
                    System.out.println("Start a customer's rent: ");
                    System.out.println("Which customer wants to rent? (Enter 'Student' or 'External' member): ");
                    String memChoice = input.next();
                    if(memChoice.equalsIgnoreCase("student")){
                        for(Student stu : st){
                            System.out.println(stu.toString());
                        }
                    }
                    else if (memChoice.equalsIgnoreCase("external")){
                        for(ExternalMember ext : em){
                            System.out.println(ext.toString());
                        }
                    }
                    else {
                        System.out.println("Invalid membership.");
                    }
                    System.out.print("Enter customer's ID: ");
                    int cRentID = input.nextInt();
                    System.out.println("Which movie does the customer want to rent? (Enter movie ID)");
                    for(Movie m : mov){
                        m.show();
                    }
                    System.out.print("Enter movie's ID: ");
                    int movID = input.nextInt();
                    Rental rent = new Rental(cRentID, movID);
                    r.add(rent);
                    for(Movie m : mov){
                        if(m.getMovieID() == movID){
                            m.updateAvailability();
                        }
                    }
                    rent.details();
                    break;
                case 7:
                    System.out.println("Start a customer's return");
                    System.out.println("List of rentals: ");
                    for(Rental rental : r){
                        rental.details();
                    }
                    System.out.println("Select customer ID: ");
                    int cID = input.nextInt();
                    System.out.println("Customer's rental details: ");
                    for(Rental rents : r){
                        if (rents.getCustomerRenterID() == cID){
                            rents.details();
                        }
                    }
                    System.out.println("Select movie ID: ");
                    int mID = input.nextInt();
                    for(Movie m : mov){
                        if(m.getMovieID() == mID){
                            m.updateAvailability();
                        }
                    }
                    System.out.println("Movie successfully returned.");
                    break;
                case 8:
                    System.out.println("Exiting system...");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}