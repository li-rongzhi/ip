package jarvis;

import java.io.IOException;

import jarvis.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Jarvis using fxml.
 */
public class Main extends Application {
    private final Jarvis jarvis = new Jarvis("records.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Jarvis");
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJarvis(jarvis);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
