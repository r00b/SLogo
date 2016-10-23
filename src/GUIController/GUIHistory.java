package GUIController;

import java.util.ArrayList;
import FrontEndExternalAPI.History;
import GUI.EditorHelp;
import GUI.HistoryHelp;
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
public class GUIHistory implements History {
    private Pane window;
    private Paint border;
    private Rectangle backdrop;
    private static int numCommands = 0;
    private ListView<Button> list;
    private ObservableList<Button> oldCommands;
    private HistoryHelp helpWindow;
    private String redoCommand;
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

    public GUIHistory(Pane p, Paint bordercoloir){
        this.window = p;
        this.border = bordercoloir;
        drawHistory();
        addTextLabel();
        addHelpButton();
        addClearButton();
        addListView();
    }

    private void drawHistory(){
        backdrop = new Rectangle(600, 220, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(660);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }
    private void addTextLabel(){
        Text label = new Text("History");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        label.setTranslateX(20);
        label.setTranslateY(680);
        window.getChildren().add(label);
    }

    private void addHelpButton(){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/help.png"));
        ImageView helpButton = new ImageView(newImage);
        helpButton.setOnMouseClicked(e -> helpHandler());
        helpButton.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        helpButton.setTranslateX(backdrop.getTranslateX() + backdrop.getWidth() - 35);
        helpButton.setTranslateY(backdrop.getTranslateY() + 10);
        helpButton.setFitWidth(30);
        helpButton.setFitHeight(30);
        window.getChildren().add(helpButton);
    }

    private void helpHandler(){
        Stage s = new Stage();
        helpWindow = new HistoryHelp(s);
        helpWindow.init();
    }
    
    private void addClearButton(){
        Button clearButton = new Button("Clear");
        clearButton.setStyle(overButton);
        clearButton.setOnMouseEntered(e -> {
            clearButton.setStyle(buttonFill);
        });
        clearButton.setOnMouseExited(e -> clearButton.setStyle(overButton));
        
        clearButton.setTranslateX(525);
        clearButton.setTranslateY(670);
        clearButton.setOnMouseClicked(e -> clear());
        window.getChildren().add(clearButton);
    }

    public Rectangle getBackdrop(){
        return backdrop;
    }


    @Override
    public void addCommand(String text) {
        numCommands++;
        Button newCommand = new Button(text);
        newCommand.setStyle(overButton);
        newCommand.setOnMouseEntered(e -> {
            newCommand.setStyle(buttonFill);
            backdrop.opacityProperty().setValue(0.8);
        });
        newCommand.setOnMouseExited(e -> newCommand.setStyle(overButton));
        newCommand.setOnMouseClicked(e -> {
            unBold();
            newCommand.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            callCommand(newCommand.getText());
        });
        oldCommands.add(0, newCommand);
        list.setItems(oldCommands);
//        window.getChildren().add(list);
    }

    private void unBold(){
        for(int i = 0; i < list.getItems().size(); i++){
            list.getItems().get(i).setFont(Font.font("Verdana", 15));
        }
    }

    private void addListView(){
        list = new ListView<Button>();
        list.setOrientation(Orientation.HORIZONTAL);
        list.setTranslateX(20);
        list.setTranslateY(685);
        list.setPrefSize(500, 95);
        oldCommands = FXCollections.observableArrayList();
        window.getChildren().add(list);
    }

    @Override
    public void callCommand(String str) {
        redoCommand = str;
        //System.out.println(redoCommand);
    }
    
    public String getRedoCommand(){
        return redoCommand;
    }
    
    private void clear(){
        oldCommands.clear();
    }
}
