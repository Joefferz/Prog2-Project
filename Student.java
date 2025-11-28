public class Student extends Person {
    private String name;
    private String customerID;
    private String membership;
    private String schoolName;
    private int grade;

    Student(String name, String customerID, String membership, String schoolName, int grade) {
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

    public String getCustomerID() {
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