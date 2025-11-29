abstract class Person {
    protected String name;
    protected String customerID;
    protected String membership;

    Person(String name, String customerID, String membership) {
        this.name = name;
        this.customerID = customerID;
        this.membership = membership;
    }

    public String toString() {
        return "Name: " + this.name + "\nID: " + this.customerID + "\nMembership: " + this.membership;
    }

    public abstract void personalInfo();

    public String getName() {
        return this.name;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getMembership() {
        return this.membership;
    }
}