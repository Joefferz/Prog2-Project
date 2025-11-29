/**
 * Represents a customer.
 * Each customer has a name, ID, and membership type.
 *
 * <p>This class is abstract and must be extended by concrete customer types
 * such as Student or ExternalMember.</p>
 */
abstract class Person {
    protected String name;
    protected String customerID;
    protected String membership;

    /**
     * Constructs a Person object with the specified name, ID, and membership.
     *
     * @param name        the customer's name
     * @param customerID  the unique ID of the customer
     * @param membership  the membership category of the customer
     */
    Person(String name, String customerID, String membership) {
        this.name = name;
        this.customerID = customerID;
        this.membership = membership;
    }

    /**
     * Returns a formatted string containing the customer's name,
     * ID, and membership type.
     *
     * @return a string representation of the customer's basic information
     */
    public String toString() {
        return "Name: " + this.name + "\nID: " + this.customerID + "\nMembership: " + this.membership;
    }

    /**
     * Displays the customer's detailed personal information.
     * Subclasses (Child classes) must provide their own implementation (override).
     */
    public abstract void personalInfo();

    /**
     * Returns the customer's name.
     *
     * @return the name of the customer
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the customer's unique ID.
     *
     * @return the ID of the customer
     */
    public String getCustomerID() {
        return this.customerID;
    }

    /**
     * Returns the customer's membership category.
     *
     * @return the membership type of the customer
     */
    public String getMembership() {
        return this.membership;
    }
}