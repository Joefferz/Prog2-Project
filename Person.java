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