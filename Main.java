import java.util.*;

class Movie {
    int movieID;
    String movieName;
    Movie(int movID, String movName) {
        this.movieID = movID;
        this.movieName = movName;
    }
}

abstract class Person {
    String name;
    int customerID;
    String membership;
    Person(String name, int customerID, String membership) {
        this.name = name;
        this.customerID = customerID;
        this.membership = membership;
    }
}

class Student extends Person {
    String name;
    int customerID;
    String membership;
    String schoolName;
    int grade;
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
    String name;
    int customerID;
    String membership;
    String job;
    String organization;
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
    int customerID;
    int movieID;
    boolean rentable = true;
    String dateBorrowed;
    String dateReturned;
    Rental(int customerID, int movieID) {
        this.customerID = customerID;
        this.movieID = movieID;
    }
}

interface Payment {
    public final double STUDENT_FEE = 5;
    public final double EXTERNAL_MEMBER_FEE = 10;
}