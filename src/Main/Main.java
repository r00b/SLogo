package Main;

import GUIController.GUIStartMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Starts the program and sets the stage
 *
 * @author Delia
 */
public class Main extends Application {
    Stage stage;

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage primaryStage) {
        stage = primaryStage;
        Scene scene = new Scene(new GUIStartMenu(primaryStage).setUpWindow());
        primaryStage.setTitle("SLogo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}