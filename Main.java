import java.util.*;
import java.time.*;

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

                case 1: //Adding a new member, 'Student' or 'External'
                    boolean memCheck = false;
                    input.nextLine();

                    while (!memCheck) {
                        System.out.print("Select a membership (Enter 'Student' or 'External'): ");
                        String membership = input.next();

                        try {
                            if (membership.equalsIgnoreCase("student")) {
                                System.out.print("Enter student's customer ID: ");
                                int studentID = input.nextInt();
                                input.nextLine();

                                System.out.print("Enter student's name: ");
                                String studentName = input.nextLine();

                                System.out.print("Enter student's school name: ");
                                String schoolName = input.nextLine();

                                System.out.print("Enter student's grade: ");
                                int grade = input.nextInt();
                                input.nextLine();

                                Student stu = new Student(studentName, studentID, membership, schoolName, grade);
                                st.add(stu);

                                System.out.println("Customer successfully added.");
                                memCheck = true;

                            } else if (membership.equalsIgnoreCase("external")) {
                                System.out.print("Enter external's customer ID: ");
                                int externalID = input.nextInt();
                                input.nextLine();

                                System.out.print("Enter member's name: ");
                                String externalName = input.nextLine();

                                System.out.print("Enter member's job: ");
                                String job = input.nextLine();

                                System.out.print("Enter member's organization name: ");
                                String organization = input.nextLine();

                                ExternalMember exMem = new ExternalMember(externalName, externalID, membership, job, organization);
                                em.add(exMem);

                                System.out.println("Customer successfully added.");
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
                            System.out.println("Unexpected Error: " + e.getMessage());
                        }
                    }
                    break;

                case 2: //Adding a new movie
                    boolean movCheck = false;
                    input.nextLine();

                    while (!movCheck) {
                        try {
                            System.out.print("Enter movie name: ");
                            String movieName = input.nextLine();

                            System.out.print("Enter movie ID: ");
                            int movieID = input.nextInt();
                            input.nextLine();

                            Movie movie = new Movie(movieID, movieName);
                            mov.add(movie);

                            System.out.println("Movie successfully added.");
                            movCheck = true;
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please try again");
                            input.nextLine();
                        }
                        catch (Exception e) {
                            System.out.println("Unexpected Error: " + e.getMessage());
                        }
                    }
                    break;

                case 3: //Listing all students
                    System.out.println("List of students:");

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
                        int stID = input.nextInt();

                        for(Student s: st) {
                            if(s.getCustomerID() == stID) {
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
                    System.out.println("List of external members: ");

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
                        int extID = input.nextInt();

                        for(ExternalMember ext: em) {
                            if(ext.getCustomerID() == extID) {
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
                    System.out.println("List of movies: ");
                    for(Movie m : mov) {
                        m.show();
                    }
                    break;

                case 6: //Starting a customer's movie rental
                    boolean rentalDone = false;

                    while (!rentalDone) {
                        try {


                            System.out.println("START A CUSTOMER'S RENTAL:\n");

                            System.out.print("Which customer wants to rent? (Enter 'Student' / 'External'): ");
                            String memChoice = input.next();

                            if (!memChoice.equalsIgnoreCase("student") &&
                                    !memChoice.equalsIgnoreCase("external")) {
                                throw new Exception("Invalid membership type.");
                            }

                            if (memChoice.equalsIgnoreCase("student")) {
                                if (st.isEmpty()) {
                                    throw new Exception("No student members available.");
                                }
                                for (Student stu : st) System.out.println(stu);
                            }
                            else {
                                if (em.isEmpty()) {
                                    throw new Exception("No external members available.");
                                }
                                for (ExternalMember ext : em) System.out.println(ext);
                            }

                            System.out.print("Enter customer's ID: ");
                            if (!input.hasNextInt()) {
                                throw new Exception("Customer ID must be a number.");
                            }
                            int cRentID = input.nextInt();

                            boolean customerFound = false;

                            if (memChoice.equalsIgnoreCase("student")) {
                                for (Student s : st)
                                    if (s.getCustomerID() == cRentID) {
                                        customerFound = true;
                                    }
                            } else {
                                for (ExternalMember e : em)
                                    if (e.getCustomerID() == cRentID) {
                                        customerFound = true;
                                    }
                            }

                            if (!customerFound)
                                throw new Exception("Customer ID does not exist.");

                            System.out.println("\nAvailable movies:");
                            boolean available = false;

                            for (Movie m : mov) {
                                if (m.isRentable().equalsIgnoreCase("Available")) {
                                    m.show();
                                    available = true;
                                }
                            }

                            if (!available)
                                throw new Exception("No movies available to rent.");

                            System.out.print("Enter movie ID: ");
                            if (!input.hasNextInt()) {
                                throw new Exception("Movie ID must be a number.");
                            }
                            int movID = input.nextInt();

                            Movie selected = null;
                            for (Movie m : mov)
                                if (m.getMovieID() == movID) selected = m;

                            if (selected == null)
                                throw new Exception("Movie ID does not exist.");

                            if (!selected.isRentable().equalsIgnoreCase("Available"))
                                throw new Exception("Movie is not available.");

                            System.out.print("Enter date of rental (YYYY-MM-DD): ");
                            LocalDate date;

                            try {
                                date = LocalDate.parse(input.next());
                            } catch (Exception e) {
                                throw new Exception("Invalid date format. Use YYYY-MM-DD.");
                            }

                            Rental rent = new Rental(cRentID, movID, date);
                            r.add(rent);
                            selected.updateAvailability();

                            System.out.println("Rental successfully created:");
                            rent.details();

                            rentalDone = true;

                        } catch (Exception e) {
                            System.out.println("Unexpected error: " + e.getMessage());
                            input.nextLine();

                            System.out.println("Please try again.");
                        }
                    }
                    break;

                case 7: //Starting a customer's return
                    System.out.println("START A CUSTOMER'S RETURN");

                    System.out.println("List of rentals: ");
                    for (Rental rental : r) {
                        rental.details();
                    }

                    int cID;
                    while (true) {
                        try {
                            System.out.print("Select customer ID: ");
                            cID = Integer.parseInt(input.next());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid ID. Please enter a valid ID.");
                        }
                    }

                    System.out.println("Customer's rental details: ");
                    for (Rental rents : r) {
                        if (rents.getCustomerRenterID() == cID) {
                            rents.fullDetails();
                        }
                    }

                    int mID;
                    while (true) {
                        try {
                            System.out.print("Select movie ID: ");
                            mID = Integer.parseInt(input.next());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid movie ID. Please enter a valid ID.");
                        }
                    }

                    LocalDate returnDate;
                    while (true) {
                        try {
                            System.out.print("Enter return date (YYYY-MM-DD): ");
                            returnDate = LocalDate.parse(input.next());
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Try again (YYYY-MM-DD).");
                        }
                    }

                    //process return
                    for (Rental customerRents : r) {
                        if (customerRents.getCustomerRenterID() == cID) {
                            customerRents.setDateReturned(returnDate);
                            customerRents.fullDetails();

                            System.out.println("Nights rented for: " + customerRents.getNightsRented() + " days.");

                            for (Student s : st) {
                                if (s.getCustomerID() == cID) {
                                    System.out.printf("Fee: $%.2f%n", customerRents.calculate(s.getMembership()));
                                }
                            }
                        }
                    }

                    for (Movie m : mov) {
                        if (m.getMovieID() == mID) {
                            m.updateAvailability();
                        }
                    }

                    System.out.println("Movie successfully returned.");
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
}