import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> st = new ArrayList<>();
        ArrayList<ExternalMember> em = new ArrayList<>();
        ArrayList<Movie> mov = new ArrayList<>();
        ArrayList<Rental> r = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("1. Add new member");
            System.out.println("2. Add new movie");
            System.out.println("3. Display students");
            System.out.println("4. Display external members");
            System.out.println("5. Display movies");
            System.out.println("6. Start a rental");
            System.out.println("7. Start a return");
            System.out.println("8. Exit");
            System.out.print("What would you like to do? (Enter your choice): ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Select a membership (Enter 'Student' or 'External'): ");
                    String membership = input.next();
                    if(membership.equalsIgnoreCase("student")){
                        System.out.print("Enter student's customer ID: ");
                        int studentID = input.nextInt();
                        System.out.print("Enter student's name: ");
                        String studentName = input.next();
                        System.out.print("Enter student's school name: ");
                        String schoolName = input.next();
                        System.out.print("Enter student's grade: ");
                        int grade = input.nextInt();
                        Student stu = new Student(studentName, studentID, membership, schoolName, grade);
                        st.add(stu);
                    }
                    else if(membership.equalsIgnoreCase("external")){
                        System.out.print("Enter external's customer ID: ");
                        int externalID = input.nextInt();
                        System.out.print("Enter member's name: ");
                        String externalName = input.next();
                        System.out.print("Enter member's job: ");
                        String job = input.next();
                        System.out.print("Enter member's organization name: ");
                        String organization = input.next();
                        ExternalMember exMem = new ExternalMember(externalName, externalID, membership, job, organization);
                        em.add(exMem);
                    }
                    else{
                        System.out.println("Invalid membership");
                        break;
                    }
                    System.out.println("Customer successfully added.");
                    break;
                case 2:
                    System.out.print("Enter movie name: ");
                    String movieName = input.next();
                    System.out.print("Enter movie ID: ");
                    int movieID = input.nextInt();
                    Movie movie = new Movie(movieID, movieName);
                    mov.add(movie);
                    System.out.println("Movie successfully added.");
                    break;
                case 3:
                    System.out.println("List of students:");
                    if(st.isEmpty()){
                        System.out.println("\nNo list of students found.");
                        break;
                    }else {
                        for (Student s : st) {
                            System.out.println(s.toString());
                        }
                    }
                    System.out.print("\nWould you like to view a student's personal info?(yes/no): ");
                    String studentPINFO = input.next();
                    if(studentPINFO.equalsIgnoreCase("yes")){
                        System.out.print("Enter student's id: ");
                        int stID = input.nextInt();
                        for(Student s: st){
                            if(s.getCustomerID() == stID){
                                s.personalInfo();
                            }
                        }
                    }
                    else if(studentPINFO.equalsIgnoreCase("no")){
                        break;
                    }
                    else {
                        System.out.println("Invalid input");
                    }
                    break;
                case 4:
                    System.out.println("List of external members: ");
                    if(em.isEmpty()){
                        System.out.println("No list of external members found.");
                        break;
                    }
                    else {
                        for (ExternalMember ext : em) {
                            System.out.println(ext.toString());
                        }
                    }
                    System.out.print("\nWould you like to view a member's personal info?(yes/no): ");
                    String externalPINFO = input.next();
                    if(externalPINFO.equalsIgnoreCase("yes")){
                        System.out.print("Enter member's id: ");
                        int extID = input.nextInt();
                        for(ExternalMember ext: em){
                            if(ext.getCustomerID() == extID){
                                ext.personalInfo();
                            }
                        }
                    }
                    else if(externalPINFO.equalsIgnoreCase("no")){
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
                    System.out.println("START A CUSTOMER'S RENTAL: ");
                    System.out.println("Which customer wants to rent? (Enter 'Student' or 'External' member): ");
                    String memChoice = input.next();
                    if(memChoice.equalsIgnoreCase("student")){
                        for(Student stu : st){
                            System.out.println(stu.toString());
                        }
                    }
                    else if (memChoice.equalsIgnoreCase("external")){
                        for(ExternalMember ext : em){
                            System.out.println(ext.toString());
                        }
                    }
                    else {
                        System.out.println("Invalid membership.");
                    }
                    System.out.print("Enter customer's ID: ");
                    int cRentID = input.nextInt();
                    System.out.println("Which movie does the customer want to rent? (Enter movie ID)");
                    for(Movie m : mov){
                        if(m.isRentable().equalsIgnoreCase("Available")){
                            m.show();
                        }
                    }
                    System.out.print("Enter movie's ID: ");
                    int movID = input.nextInt();
                    System.out.println("Enter date of rental (MM-DD-YYYY): ");
                    String borrowDate = input.next();
                    Rental rent = new Rental(cRentID, movID, borrowDate);
                    r.add(rent);
                    for(Movie m : mov){
                        if(m.getMovieID() == movID){
                            m.updateAvailability();
                        }
                    }
                    rent.details();
                    break;
                case 7:
                    System.out.println("START A CUSTOMER'S RETURN");
                    System.out.println("List of rentals: ");
                    for(Rental rental : r){
                        rental.details();
                    }
                    System.out.println("Select customer ID: ");
                    int cID = input.nextInt();
                    System.out.println("Customer's rental details: ");
                    for(Rental rents : r){
                        if (rents.getCustomerRenterID() == cID){
                            rents.fullDetails();
                        }
                    }
                    System.out.println("Select movie ID: ");
                    int mID = input.nextInt();
                    System.out.println("Enter return date (MM-DD-YYYY): ");
                    String returnDate = input.next();
                    for(Rental customerRents : r){
                        if(customerRents.getCustomerRenterID() == cID){
                            customerRents.setDateReturned(returnDate);
                            customerRents.fullDetails();
                        }
                    }
                    for(Movie m : mov){
                        if(m.getMovieID() == mID){
                            m.updateAvailability();
                        }
                    }
                    System.out.println("Movie successfully returned.");
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