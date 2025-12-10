/**
 * Represents an external member customer.
 * An external member has additional information such as job and organization name.
 *
 * <p>This class inherits the {@link Person} abstract class.</p>
 */
public class ExternalMember extends Person {
    private String job;
    private String organization;

    /**
     * Constructs an ExternalMember object with the given personal and academic details.
     *
     * @param name          the member's name (inherited)
     * @param customerID    the member's unique customer ID (inherited)
     * @param membership    the membership type = External (inherited)
     * @param job           the member's job
     * @param organization  the member's organization affiliation
     */
    ExternalMember(String name, String customerID, String membership, String job, String organization) {
        super(name, customerID, membership);
        this.job = job;
        this.organization = organization;
    }

    /**
     * Returns the member's name.
     *
     * @return the name of the member
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Returns the member's unique customer ID.
     *
     * @return the ID of a member
     */
    public String getCustomerID() {
        return super.getCustomerID();
    }

    /**
     * Returns the member's membership type.
     *
     * @return the membership category
     */
    public String getMembership() {
        return super.getMembership();
    }

    /**
     * Returns the member's job.
     *
     * @return the job
     */
    public String getInfoOne() {
        return this.job;
    }

    /**
     * Returns the organization the member is in.
     *
     * @return the organization name
     */
    public String getInfoTwo() {
        return this.organization;
    }

    /**
     * Returns a string containing the member's basic information.
     * Overrides method in Person
     *
     * @return a formatted string of member's details
     */
    @Override
    public String toString(){
        return super.toString();
    }

    /**
     * Prints the member's personal information (job and organization).
     * Implementation of the abstract method in Person.
     */
    @Override
    public void personalInfo(){
        System.out.println("Job: " + getInfoOne() + "\nOrganization's name: " + getInfoTwo());
    }
}