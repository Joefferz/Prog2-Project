package main.java.com.movierental.test;

/**
 * Represents a student customer.
 * A student has additional information such as school name and grade.
 *
 * <p>This class inherits the {@link Person} abstract class.</p>
 */
public class Student extends Person {
    private String schoolName;
    private String grade;

    /**
     * Constructs a Student object with the given personal and academic details.
     *
     * @param name        the student's name (inherited)
     * @param customerID  the student's unique customer ID (inherited)
     * @param membership  the membership type = Student (inherited)
     * @param schoolName  the name of the school the student attends
     * @param grade       the student's grade
     */
    Student(String name, String customerID, String membership, String schoolName, String grade) {
        super(name, customerID, membership);
        this.schoolName = schoolName;
        this.grade = grade;
    }

    /**
     * Returns the student's name.
     *
     * @return the name of the student
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Returns the student's unique customer ID.
     *
     * @return the ID of a student
     */
    public String getCustomerID() {
        return super.getCustomerID();
    }

    /**
     * Returns the student's membership type.
     *
     * @return the membership category
     */
    public String getMembership() {
        return super.getMembership();
    }

    /**
     * Returns the name of the school the student attends.
     *
     * @return the name of the school
     */
    public String getInfoOne() {
        return this.schoolName;
    }

    /**
     * Returns the student's grade.
     *
     * @return the grade
     */
    public String getInfoTwo() {
        return this.grade;
    }

    /**
     * Returns a string containing the student's basic information.
     * Overrides method in Person
     *
     * @return a formatted string of student details
     */
    @Override
    public String toString(){
        return super.toString();
    }

    /**
     * Prints the student's academic/personal information (school name and grade).
     * Implementation of the abstract method in Person.
     */
    @Override
    public void personalInfo(){
        System.out.println("School Name: " + getInfoOne() + "\nGrade: " + getInfoTwo());
    }
}