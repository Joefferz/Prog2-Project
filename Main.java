import java.util.*;

class Movie {
    private int movieID;
    private String movieName;
    Movie(int movID, String movName) {
        this.movieID = movID;
        this.movieName = movName;
    }
    public int getMovieID() {
        return movieID;
    }
    public String getMovieName() {
        return movieName;
    }
    public void show(){
        System.out.println("Movie ID: " + movieID);
        System.out.println("Movie Name: " + movieName);
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
    private int customerID;
    private int movieID;
    private boolean rentable = true;
    private String dateBorrowed;
    private String dateReturned;
    Rental(int customerID, int movieID) {
        this.customerID = customerID;
        this.movieID = movieID;
    }
    public String getMovieAvailability(){
        if(rentable){
            return "Available";
        }
        else{
            return "Not Available";
        }
    }
    public boolean updateMovieAvailability() {
        if(dateBorrowed == null && dateReturned == null){
            return true;
        }
        else{
            return false;
        }
    }
}

interface Payment {
    public final double STUDENT_FEE = 5;
    public final double EXTERNAL_MEMBER_FEE = 10;
}