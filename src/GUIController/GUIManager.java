package GUIController;

import FrontEndExternalAPI.GUIController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIManager implements GUIController{
    public static final int IDE_WIDTH = 1600;
    public static final int IDE_HEIGHT = 1000;
    private Paint penColor;
    private ImageView background, turtle;
    private Stage stage;
    private Pane window;

    public GUIManager(Paint penColor, String background, String turtle){

        this.penColor = penColor;
        Image newImg = new Image(getClass().getClassLoader()
                .getResourceAsStream(background));
        ImageView backgroundImageIDE = new ImageView(newImg);
        backgroundImageIDE.setFitWidth(IDE_WIDTH);
        backgroundImageIDE.setFitHeight(IDE_HEIGHT);
        this.background = backgroundImageIDE;
        newImg = new Image(getClass().getClassLoader()
                .getResourceAsStream(turtle));
        ImageView turtleImageIDE = new ImageView(newImg);
        this.turtle = turtleImageIDE;
    }

    @Override
    public void init() {
        //create histoy, console, editor, display, variables, button menu
        stage = new Stage();
        stage.setTitle("Slogo");
        stage.setScene(new Scene(setUpWindow()));
        stage.show();
    }

    private Parent setUpWindow(){
        window = new Pane();
        window.setPrefSize(IDE_WIDTH, IDE_HEIGHT);
        window.getChildren().add(background);
        return window;
    }

    @Override
    public void getInitialParams() {

    }

    @Override
    public void getCurrentCommand() {

    }

    @Override
    public void passCurrentCommand() {

    }

    @Override
    public void throwError() {

    }

    @Override
    public void getErrors() {

    }

    @Override
    public void storeOldCommand() {

    }

    @Override
    public void returnAction() {

    }
}
