    import java.lang.reflect.Type;
    import java.util.*;
    import java.time.*;
    import java.time.format.DateTimeFormatter;
    import java.io.*;
    import com.google.gson.*;
    import com.google.gson.reflect.TypeToken;
    import com.google.gson.TypeAdapter;
    import com.google.gson.stream.JsonReader;
    import com.google.gson.stream.JsonWriter;

    public class Main {

        private static final Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);

            File folder = new File("Data");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            //Member (Students + ExternalMembers)
            ArrayList<Person> members = loadList("members.json",
                    new TypeToken<ArrayList<Person>>(){}.getType());

            if (members.isEmpty()) {
                members.add(new Student("Joeffrey", "ST1", "Student", "Vanier", 90));
                members.add(new Student("Thomas", "ST2", "Student", "Vanier", 90));
                members.add(new ExternalMember("Charles", "EX1", "External", "Engineer", "TechCorp"));
                members.add(new ExternalMember("Danush", "EX2", "External", "Doctor", "HealthCareInc"));
            }

            ArrayList<Movie> movies = loadList("movies.json",
                    new TypeToken<ArrayList<Movie>>(){}.getType());

            if (movies.isEmpty()) {
                movies.add(new Movie("MV1", "Home Alone 2: Lost in New York"));
                movies.add(new Movie("MV2", "The Grinch"));
                movies.add(new Movie("MV3", "Despicable Me"));
                movies.add(new Movie("MV4", "The Lion King"));
            }

            ArrayList<Rental> rentals = loadList("rentals.json",
                    new TypeToken<ArrayList<Rental>>(){}.getType());

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

                int choice;

                while (true) {
                    System.out.print("Enter your choice (1-8): ");
                    try {
                        choice = input.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number (1-8).");
                        input.nextLine();
                    }
                }

                switch (choice) {

                    case 1: // Add new member
                        addMember(input, members);
                        break;

                    case 2: // Add new movie
                        addMovie(input, movies);
                        break;

                    case 3: // Display students
                        displayMembersByType(members, "Student", input);
                        break;

                    case 4: // Display external members
                        displayMembersByType(members, "External", input);
                        break;

                    case 5: // Display movies
                        System.out.println("--- MOVIE LIST ---");
                        for (Movie m : movies){
                            m.show();
                            System.out.println("--------------------");
                        }
                        break;

                    case 6: // Start rental
                        startRental(input, members, movies, rentals);
                        break;

                    case 7: // Start return
                        startReturn(input, members, movies, rentals);
                        break;

                    case 8: // Exit and save
                        saveList("members.json", members);
                        saveList("movies.json", movies);
                        saveList("rentals.json", rentals);
                        System.out.println("Data saved. Exiting...");
                        flag = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            }
        }

        //MEMBER METHODS
        private static void addMember(Scanner input, ArrayList<Person> members) {
            System.out.println("--- ADD A NEW MEMBER ---");
            boolean memCheck = false;
            input.nextLine();

            while (!memCheck) {
                System.out.print("Select membership (Student/External): ");
                String membership = input.nextLine();

                try {
                    if (membership.equalsIgnoreCase("Student")) {
                        System.out.print("Enter student's customer ID (ST1-ST999): ");
                        String studentID = input.nextLine().toUpperCase();

                        if (!studentID.matches("ST\\d{1,3}"))
                            throw new Exception("Invalid ID. Must start with 'ST' and 1-3 digits.");

                        for (Person p : members) {
                            if (p.getCustomerID().equalsIgnoreCase(studentID))
                                throw new MemberAlreadyExistsException("Student ID already exists: " + studentID);
                        }

                        System.out.print("Enter student's name: ");
                        String studentName = input.nextLine();

                        System.out.print("Enter school name: ");
                        String schoolName = input.nextLine();

                        System.out.print("Enter grade: ");
                        int grade = input.nextInt();
                        input.nextLine();

                        members.add(new Student(studentName, studentID, membership, schoolName, grade));
                        System.out.println("Student added successfully.");
                        memCheck = true;

                    } else if (membership.equalsIgnoreCase("External")) {
                        System.out.print("Enter member's customer ID (EX1-EX999): ");
                        String externalID = input.nextLine().toUpperCase();

                        if (!externalID.matches("EX\\d{1,3}"))
                            throw new Exception("Invalid ID. Must start with 'EX' and 1-3 digits.");

                        for (Person p : members) {
                            if (p.getCustomerID().equalsIgnoreCase(externalID))
                                throw new MemberAlreadyExistsException("Member ID already exists: " + externalID);
                        }

                        System.out.print("Enter member's name: ");
                        String externalName = input.nextLine();

                        System.out.print("Enter job: ");
                        String job = input.nextLine();

                        System.out.print("Enter organization: ");
                        String organization = input.nextLine();

                        members.add(new ExternalMember(externalName, externalID, membership, job, organization));
                        System.out.println("External member added successfully.");
                        memCheck = true;

                    } else {
                        System.out.println("Invalid membership. Try again.");
                    }

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        private static void displayMembersByType(ArrayList<Person> members, String type, Scanner input) {
            System.out.println("--- " + type.toUpperCase() + " LIST ---");
            boolean found = false;
            for (Person p : members) {
                if (p.getMembership().equalsIgnoreCase(type)) {
                    System.out.println(p.toString());
                    System.out.println("--------------------");
                    found = true;
                }
            }
            if (!found) System.out.println("No " + type.toLowerCase() + " members found.");

            System.out.print("\nView personal info? (yes/no): ");
            String ans = input.next();
            if (ans.equalsIgnoreCase("yes")) {
                System.out.print("Enter ID: ");
                String id = input.next();
                for (Person p : members) {
                    if (p.getCustomerID().equalsIgnoreCase(id)) p.personalInfo();
                }
            }
        }

        // MOVIE METHODS
        private static void addMovie(Scanner input, ArrayList<Movie> movies) {
            System.out.println("--- ADD A NEW MOVIE ---");
            boolean movCheck = false;
            input.nextLine();

            while (!movCheck) {
                try {
                    System.out.print("Enter movie name: ");
                    String movieName = input.nextLine();

                    System.out.print("Enter movie ID (MV1-MV999): ");
                    String movieID = input.nextLine().toUpperCase();

                    if (!movieID.matches("MV\\d{1,3}"))
                        throw new Exception("Invalid ID. Must start with 'MV' and 1-3 digits.");

                    for (Movie m : movies) {
                        if (m.getMovieID().equalsIgnoreCase(movieID))
                            throw new MovieAlreadyExistsException("Movie ID already exists: " + movieID);
                    }

                    movies.add(new Movie(movieID, movieName));
                    System.out.println("Movie added successfully.");
                    movCheck = true;

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        //RENTAL METHODS
        private static void startRental(Scanner input, ArrayList<Person> members, ArrayList<Movie> movies, ArrayList<Rental> rentals) {
            System.out.println("--------------------");
            System.out.println("START A CUSTOMER'S RENTAL:\n");

            input.nextLine();

            try {
                if (members.isEmpty()) throw new Exception("No members available.");

                System.out.println("List of all members:");
                for (Person p : members) {
                    System.out.println(p.toString());
                    System.out.println("--------------------");
                }

                System.out.print("Enter customer's ID: ");
                String cRentID = input.nextLine().trim().toUpperCase();

                Person customer = findCustomer(members, cRentID);

                System.out.println("\n--- Available Movies ---");
                ArrayList<Movie> availableMovies = new ArrayList<>();
                for (Movie m : movies) {
                    if (m.isRentable().equalsIgnoreCase("Available")) {
                        m.show();
                        System.out.println("--------------------");
                        availableMovies.add(m);
                    }
                }
                if (availableMovies.isEmpty()) throw new Exception("No movies available to rent.");

                System.out.print("Enter movie ID: ");
                String movRentID = input.nextLine().trim().toUpperCase();

                Movie selected = getMovie(availableMovies, movRentID, true);

                for (Rental rent : rentals) {
                    if (rent.getCustomerRenterID().equalsIgnoreCase(cRentID) &&
                            rent.getMovieRentedID().equalsIgnoreCase(movRentID) &&
                            rent.getDateReturned() == null) {
                        throw new Exception("This customer has already rented this movie and not returned it yet.");
                    }
                }

                Rental newRent = new Rental(cRentID, selected.getMovieID(), LocalDate.now());
                rentals.add(newRent);
                selected.updateAvailability();

                System.out.println("\nRental successfully created:");
                newRent.details();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        private static void startReturn(Scanner input, ArrayList<Person> members, ArrayList<Movie> movies, ArrayList<Rental> rentals) {
            System.out.println("--------------------");
            System.out.println("START A CUSTOMER'S RETURN:");

            input.nextLine();

            try {
                if (rentals.isEmpty()) throw new Exception("No rentals available.");

                System.out.println("\n--- All Rentals ---");
                for (Rental rent : rentals) {
                    rent.details();
                    System.out.println("--------------------");
                }

                System.out.print("Enter customer's ID: ");
                String cReturnID = input.nextLine().trim().toUpperCase();

                Person customer = findCustomer(members, cReturnID);

                ArrayList<Rental> customerRentals = new ArrayList<>();
                for (Rental rent : rentals) {
                    if (rent.getCustomerRenterID().equalsIgnoreCase(cReturnID)) customerRentals.add(rent);
                }

                if (customerRentals.isEmpty()) throw new Exception("This customer has no rentals.");

                System.out.println("\n--- Member's Rentals ---");
                for (Rental rent : customerRentals) {
                    rent.details();
                    System.out.println("--------------------");
                }

                System.out.print("Enter movie ID to return: ");
                String movReturnID = input.nextLine().trim().toUpperCase();

                Rental activeRental = null, everRented = null;
                for (Rental rent : customerRentals) {
                    if (rent.getMovieRentedID().equalsIgnoreCase(movReturnID)) {
                        everRented = rent;
                        if (rent.getDateReturned() == null) activeRental = rent;
                    }
                }

                if (everRented == null) throw new Exception("This customer NEVER rented this movie.");
                if (activeRental == null) throw new Exception("This rental has ALREADY been returned.");

                Movie selected = getMovie(movies, movReturnID, false);
                System.out.println("Movie found. Proceeding with return.");

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
                    } catch (Exception e) {
                        System.out.println("Invalid return date. Try again.");
                    }
                }

                activeRental.setDateReturned(returnDate);
                activeRental.details();
                System.out.println("Nights rented: " + activeRental.getNightsRented());

                double finalFee = activeRental.calculate(customer.getMembership());
                System.out.printf("Fee: $%.2f%n", finalFee);

                selected.updateAvailability();
                System.out.println("Movie successfully returned.");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        //HELPER METHODS
        private static Person findCustomer(ArrayList<Person> members, String customerID) throws Exception {
            for (Person p : members) {
                if (p.getCustomerID().equalsIgnoreCase(customerID)) return p;
            }
            throw new Exception("Customer ID does not exist.");
        }

        private static Movie getMovie(ArrayList<Movie> movies, String movID, boolean forRental)
                throws Exception {
            for (Movie m : movies) {
                if (m.getMovieID().equalsIgnoreCase(movID)) {
                    if (forRental && !m.isRentable().equalsIgnoreCase("Available"))
                        throw new MovieUnavailableException("Movie is already borrowed.");
                    return m;
                }
            }
            throw new MovieNotFoundException("Movie not found: " + movID);
        }

        //LocalDate adapter
        private static class LocalDateAdapter extends TypeAdapter<LocalDate> {

            @Override
            public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
                if (localDate == null) {
                    jsonWriter.nullValue();
                    return;
                }
                jsonWriter.value(localDate.toString());
            }

            @Override
            public LocalDate read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == com.google.gson.stream.JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return LocalDate.parse(jsonReader.nextString());
            }
        }

        //Loading info from a file
        private static <T> ArrayList<T> loadList(String fileName, Type type) {
            File file = new File("Data", fileName);

            try (FileReader reader = new FileReader(file)) {
                return gson.fromJson(reader, type);
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }

        //Saving files into a folder
        private static <T> void saveList(String fileName, ArrayList<T> list) {
            File file = new File("Data", fileName);

            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(list, writer);
            } catch (IOException e) {
                System.out.println("Error saving: " + e.getMessage());
            }
        }
    }