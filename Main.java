import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Student> st = new ArrayList<>();
        ArrayList<ExternalMember> em = new ArrayList<>();
        ArrayList<Movie> mov = new ArrayList<>();
        ArrayList<Rental> r = new ArrayList<>();

        File stFile = new File("Prog2Project-master\\Data\\Students.txt");
        File exFile = new File("Prog2Project-master\\Data\\ExternalMembers.txt");
        File movFile = new File("Prog2Project-master\\Data\\Movies.txt");
        File rentalFile = new File("Prog2Project-master\\Data\\Rentals.txt");

        boolean flag = true;

        while (flag) {
            System.out.println("--------------------");
            System.out.println("MOVIE RENTAL SYSTEM");
            System.out.println("--------------------");
            System.out.println("1. Add new member");
            System.out.println("2. Add new movie");
            System.out.println("3. Display students");
            System.out.println("4. Display external members");
            System.out.println("5. Display movies");
            System.out.println("6. Start a rental");
            System.out.println("7. Start a return");
            System.out.println("8. Exit");
            System.out.println("--------------------");

            System.out.print("What would you like to do? (Enter your choice): ");
            int choice = input.nextInt();

            switch (choice) {

                case 1: //Adding a new member, 'Student' or 'External'
                    System.out.println("---ADD A NEW MEMBER---");
                    boolean memCheck = false;
                    input.nextLine();

                    //Checking valid membership
                    while (!memCheck) {
                        System.out.print("Select a membership (Enter 'Student' or 'External'): ");
                        String membership = input.nextLine();

                        try {
                            if (membership.equalsIgnoreCase("student")) {

                                //Checking student ID
                                boolean studentIDCheck = false;
                                String studentID = null;

                                while (!studentIDCheck) {
                                    System.out.print("Enter student's customer ID (e.g., ST1, ST12, ST123): ");
                                    studentID = input.nextLine().toUpperCase();

                                    //ST followed by 1 to 3 digits
                                    if (studentID.matches("ST\\d{1,3}")) {

                                        //Check for duplicate ID
                                        boolean dupeSTIDCheck = false;

                                        for (Student s : st) {
                                            if (s.getCustomerID().equalsIgnoreCase(studentID)) {
                                                dupeSTIDCheck = true;
                                                break;
                                            }
                                        }

                                        if (dupeSTIDCheck) {
                                            System.out.println("Student ID already exists.");
                                        }
                                        else {
                                            studentIDCheck = true;
                                        }
                                    }
                                    else {
                                        throw new Exception("Invalid ID. Must start with 'ST' and have 1-3 digits (max 5 chars).");
                                    }
                                }

                                System.out.print("Enter student's name: ");
                                String studentName = input.nextLine();

                                System.out.print("Enter student's school name: ");
                                String schoolName = input.nextLine();

                                System.out.print("Enter student's grade: ");
                                int grade = input.nextInt();
                                input.nextLine();

                                Student stu = new Student(studentName, studentID, membership, schoolName, grade);
                                st.add(stu);

                                System.out.println("--------------------");
                                System.out.println("Customer successfully added.\n");
                                memCheck = true;

                            } else if (membership.equalsIgnoreCase("external")) {

                                //Checking external member ID
                                boolean memberIDCheck = false;
                                String externalID = null;

                                while (!memberIDCheck) {
                                    System.out.print("Enter member's customer ID (e.g., EX1, EX12, EX123): ");
                                    externalID = input.nextLine().toUpperCase();

                                    //EX followed by 1 to 3 digits
                                    if (externalID.matches("EX\\d{1,3}")) {

                                        //Check for duplicate ID
                                        boolean dupeEXIDCheck = false;

                                        for (ExternalMember ex : em) {
                                            if (ex.getCustomerID().equalsIgnoreCase(externalID)) {
                                                dupeEXIDCheck = true;
                                                break;
                                            }
                                        }

                                        if (dupeEXIDCheck) {
                                            System.out.println("Member ID already exists.");
                                        }
                                        else {
                                            memberIDCheck = true;
                                        }
                                    }
                                    else {
                                        throw new Exception("Invalid ID. Must start with 'EX' and have 1-3 digits (max 5 chars).");
                                    }
                                }

                                System.out.print("Enter member's name: ");
                                String externalName = input.nextLine();

                                System.out.print("Enter member's job: ");
                                String job = input.nextLine();

                                System.out.print("Enter member's organization name: ");
                                String organization = input.nextLine();

                                ExternalMember exMem = new ExternalMember(externalName, externalID, membership, job, organization);
                                em.add(exMem);

                                System.out.println("--------------------");
                                System.out.println("Customer successfully added.\n");
                                memCheck = true;
                            }
                            else {
                                System.out.println("Invalid membership. Please try again.");
                            }
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please try again");
                            input.nextLine();
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case 2: //Adding a new movie
                    System.out.println("---ADD A NEW MOVIE---");
                    boolean movCheck = false;
                    input.nextLine();

                    while (!movCheck) {
                        try {

                            //Check for duplicate movie name
                            boolean movieNameCheck = false;
                            String movieName = null;
                            while (!movieNameCheck) {
                                System.out.print("Enter movie name: ");
                                movieName = input.nextLine();

                                boolean dupeNameCheck = false;
                                for (Movie m : mov) {
                                    if (m.getMovieName().equalsIgnoreCase(movieName)) {
                                        dupeNameCheck = true;
                                        break;
                                    }
                                }

                                if (dupeNameCheck) {
                                    System.out.println("Movie already exists.");
                                }
                                else {
                                    movieNameCheck = true;
                                }
                            }

                            boolean movieIDCheck = false;
                            String movieID = null;

                            //Checking movie ID
                            while (!movieIDCheck) {
                                System.out.print("Enter movie's ID (e.g., MV1, MV12, MV123): ");
                                movieID = input.nextLine().toUpperCase();

                                //MV followed by 1 to 3 digits
                                if (movieID.matches("MV\\d{1,3}")) {

                                    //Check for duplicate ID
                                    boolean dupeMVIDCheck = false;

                                    for (Movie mv : mov) {
                                        if (mv.getMovieID().equalsIgnoreCase(movieID)) {
                                            dupeMVIDCheck = true;
                                            break;
                                        }
                                    }

                                    if (dupeMVIDCheck) {
                                        System.out.println("Movie ID already exists.");
                                    }
                                    else {
                                        movieIDCheck = true;
                                    }
                                } else {
                                    throw new Exception("Invalid ID. Must start with 'MV' and have 1-3 digits (max 5 chars).");
                                }
                            }

                            Movie movie = new Movie(movieID, movieName);
                            mov.add(movie);

                            System.out.println("--------------------");
                            System.out.println("Movie successfully added.\n");
                            movCheck = true;
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please try again");
                            input.nextLine();
                        }
                        catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 3: //Listing all students
                    System.out.println("---STUDENT LIST---");

                    if(st.isEmpty()) {
                        System.out.println("\nNo list of students found.");
                        break;
                    }
                    else {
                        for (Student s : st) {
                            System.out.println(s.toString());
                        }
                    }

                    System.out.print("\nWould you like to view a student's personal info?(yes/no): ");
                    String studentPINFO = input.next();

                    if(studentPINFO.equalsIgnoreCase("yes")) {
                        System.out.print("Enter student's id: ");
                        String stID = input.next();

                        for(Student s: st) {
                            if(s.getCustomerID().equalsIgnoreCase(stID)) {
                                s.personalInfo();
                            }
                        }
                    }
                    else if(studentPINFO.equalsIgnoreCase("no")) {
                        break;
                    }
                    else {
                        System.out.println("Invalid input");
                    }
                    break;

                case 4: //Listing all external members
                    System.out.println("---MEMBER LIST---");

                    if(em.isEmpty()) {
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

                    if(externalPINFO.equalsIgnoreCase("yes")) {
                        System.out.print("Enter member's id: ");
                        String extID = input.next();

                        for(ExternalMember ext: em) {
                            if(ext.getCustomerID().equalsIgnoreCase(extID)) {
                                ext.personalInfo();
                            }
                        }
                    }
                    else if(externalPINFO.equalsIgnoreCase("no")) {
                        break;
                    }
                    else {
                        System.out.println("Invalid input");
                    }
                    break;

                case 5: //Listing all movies
                    System.out.println("---MOVIE LIST---");
                    for(Movie m : mov) {
                        m.show();
                    }
                    break;

                case 6: //Starting a customer's movie rental
                    System.out.println("--------------------");
                    System.out.println("START A CUSTOMER'S RENTAL:\n");

                    input.nextLine();

                    try {
                        //Show members
                        if (st.isEmpty() && em.isEmpty()) {
                            throw new Exception("No members available.");
                        }

                        System.out.println("List of all members:");
                        System.out.println("--- Students ---");
                        for (Student stu : st) System.out.println(stu);

                        System.out.println("--- External Members ---");
                        for (ExternalMember ext : em) System.out.println(ext);

                        //Enter customer ID
                        System.out.print("Enter customer's ID: ");
                        String cRentID = input.nextLine().trim();

                        findCustomer(st, em, cRentID); //throws exception if missing

                        //List available movies
                        System.out.println("\n--- Available Movies ---");
                        boolean foundAvailable = false;

                        for (Movie m : mov) {
                            if (m.isRentable().equalsIgnoreCase("Available")) {
                                m.show();
                                foundAvailable = true;
                            }
                        }

                        if (!foundAvailable)
                            throw new Exception("No movies available to rent.");

                        //Enter movie ID
                        System.out.print("Enter movie ID: ");
                        String movRentID = input.nextLine().trim();

                        Movie selected = getMovie(mov, movRentID);

                        if (!selected.isRentable().equalsIgnoreCase("Available")) {
                            throw new Exception("This movie is NOT available for rent.");
                        }

                        //Create rental
                        LocalDate rentDate = LocalDate.now();
                        Rental newRent = new Rental(cRentID, selected.getMovieID(), rentDate);

                        r.add(newRent);
                        selected.updateAvailability();

                        System.out.println("\nRental successfully created:");
                        newRent.details();

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 7: //Starting a customer's return
                    System.out.println("--------------------");
                    System.out.println("START A CUSTOMER'S RETURN:");

                    try {
                        input.nextLine();

                        //List all rentals
                        if (r.isEmpty()) {
                            throw new Exception("No rentals available.");
                        }

                        System.out.println("\n--- All Rentals ---");
                        for (Rental rent : r) {
                            rent.details();
                        }

                        //Enter customer ID
                        System.out.print("Enter customer's ID: ");
                        String cReturnID = input.nextLine().trim();

                        boolean hasRental = false;
                        for (Rental rent : r) {
                            if (rent.getCustomerRenterID().equalsIgnoreCase(cReturnID)) {
                                rent.fullDetails();
                                hasRental = true;
                            }
                        }

                        if (!hasRental) {
                            throw new Exception("This customer has no rentals.");
                        }

                        findCustomer(st, em, cReturnID);

                        //List customer's rentals
                        System.out.println("\n--- Member's Rentals ---");
                        for (Rental rent : r) {
                            if (rent.getCustomerRenterID().equalsIgnoreCase(cReturnID)) {
                                rent.fullDetails();
                            }
                        }

                        //Enter movie ID
                        System.out.print("Enter movie ID to return: ");
                        String movReturnID = input.nextLine().trim();

                        Movie selected = getMovie(mov, movReturnID);

                        //Look for rental
                        Rental activeRental = null;
                        Rental alreadyReturned = null;

                        for (Rental rent : r) {
                            if (rent.getCustomerRenterID().equalsIgnoreCase(cReturnID)
                                    && rent.getMovieRentedID().equalsIgnoreCase(movReturnID)) {

                                if (rent.getDateReturned() == null) {
                                    activeRental = rent; // Active rental
                                } else {
                                    alreadyReturned = rent; // Previously returned
                                }
                            }
                        }

                        //No rental found
                        if (activeRental == null && alreadyReturned == null) {
                            throw new Exception("This customer NEVER rented this movie.");
                        }

                        //Already returned
                        if (activeRental == null) {
                            throw new Exception("This rental has ALREADY been returned.");
                        }

                        System.out.println("Movie found. Proceeding with return.");

                        //Return date
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        LocalDate returnDate;

                        while (true) {
                            try {
                                System.out.print("Enter return date (MM/DD/YYYY): ");
                                returnDate = LocalDate.parse(input.nextLine().trim(), dtf);

                                if (!returnDate.isAfter(activeRental.getDateBorrowed())) {
                                    System.out.println("Return date must be AFTER borrow date.");
                                    continue;
                                }

                                break;
                            }
                            catch (Exception e) {
                                System.out.println("Invalid return date. Try again.");
                            }
                        }

                        //Finish the return
                        activeRental.setDateReturned(returnDate);
                        activeRental.fullDetails();

                        System.out.println("Nights rented: " + activeRental.getNightsRented());

                        //Calculate fee
                        double finalFee = 0;

                        for (Student s : st) {
                            if (s.getCustomerID().equalsIgnoreCase(cReturnID)) {
                                finalFee = activeRental.calculate(s.getMembership());
                            }
                        }
                        for (ExternalMember e : em) {
                            if (e.getCustomerID().equalsIgnoreCase(cReturnID)) {
                                finalFee = activeRental.calculate(e.getMembership());
                            }
                        }

                        System.out.printf("Fee: $%.2f%n", finalFee);

                        //Make movie available again
                        selected.updateAvailability();
                        System.out.println("Movie successfully returned.");

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 8: //Exiting the system
                    System.out.println("Exiting system...");
                    flag = false;
                    break;

                default: //Default response after invalid input
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static Movie getMovie(ArrayList<Movie> mov, String movRentID) throws Exception {
        Movie selected = null;
        for (Movie m : mov) {
            if (m.getMovieID().equalsIgnoreCase(movRentID)) {
                selected = m;
            }
        }

        if (selected == null) {
            throw new Exception("Invalid movie ID.");
        }

        if (!selected.isRentable().equalsIgnoreCase("Available")) {
            throw new Exception("Movie is not available.");
        }
        return selected;
    }

    private static void findCustomer(ArrayList<Student> st, ArrayList<ExternalMember> em, String cReturnID) throws Exception {
        boolean customerFound = false;

        for (Student s : st) {
            if (s.getCustomerID().equalsIgnoreCase(cReturnID)) {
                customerFound = true;
            }
        }
        for (ExternalMember e : em) {
            if (e.getCustomerID().equalsIgnoreCase(cReturnID)) {
                customerFound = true;
            }
        }

        if (!customerFound) {
            throw new Exception("Customer ID does not exist.");
        }
    }
}