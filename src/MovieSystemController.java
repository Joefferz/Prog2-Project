import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

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
    @FXML private TextField memberID, memberName, membership, persoInfo1, persoInfo2;
    @FXML private TextArea memInfo;

    // Rent
    @FXML private TableView<Rental> rentList;
    @FXML private TextField rentMovID, rentMemID;
    @FXML private TextArea rentInfo;

    // Return
    @FXML private TableView<Rental> returnList;
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
        memID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        memName.setCellValueFactory(new PropertyValueFactory<>("name"));
        memMembership.setCellValueFactory(new PropertyValueFactory<>("membership"));
        info1.setCellValueFactory(new PropertyValueFactory<>("infoOne"));
        info2.setCellValueFactory(new PropertyValueFactory<>("infoTwo"));

        MemberList.getItems().setAll(members);

        // Rental Table
        rentList.getItems().setAll(rentals);
        returnList.getItems().setAll(rentals);
    }

    //PANE SWITCHING
    @FXML
    private void showMovies() {
        MoviePane.setVisible(true);
        MemberPane.setVisible(false);
        RentMovie.setVisible(false);
        ReturnMovie.setVisible(false);
    }

    @FXML
    private void showMembers() {
        MoviePane.setVisible(false);
        MemberPane.setVisible(true);
        RentMovie.setVisible(false);
        ReturnMovie.setVisible(false);
    }

    @FXML
    private void showRentals() {
        MoviePane.setVisible(false);
        MemberPane.setVisible(false);
        RentMovie.setVisible(true);
        ReturnMovie.setVisible(false);
    }

    @FXML
    private void showRentalsToReturn() {
        MoviePane.setVisible(false);
        MemberPane.setVisible(false);
        RentMovie.setVisible(false);
        ReturnMovie.setVisible(true);
    }

    //ADD MOVIE
    @FXML
    private void addMovie() {
        try {
            String id = movieID.getText().trim().toUpperCase();
            String name = movieName.getText().trim();

            if (!id.matches("MV\\d{1,3}"))
                throw new Exception("Movie ID must be MV1–MV999");

            for (Movie m : movies) {
                if (m.getMovieID().equalsIgnoreCase(id))
                    throw new Exception("Movie ID already exists.");
            }

            Movie m = new Movie(id, name);
            movies.add(m);
            MovieList.getItems().add(m);

            saveList("movies.json", movies);

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    //ADD MEMBER
    @FXML
    private void addMember() {
        try {
            String id = memberID.getText().toUpperCase().trim();
            String name = memberName.getText().trim();
            String mem = membership.getText().trim();
            String infoA = persoInfo1.getText().trim();
            String infoB = persoInfo2.getText().trim();

            if (!id.matches("(ST|EX)\\d{1,3}"))
                throw new Exception("Member ID must be ST### or EX###.");

            for (Person p : members) {
                if (p.getCustomerID().equalsIgnoreCase(id))
                    throw new Exception("Member ID already exists.");
            }

            Person p;
            if (mem.equalsIgnoreCase("Student")) {
                p = new Student(name, id, mem, infoA, Integer.parseInt(infoB));
            } else if (mem.equalsIgnoreCase("External")) {
                p = new ExternalMember(name, id, mem, infoA, infoB);
            } else {
                throw new Exception("Membership must be Student or External.");
            }

            members.add(p);
            MemberList.getItems().add(p);
            saveList("members.json", members);

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    //RENT
    @FXML
    private void rentMovie() {
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

            saveList("movies.json", movies);
            saveList("rentals.json", rentals);

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    //RETURNING MOVIE
    @FXML
    private void returnMovie() {
        try {
            String mid = returnMovID.getText().toUpperCase().trim();
            String cid = returnMemID.getText().toUpperCase().trim();
            LocalDate date = returnDate.getValue();

            if (date == null)
                throw new Exception("Select a return date.");

            Rental active = null;
            for (Rental r : rentals) {
                if (r.getCustomerRenterID().equalsIgnoreCase(cid) &&
                        r.getMovieRentedID().equalsIgnoreCase(mid) &&
                        r.getDateReturned() == null) {
                    active = r;
                }
            }

            if (active == null)
                throw new Exception("No active rental found.");

            active.setDateReturned(date);

            Movie m = findMovie(mid);
            m.updateAvailability(); // becomes Available again

            rentList.getItems().setAll(rentals);
            returnList.getItems().setAll(rentals);

            saveList("movies.json", movies);
            saveList("rentals.json", rentals);

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    //EXIT SYSTEM
    @FXML
    private void exitSystem() {
        Platform.exit();
    }


    //HELPER METHODS
    private Movie findMovie(String id) throws Exception {
        for (Movie m : movies)
            if (m.getMovieID().equalsIgnoreCase(id))
                return m;
        throw new Exception("Movie not found.");
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
            out.value(date.toString());
        }
        @Override
        public LocalDate read(JsonReader in) throws IOException {
            return LocalDate.parse(in.nextString());
        }
    }
}
