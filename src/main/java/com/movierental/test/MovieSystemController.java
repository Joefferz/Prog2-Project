package main.java.com.movierental.test;

import main.Exceptions.MemberAlreadyExistsException;
import main.Exceptions.MovieNotFoundException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javafx.stage.Stage;

public class MovieSystemController {

    private ArrayList<Person> members = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Rental> rentals = new ArrayList<>();

    //PANES
    @FXML private AnchorPane MoviePane, MemberPane, RentMovie, ReturnMovie;

    // Movies
    @FXML private TableView<Movie> MovieList;
    @FXML private TableColumn<Movie,String> mvID, mvName, mvStatus;
    @FXML private TextField movieID, movieName;
    @FXML private TextArea movieInfo;

    // Members
    @FXML private TableView<Person> MemberList;
    @FXML private TableColumn<Person,String> memID, memName, memMembership, info1, info2;
    @FXML private TextField memberID, memberName, persoInfo1, persoInfo2;
    @FXML private ChoiceBox<String> membership;
    @FXML private TextArea memInfo;
    @FXML private Label personal1, personal2;

    // Rent
    @FXML private TableView<Rental> rentList;
    @FXML private TableColumn<Rental, String> mvRTID;
    @FXML private TableColumn<Rental, String> mbrRTID;
    @FXML private TableColumn<Rental, LocalDate> rtBorrow;
    @FXML private TableColumn<Rental, LocalDate> rtReturn;
    @FXML private TextField rentMovID, rentMemID;
    @FXML private TextArea rentInfo;

    // Return
    @FXML private TableView<Rental> returnList;
    @FXML private TableColumn<Rental, String> mvRTNID;
    @FXML private TableColumn<Rental, String> mbrRTNID;
    @FXML private TableColumn<Rental, LocalDate> rtnBorrow;
    @FXML private TableColumn<Rental, LocalDate> rtnReturn;
    @FXML private TextField returnMovID, returnMemID;
    @FXML private DatePicker returnDate;
    @FXML private TextArea returnInfo;

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();


