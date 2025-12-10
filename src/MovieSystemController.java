import javafx.application.Platform;
import javafx.fxml.FXML;

import java.lang.reflect.Type;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;

import Exceptions.MemberAlreadyExistsException;
import Exceptions.MovieAlreadyExistsException;
import Exceptions.MovieNotFoundException;
import Exceptions.MovieUnavailableException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class MovieSystemController {

    private ArrayList<Person> members = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Rental> rentals = new ArrayList<>();

    /*private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new Main.LocalDateAdapter())
            .setPrettyPrinting()
            .create();*/

    @FXML
    AnchorPane MoviePane, MemberPane, RentMovie, ReturnMovie;

    @FXML
    Button addMV, addMbr, rentMV, returnMV;

    @FXML
    TableView<Movie> MovieList;
    @FXML
    TableColumn<Movie, String> mvID, mvName, mvStatus;

    @FXML
    TableView<Person> MemberList;
    @FXML
    TableColumn<Person, String> memID, memName, memMembership, info1, info2;

    @FXML
    TableView<Rental> rentList, returnList;

    @FXML
    public void initialize() {

        /*File folder = new File("Data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        members = loadList("members.json",
                new TypeToken<ArrayList<Person>>(){}.getType());

        if (members.isEmpty()) {
            members.add(new Student("Joeffrey", "ST1", "Student", "Vanier", 90));
            members.add(new Student("Thomas", "ST2", "Student", "Vanier", 90));
            members.add(new ExternalMember("Charles", "EX1", "External", "Engineer", "TechCorp"));
            members.add(new ExternalMember("Danush", "EX2", "External", "Doctor", "HealthCareInc"));
        }

        movies = loadList("movies.json",
                new TypeToken<ArrayList<Movie>>(){}.getType());

        if (movies.isEmpty()) {
            movies.add(new Movie("MV1", "Home Alone 2"));
            movies.add(new Movie("MV2", "The Grinch"));
        }

        rentals = loadList("rentals.json",
                new TypeToken<ArrayList<Rental>>(){}.getType());*/
    }

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

    @FXML
    private void addMovie() {

    }

    @FXML
    private void addMember() {

    }

    @FXML
    private void rentMovie() {

    }

    @FXML
    private void returnMovie() {

    }

    @FXML
    private void exitSystem() {
        Platform.exit();
    }
}
