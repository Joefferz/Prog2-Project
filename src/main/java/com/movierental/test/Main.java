package main.java.com.movierental.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Entry point for the Movie Rental application.
 * <p>
 * This class initializes and launches the JavaFX GUI. It loads the main FXML layout,
 * applies the stylesheet, and sets up basic window dragging functionality for
 * an undecorated stage.
 * </p>
 * <p>
 * The main responsibilities include:
 * <ul>
 *     <li>Loading the FXML layout for the main interface.</li>
 *     <li>Setting up the scene and stage.</li>
 *     <li>Applying CSS stylesheets.</li>
 *     <li>Enabling click-and-drag movement of the window.</li>
 * </ul>
 * </p>
 */
public class Main extends Application {
    double x, y = 0;

    /**
     * Starts the JavaFX application by setting up the stage and scene.
     *
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MovieRental.fxml"));
        Scene scene = new Scene(root, 800, 500);
        stage.initStyle(StageStyle.UNDECORATED);

        //Enabling dragging of undecorated window
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        // Apply stylesheet
        scene.getStylesheets().add(getClass().getResource("main/resources/Sidebar-Buttons.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
        }

        /**
        * Launches the JavaFX application.
        *
        * @param args command-line arguments (ignored)
        */
        public static void main(String[] args) {
            launch(args);
        }
}

