public class Student extends Person {
    private String schoolName;
    private int grade;

    Student(String name, String customerID, String membership, String schoolName, int grade) {
        super(name, customerID, membership);
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
        return this.schoolName;
    }

    public int getGrade() {
        return this.grade;
    }

    public String toString(){
        return super.toString();
    }

    public void personalInfo(){
        System.out.println("School Name: " + getSchoolName() + "\nGrade: " + getGrade());
    }
}