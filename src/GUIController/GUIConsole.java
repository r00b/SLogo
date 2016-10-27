package GUIController;

import BackEndExternalAPI.CommandParser;
import FrontEndExternalAPI.Console;
import GUI.ConsoleHelp;
import GUI.EditorHelp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIConsole implements Console{
    private Pane window;
    private Paint border;
    private Rectangle backdrop;
    private ConsoleHelp helpWindow;
    private ListView<String> list;
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonClicked = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;" +
            "-fx-border: 12px solid; -fx-border-color: white; -fx-background-radius: 15.0;";
    private ObservableList<String> listOfCommands;

    /**
     *
     * @param p
     * @param borderColor
     */
    public GUIConsole(Pane p, Paint borderColor){
        this.window = p;
        this.border = borderColor;
        drawConsole();
        addTextLabel();
        addHelpButton();
        addListView();
    }

    /**
     *
     * @param text
     */
    public void addConsole(String text) {
        listOfCommands.add(text);
        list.setItems(listOfCommands);
    }

    /**
     *
     */
    private void addListView(){
        list = new ListView<String>();
        list.setOrientation(Orientation.VERTICAL);
        list.setTranslateX(20);
        list.setTranslateY(380);
        list.setPrefSize(570, 200);
        list.opacityProperty().setValue(0.8);
        listOfCommands = FXCollections.observableArrayList();
        list.setOnMouseEntered(e -> {
            list.opacityProperty().setValue(0.8);
            backdrop.opacityProperty().setValue(0.8);
        });
        list.setOnMouseExited(e -> list.opacityProperty().setValue(0.5));
        window.getChildren().add(list);
    }

    @Override
    /**
     *
     */
    public void printResult() {
        
    }

    private void drawConsole(){
        backdrop = new Rectangle(600, 240, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(350);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }

    private void addTextLabel(){
        Text label = new Text("Console");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        label.setTranslateX(20);
        label.setTranslateY(370);
        window.getChildren().add(label);
    }

    private void addHelpButton(){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/help.png"));
        ImageView helpButton = new ImageView(newImage);
        helpButton.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        helpButton.setTranslateX(backdrop.getTranslateX() + backdrop.getWidth() - 35);
        helpButton.setTranslateY(backdrop.getTranslateY() + 10);
        helpButton.setFitWidth(30);
        helpButton.setFitHeight(30);
        helpButton.setOnMouseClicked(e -> helpHandler());
        window.getChildren().add(helpButton);
    }
    
    private void helpHandler(){
        Stage s = new Stage();
        helpWindow = new ConsoleHelp(s);
        helpWindow.init();
    }
}
