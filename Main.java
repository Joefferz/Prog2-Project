import java.util.*;

class Movie {
    private int movieID;
    private String movieName;
    Movie(int movID, String movName) {
        this.movieID = movID;
        this.movieName = movName;
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
}

interface Payment {
    public final double STUDENT_FEE = 5;
    public final double EXTERNAL_MEMBER_FEE = 10;
}