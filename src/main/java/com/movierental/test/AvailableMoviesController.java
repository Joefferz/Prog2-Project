package main.java.com.movierental.test;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class AvailableMoviesController {

    @FXML private TableView<Movie> table;
    @FXML private TableColumn<Movie, String> colID;
    @FXML private TableColumn<Movie, String> colName;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("movieID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("movieName"));
    }

    public void loadAvailableMovies(ArrayList<Movie> movies) {
        ArrayList<Movie> available = new ArrayList<>();

        for (Movie m : movies) {
            if (m.isRentable().equalsIgnoreCase("Available")) {
                available.add(m);
            }
        }

        table.getItems().setAll(available);
    }
}
