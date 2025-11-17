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
        else {
            return false;
        }
    }
    public int getNightsRented(){
        String[]str = dateBorrowed.split("-");
        int date1 = Integer.valueOf(str[1]);
        String[] str2 = dateReturned.split("-");
        int date2 = Integer.valueOf(str2[1]);
    }
}

interface Payment {
    public final double STUDENT_FEE = 5;
    public final double EXTERNAL_MEMBER_FEE = 10;
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> st = new ArrayList<>();
        ArrayList<ExternalMember> em = new ArrayList<>();
        ArrayList<Movie> mov = new ArrayList<>();
        ArrayList<Rental> r = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("1. Add member");
            System.out.println("2. Add movie");
            System.out.println("3. Show students");
            System.out.println("4. Show external members");
            System.out.println("5. Show movies");
            System.out.println("6. Rent movie");
            System.out.println("7. Return a movie");
            System.out.println("8. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter membership (Student or External): ");
                    String membership = sc.next();
                    if(membership.equalsIgnoreCase("student")){
                        System.out.print("Enter student's customer ID: ");
                        int studentID = sc.nextInt();
                        System.out.print("Enter student's name: ");
                        String studentName = sc.next();
                        System.out.print("Enter student's school name: ");
                        String schoolName = sc.next();
                        System.out.print("Enter student's grade: ");
                        int grade = sc.nextInt();
                        Student stu = new Student(studentName, studentID, membership, schoolName, grade);
                        st.add(stu);
                    }
                    else if(membership.equalsIgnoreCase("external")){
                        System.out.print("Enter external's customer ID: ");
                        int externalID = sc.nextInt();
                        System.out.print("Enter member's name: ");
                        String externalName = sc.next();
                        System.out.print("Enter member's job: ");
                        String job = sc.next();
                        System.out.print("Enter member's organization name: ");
                        String organization = sc.next();
                        ExternalMember exmem = new ExternalMember(externalName, externalID, membership, job, organization);
                        em.add(exmem);
                    }
                    else{
                        System.out.println("Invalid membership");
                    }
                    System.out.print("Customer successfully added.");
                    break;
                case 2:
                    System.out.print("Enter movie ID: ");
                    int movieID = sc.nextInt();
                    System.out.print("Enter movie name: ");
                    String movieName = sc.next();
                    Movie movie = new Movie(movieID, movieName);
                    mov.add(movie);
                    System.out.print("Movie successfully added.");
                    break;
                case 3:
                    System.out.println("List of students:");
                    for(Student s: st){
                        System.out.println(s.toString());
                    }
                    System.out.print("\nWould you like to check a student's personal info?(yes/no): ");
                    String check1 = sc.next();
                    if(check1.equalsIgnoreCase("yes")){
                        System.out.print("Enter student's id: ");
                        int stID = sc.nextInt();
                        for(Student s: st){
                            if(s.getCustomerID() == stID){
                                s.personalInfo();
                            }
                        }
                    }
                    else if(check1.equalsIgnoreCase("no")){
                        break;
                    }
                    else {
                        System.out.println("Invalid input");
                    }
                    break;
                case 4:
                    System.out.println("List of external members: ");
                    for(ExternalMember ext : em){
                        System.out.println(ext.toString());
                    }
                    System.out.print("\nWould you like to check a member's personal info?(yes/no): ");
                    String check2 = sc.next();
                    if(check2.equalsIgnoreCase("yes")){
                        System.out.print("Enter member's id: ");
                        int extID = sc.nextInt();
                        for(ExternalMember ext: em){
                            if(ext.getCustomerID() == extID){
                                ext.personalInfo();
                            }
                        }
                    }
                    else if(check2.equalsIgnoreCase("no")){
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
                    break;
                case 7:
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