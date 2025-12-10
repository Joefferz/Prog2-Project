import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Main driver class
 *
 * This class loads data, displays the menu, handles user input,
 * and controls the program such as adding members, adding movies,
 * starting rentals, and processing returns.
 */
public class Main extends Application {
    double x, y = 0;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MovieRental.fxml"));
        Scene scene = new Scene(root, 800, 500);
        stage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        scene.getStylesheets().add(getClass().getResource("Sidebar-Buttons.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
        }
        /**
         * * Initializes storage, loads data from JSON files,
         * and runs the main menu loop.
         *
         * @param args command-line arguments
         */
        public static void main(String[] args) {
            launch(args);
        }
}

