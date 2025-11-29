public class ExternalMember extends Person {
    private String job;
    private String organization;

    ExternalMember(String name, String customerID, String membership, String job, String organization) {
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

    public String getCustomerID() {
        return super.getCustomerID();
    }

    public String getMembership() {
        return super.getMembership();
    }

    public String getJob() {
        return this.job;
    }

    public String getOrganization() {
        return this.organization;
    }

    public String toString(){
        return super.toString();
    }

    public void personalInfo(){
        System.out.println("Job: " + getJob() + "\nOrganization's name: " + getOrganization());
    }
}