    //TABLES
    @FXML
    public void initialize() {

        // Load JSON
        members = loadList("members.json", new TypeToken<ArrayList<Person>>(){}.getType());
        movies = loadList("movies.json", new TypeToken<ArrayList<Movie>>(){}.getType());
        rentals = loadList("rentals.json", new TypeToken<ArrayList<Rental>>(){}.getType());

        // If empty, insert defaults
        if (movies.isEmpty()) {
            movies.add(new Movie("MV1", "Home Alone 2"));
            movies.add(new Movie("MV2", "The Grinch"));
        }

        // Set up Movie Table
        mvID.setCellValueFactory(new PropertyValueFactory<>("movieID"));
        mvName.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        mvStatus.setCellValueFactory(new PropertyValueFactory<>("rentable"));

        MovieList.getItems().setAll(movies);

        // Members Table
        if (members.isEmpty()) {
            members.add(new Student("Joeffrey", "ST1", "Student", "Vanier", "90"));
            members.add(new Student("Thomas", "ST2", "Student", "Vanier", "75"));
            members.add(new ExternalMember("Charles", "EX1", "External", "Engineer", "TechCorp"));
            members.add(new ExternalMember("Danush", "EX2", "External", "Doctor", "HealthCareInc"));
        }

        memID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        memName.setCellValueFactory(new PropertyValueFactory<>("name"));
        memMembership.setCellValueFactory(new PropertyValueFactory<>("membership"));
        info1.setCellValueFactory(new PropertyValueFactory<>("infoOne"));
        info2.setCellValueFactory(new PropertyValueFactory<>("infoTwo"));

        MemberList.getItems().setAll(members);

        // Rental Tables
        // Rent table column setup
        mvRTID.setCellValueFactory(new PropertyValueFactory<>("movieRentedID"));
        mbrRTID.setCellValueFactory(new PropertyValueFactory<>("customerRenterID"));
        rtBorrow.setCellValueFactory(new PropertyValueFactory<>("dateBorrowed"));
        rtReturn.setCellValueFactory(new PropertyValueFactory<>("dateReturned"));

        // Return table column setup
        mvRTNID.setCellValueFactory(new PropertyValueFactory<>("movieRentedID"));
        mbrRTNID.setCellValueFactory(new PropertyValueFactory<>("customerRenterID"));
        rtnBorrow.setCellValueFactory(new PropertyValueFactory<>("dateBorrowed"));
        rtnReturn.setCellValueFactory(new PropertyValueFactory<>("dateReturned"));

        if (rentals.isEmpty()) {
            rentals.add(new Rental("ST1", "MV1", LocalDate.now().minusDays(3)));
            rentals.add(new Rental("EX1", "MV6", LocalDate.now().minusDays(1)));
        }

        rentList.getItems().setAll(rentals);
        returnList.getItems().setAll(rentals);

        //Drop down menu
        membership.setItems(FXCollections.observableArrayList("Student", "External"));
        membership.setValue("Student");

        //Auto-generate ID
        movieID.setText(generateMovieID());
        memberID.setText(generateMemberID("ST"));

        // Update member ID automatically when membership type changes
        membership.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            if (nv.equals("Student")) {
                memberID.setText(generateMemberID("ST"));
                personal1.setText("SCHOOL NAME");
                personal2.setText("GRADE");
                persoInfo1.clear();
                persoInfo2.clear();
            } else {
                memberID.setText(generateMemberID("EX"));
                personal1.setText("JOB");
                personal2.setText("ORGANISATION");
                persoInfo1.clear();
                persoInfo2.clear();
            }
        });

        //Personal info labels default values
        if ("Student".equals(membership.getValue())) {
            personal1.setText("SCHOOL NAME");
            personal2.setText("GRADE");
            memberID.setText(generateMemberID("ST"));
        } else {
            personal1.setText("JOB");
            personal2.setText("ORGANISATION");
            memberID.setText(generateMemberID("EX"));
        }
    }

    //PANE SWITCHING
    @FXML
    private void showMovies() {
        MoviePane.setVisible(true);
        MemberPane.setVisible(false);
        RentMovie.setVisible(false);
        ReturnMovie.setVisible(false);

        movieName.clear();
    }

    @FXML
    private void showMembers() {
        MoviePane.setVisible(false);
        MemberPane.setVisible(true);
        RentMovie.setVisible(false);
        ReturnMovie.setVisible(false);

        memberName.clear();
    }

    @FXML
    private void showRentals() {
        MoviePane.setVisible(false);
        MemberPane.setVisible(false);
        RentMovie.setVisible(true);
        ReturnMovie.setVisible(false);

        rentMovID.clear();
        rentMemID.clear();
    }

    @FXML
    private void showRentalsToReturn() {
        MoviePane.setVisible(false);
        MemberPane.setVisible(false);
        RentMovie.setVisible(false);
        ReturnMovie.setVisible(true);

        returnMovID.clear();
        returnMemID.clear();
    }

    //ADD MOVIE
    @FXML
    private void addMovie() {
        movieInfo.clear();

        try {
            // AUTO-GENERATE ID
            String id = generateMovieID();
            movieID.setText(id);

            String name = movieName.getText().trim();

            if (name.isEmpty())
                throw new Exception("Movie name cannot be empty.");

            Movie m = new Movie(id, name);
            movies.add(m);
            MovieList.getItems().add(m);

            saveList("movies.json", movies);

            movieInfo.setText("Movie added successfully!");

            // Prepare ID for next movie
            movieID.setText(generateMovieID());
            movieName.clear();

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }


    //ADD MEMBER
    @FXML
    private void addMember() {
        memInfo.clear();

        try {
            String name = memberName.getText().trim();
            String mem = membership.getValue();
            String infoA = persoInfo1.getText().trim();
            String infoB = persoInfo2.getText().trim();

            if (name.isEmpty())
                throw new Exception("Member name cannot be empty.");
            if (infoA.isEmpty())
                throw new Exception("Member info cannot be empty.");
            if (infoB.isEmpty())
                throw new Exception("Member info cannot be empty.");

            // AUTO-GENERATE ID
            String id;
            if (mem.equals("Student")) {
                id = generateMemberID("ST");
            }
            else {
                id = generateMemberID("EX");
            }

            memberID.setText(id);

            // Prevent duplicate ID
            for (Person p : members) {
                if (p.getCustomerID().equalsIgnoreCase(id))
                    throw new MemberAlreadyExistsException("Member ID already exists.");
            }

            Person p;
            if (mem.equals("Student")) {
                p = new Student(name, id, mem, infoA, infoB);
            } else {
                p = new ExternalMember(name, id, mem, infoA, infoB);
            }

            members.add(p);
            MemberList.getItems().add(p);
            saveList("members.json", members);

            memInfo.setText("Member added successfully!");

            // Prepare next ID
            if (mem.equals("Student"))
                memberID.setText(generateMemberID("ST"));
            else
                memberID.setText(generateMemberID("EX"));

            memberName.clear();
            persoInfo1.clear();
            persoInfo2.clear();

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    //RENT
    @FXML
    private void rentMovie() {
        rentInfo.clear();

        try {
            String mid = rentMovID.getText().toUpperCase().trim();
            String cid = rentMemID.getText().toUpperCase().trim();

            Movie m = findMovie(mid);
            Person p = findMember(cid);

            if (!m.isRentable().equalsIgnoreCase("Available"))
                throw new Exception("Movie is already rented.");

            Rental r = new Rental(cid, mid, LocalDate.now());
            rentals.add(r);

            m.updateAvailability(); // becomes Unavailable

            rentList.getItems().setAll(rentals);
            returnList.getItems().setAll(rentals);

            rentInfo.setText("Movie rented successfully!");

            saveList("movies.json", movies);
            saveList("rentals.json", rentals);

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    //RETURNING MOVIE
    @FXML
    private void returnMovie() {
        returnInfo.clear();

        try {
            String mid = returnMovID.getText().toUpperCase().trim();
            String cid = returnMemID.getText().toUpperCase().trim();
            LocalDate date = returnDate.getValue();

            // input checks
            if (mid.isEmpty()) throw new Exception("Movie ID cannot be empty.");
            if (cid.isEmpty()) throw new Exception("Member ID cannot be empty.");
            if (date == null) throw new Exception("Select a return date.");

            // find movie (findMovie throws if not found)
            Movie movie;
            try {
                movie = findMovie(mid);
            } catch (Exception ex) {
                throw new Exception("Movie not found.");
            }

            // check movie status (assumes rentable field: "Available" or "Unavailable")
            if (movie.isRentable().equalsIgnoreCase("Available")) {
                throw new Exception("This movie is not rented right now.");
            }

            // find active rental for this member + movie
            Rental active = rentals.stream()
                    .filter(r -> r.getMovieRentedID().equalsIgnoreCase(mid))
                    .filter(r -> r.getCustomerRenterID().equalsIgnoreCase(cid))
                    .filter(r -> r.getDateReturned() == null)
                    .findFirst()
                    .orElse(null);

            if (active == null) {
                // member hasn't rented this movie
                throw new Exception("This movie is not rented by this member.");
            }

            // set return date and compute fees
            active.setDateReturned(date);

            Person customer = findMember(cid); // will throw if member not found
            int nights = active.getNightsRented();
            double fee = active.calculate(customer.getMembership());

            movie.updateAvailability();

            // refresh UI & save
            rentList.getItems().setAll(rentals);
            returnList.getItems().setAll(rentals);

            try {
                saveList("movies.json", movies);
                saveList("rentals.json", rentals);
            } catch (Exception ex) {
                // if saveList swallows exceptions currently, consider logging here
                ex.printStackTrace();
            }

            returnInfo.setText(
                    "Returned successfully! | Nights rented: " + nights +
                            " | Rental Fee: $" + String.format("%.2f", fee)
            );

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }


    //EXIT SYSTEM
    @FXML
    private void exitSystem() {
        saveList("members.json", members);
        saveList("movies.json", movies);
        saveList("rentals.json", rentals);
        Platform.exit();
    }


    //HELPER METHODS
    private Movie findMovie(String id) throws Exception {
        for (Movie m : movies)
            if (m.getMovieID().equalsIgnoreCase(id))
                return m;
        throw new MovieNotFoundException("Movie not found.");
    }

    private Person findMember(String id) throws Exception {
        for (Person p : members)
            if (p.getCustomerID().equalsIgnoreCase(id))
                return p;
        throw new Exception("Member not found.");
    }

    private void showAlert(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.show();
    }

    //Auto generate movie IDs
    private String generateMovieID() {
        int max = 0;
        for (Movie m : movies) {
            String numPart = m.getMovieID().substring(2); // remove "MV"
            max = Math.max(max, Integer.parseInt(numPart));
        }
        return "MV" + (max + 1);
    }

    //Auto generate member IDs
    private String generateMemberID(String prefix) { // prefix = ST or EX
        int max = 0;
        for (Person p : members) {
            if (p.getCustomerID().startsWith(prefix)) {
                String num = p.getCustomerID().substring(2);
                max = Math.max(max, Integer.parseInt(num));
            }
        }
        return prefix + (max + 1);
    }

    //JSON METHODS
    private <T> ArrayList<T> loadList(String file, java.lang.reflect.Type type) {
        try (FileReader r = new FileReader(new File("Data", file))) {
            return gson.fromJson(r, type);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private <T> void saveList(String file, ArrayList<T> list) {
        try (FileWriter w = new FileWriter(new File("Data", file))) {
            gson.toJson(list, w);
        } catch (Exception ignored) {}
    }

    // LocalDate adapter
    private static class LocalDateAdapter extends com.google.gson.TypeAdapter<LocalDate> {

        @Override
        public void write(JsonWriter out, LocalDate date) throws IOException {
            if (date == null) {
                out.nullValue();   // write proper JSON null
            } else {
                out.value(date.toString());
            }
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;        // read JSON null properly
            }
            return LocalDate.parse(in.nextString());
        }
    }

    //lIST MOVIES IN RENTAL PANE
    @FXML
    private void openAvailableMovies() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AvailableMovies.fxml"));
            Parent root = loader.load();

            // Get controller of the popup
            AvailableMoviesController controller = loader.getController();
            controller.loadAvailableMovies(movies);

            Stage stage = new Stage();
            stage.setTitle("Available Movies");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}