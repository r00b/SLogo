package GUIController;

import FrontEndExternalAPI.Console;
import GUI.ConsoleHelp;
import javafx.beans.property.ReadOnlyDoubleProperty;
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
    private static final int BACKDROP_X = 620;//10;
    private static final int BACKDROP_Y = 600;//350;
    private static final int BACKDROP_WIDTH = 600;
    private static final int BACKDROP_HEIGHT = 240;
    private Pane window;
    private Paint border;
    private Rectangle backdrop;
    private ConsoleHelp helpWindow;
    private ImageView helpButton;
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
        addClearButton();
    }

    /**
     *
     * @param text
     */
    public void addConsole(String text) {
        listOfCommands.add(text);
        list.setItems(listOfCommands);
    }

    private void drawConsole(){
        backdrop = new Rectangle(BACKDROP_WIDTH, BACKDROP_HEIGHT, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateX(BACKDROP_X);
        backdrop.setTranslateY(BACKDROP_Y);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }
    /**
     *
     */
    private void addListView(){
        list = new ListView<String>();
        list.setOrientation(Orientation.VERTICAL);
        list.setTranslateX(BACKDROP_X + 10);
        list.setTranslateY(BACKDROP_Y + 30);
        list.setPrefSize(BACKDROP_WIDTH - 30, BACKDROP_HEIGHT - 40);
        list.opacityProperty().setValue(0.8);
        listOfCommands = FXCollections.observableArrayList();
        list.setOnMouseEntered(e -> {
            list.opacityProperty().setValue(0.8);
            backdrop.opacityProperty().setValue(0.8);
        });
        list.setOnMouseExited(e -> list.opacityProperty().setValue(0.5));
        window.getChildren().add(list);
    }

    private void addTextLabel(){
        Text label = new Text("Console");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        label.setTranslateX(BACKDROP_X + 10);
        label.setTranslateY(BACKDROP_Y + 20);
        window.getChildren().add(label);
    }

    private void addHelpButton(){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("Images/help.png"));
        helpButton = new ImageView(newImage);
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

    private void addClearButton(){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("Images/clear.png"));
        ImageView clearImg = new ImageView(newImage);
        Button clear = newButton("Clear", clearImg, (int) backdrop.getTranslateX() + 100, (int) backdrop.getTranslateY());
        clear.setOnMouseClicked(e -> listOfCommands.clear());
        window.getChildren().add(clear);
    }

    private Button newButton(String text, ImageView imgV, int x, int y){
        imgV.setFitWidth(25);
        imgV.setFitHeight(25);
        Button run = new Button(text, imgV);
        run.setStyle(overButton);
        run.setOnMouseEntered(e -> {
            run.setStyle(buttonFill);
            backdrop.opacityProperty().setValue(0.8);
        });
        run.setOnMouseExited(e -> run.setStyle(overButton));
        run.setTranslateX(x);
        run.setTranslateY(y);
        return run;
    }

    /**
     *
     * @param width
     * @param height
     */
    public void bindNodes(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height){
        list.prefWidthProperty().bind(width.subtract(650));
        list.prefHeightProperty().bind(height.subtract(660));
        helpButton.translateXProperty().bind(width.subtract(50));
    }


    /**
     *
     * @return
     */
    public Rectangle getBackdrop(){
        return backdrop;
    }

    
}
