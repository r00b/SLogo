package GUI;

import FrontEndInternalAPI.ButtonMenu;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/22/2016.
 */
public class HelpMenu {
    private static final int HELP_MENU_WIDTH = 700;
    private static final int HELP_MENU_HEIGHT = 600;
    private static final int X_POS_BUTTON = 150;
    private Stage myStage;
    private Pane helpWindow;
    private HelpMenu editorHelp, historyHelp, variablesHelp;
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";

    public HelpMenu(Stage s){
        myStage = s;
    }

    public void init(){
        myStage.setTitle("Help");
        myStage.setScene(new Scene(setUpWindow()));
        myStage.show();
    }

    public Parent setUpWindow(){
        helpWindow = new Pane();
        helpWindow.setPrefSize(HELP_MENU_WIDTH, HELP_MENU_HEIGHT);
        Image background = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/background.jpg"));
        ImageView backgroundImageMainScreen = new ImageView(background);
        backgroundImageMainScreen.setFitWidth(HELP_MENU_WIDTH + 50);
        backgroundImageMainScreen.setFitHeight(HELP_MENU_HEIGHT);
        helpWindow.getChildren().add(backgroundImageMainScreen);

        addNodes();
        return helpWindow;
    }

    public void addNodes(){
        helpWindow.getChildren().addAll(addButton("IDE Style and color", X_POS_BUTTON, 200),
                addButton("Editor", X_POS_BUTTON, 250),
                addButton("Variables", X_POS_BUTTON, 300),
                addButton("History", X_POS_BUTTON, 350),
                addButton("Commands", X_POS_BUTTON + 300, 200),
                addButton("Display", X_POS_BUTTON + 300, 250),
                addButton("Controls", X_POS_BUTTON + 300, 300));

        Text titleText = new Text("Which option do you need help with?");
        titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        titleText.setFill(Color.WHITE);
        titleText.setTranslateX(100);
        titleText.setTranslateY(100);
        Button editor = addButton("Editor", X_POS_BUTTON, 250);
        editor.setOnMouseClicked(e -> editorHandler());
        Button history = addButton("History", X_POS_BUTTON, 350);
        history.setOnMouseClicked(e -> historyHandler());
        Button variables = addButton("Variables", X_POS_BUTTON, 300);
        variables.setOnMouseClicked(e -> variablesHandler());

        helpWindow.getChildren().add(addRectangle());
        helpWindow.getChildren().add(titleText);
        helpWindow.getChildren().addAll(addButton("IDE Style and color", X_POS_BUTTON, 200),
                editor,
                variables,
                history,
                addButton("Commands", X_POS_BUTTON + 300, 200),
                addButton("Display", X_POS_BUTTON + 300, 250),
                addButton("Controls", X_POS_BUTTON + 300, 300));

    }

    public Button addButton(String text, int x, int y){
        Button newButton;
        newButton = new Button(text);
        newButton.setStyle(overButton);
        newButton.setOnMouseEntered(e -> {
            newButton.setStyle(buttonFill);
        });
        newButton.setOnMouseExited(e -> newButton.setStyle(overButton));
        newButton.setTranslateX(x);
        newButton.setTranslateY(y);
        return newButton;
    }

    public Rectangle addRectangle(){
        Rectangle backdrop = new Rectangle(520, 410, Color.MIDNIGHTBLUE);
        backdrop.setTranslateY(70);
        backdrop.setTranslateX(80);
        backdrop.opacityProperty().setValue(0.5);
        return backdrop;
    }

    private void editorHandler(){
        Stage s = new Stage();
        editorHelp = new EditorHelp(s);
        editorHelp.init();
    }

    private void historyHandler(){
        Stage s = new Stage();
        historyHelp = new HistoryHelp(s);
        historyHelp.init();
    }

    private void variablesHandler(){
        Stage s = new Stage();
        variablesHelp = new VariablesHelp(s);
        variablesHelp.init();
    }

    public Stage getStage(){
        return myStage;
    }

    public Pane getWindow(){
        return helpWindow;
    }
}
