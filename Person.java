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
        return "Name: " + name + "\nID: " + customerID + "\nMembership: " + membership;
    }

    abstract void personalInfo();

    public String getName() {
        return name;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getMembership() {
        return membership;
    }
}