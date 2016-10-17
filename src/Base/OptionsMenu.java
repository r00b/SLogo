package Base;

import FrontEndInternalAPI.Options;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/17/2016.
 */
public abstract class OptionsMenu implements Options {

    private static final int START_MENU_WIDTH = 700;
    private static final int START_MENU_HEIGHT = 600;
    private static final int DROP_DOWN_X_VALUE = 400;

    private Pane startWindow;
    private Stage stage;
    private ColorPicker penColor;
    private ComboBox<String> backgroundBox, turtleBox, languageBox;
    private ObservableList<String> backgroundOptions =
            FXCollections.observableArrayList(
                    "Circuits",
                    "Floating Cubes",
                    "Nebula",
                    "Metal Sheets",
                    "Spinning Screens"
            );
    private ObservableList<String> turtleOptions =
            FXCollections.observableArrayList(
                    "Turtle",
                    "Drake",
                    "Heart",
                    "Young Rob",
                    "Prof. Duvall"

            );
    private ObservableList<String> languageOptions =
            FXCollections.observableArrayList(
                    "Chinese",
                    "English",
                    "French",
                    "German",
                    "Italian",
                    "Portuguese",
                    "Russian",
                    "Spanish",
                    "Syntax"

            );
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-font: 35 arial;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-font: 35 arial;" +
            "-fx-text-fill: white;";

    public OptionsMenu (Stage s){
        this.stage = s;
    }

    public Parent setUpWindow(){
        startWindow = new Pane();
        startWindow.setPrefSize(START_MENU_WIDTH, START_MENU_HEIGHT);
        Image background = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/background.jpg"));
        ImageView backgroundImageMainScreen = new ImageView(background);
        backgroundImageMainScreen.setFitWidth(START_MENU_WIDTH + 50);
        backgroundImageMainScreen.setFitHeight(START_MENU_HEIGHT);
        startWindow.getChildren().add(backgroundImageMainScreen);
        addTitle();
        addRectangle();
        changePenColor();
        changeBackground();
        changeSpriteImage();
        changeLanguage();
        addLaunchButton();

        return startWindow;
    }

    @Override
    public void changeBackground() {

        System.setProperty("glass.accessible.force", "false");
        backgroundBox = new ComboBox(backgroundOptions);
        backgroundBox.setValue("Floating Cubes");
        backgroundBox.setTranslateX(DROP_DOWN_X_VALUE);
        backgroundBox.setTranslateY(350);
        Label backgroundLabel = generateLabel("Select background image", 125, 350);
        startWindow.getChildren().add(backgroundLabel);
        startWindow.getChildren().add(backgroundBox);
    }

    @Override
    public void changePenColor() {

        penColor = generateColorPicker(Color.MEDIUMPURPLE, DROP_DOWN_X_VALUE, 300);
        Label penLabel = generateLabel("Select pen color", 125, 300);
        startWindow.getChildren().add(penColor);
        startWindow.getChildren().add(penLabel);
    }

    private ColorPicker generateColorPicker(Color defaultColor, int x, int y){
        penColor = new ColorPicker();
        penColor.setTranslateX(x);
        penColor.setTranslateY(y);
        penColor.setValue(defaultColor);
        return penColor;
    }

    private Label generateLabel(String text, int x, int y){
        Label penLabel = new Label(text);
        penLabel.setTranslateX(x);
        penLabel.setTranslateY(y);
        penLabel.setTextFill(Color.WHITE);
        penLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        return penLabel;
    }

    @Override
    public void changeLanguage() {
        System.setProperty("glass.accessible.force", "false");
        languageBox = new ComboBox(languageOptions);
        languageBox.setValue("English");
        languageBox.setTranslateX(DROP_DOWN_X_VALUE);
        languageBox.setTranslateY(450);
        Label languageLabel = generateLabel("Select language", 125, 450);
        startWindow.getChildren().add(languageLabel);
        startWindow.getChildren().add(languageBox);

    }

    @Override
    public void changeSpriteImage() {
        System.setProperty("glass.accessible.force", "false");
        turtleBox = new ComboBox(turtleOptions);
        turtleBox.setValue("Turtle");
        turtleBox.setTranslateX(DROP_DOWN_X_VALUE);
        turtleBox.setTranslateY(400);
        Label turtleLabel = generateLabel("Select turtle image", 125, 400);
        startWindow.getChildren().add(turtleLabel);
        startWindow.getChildren().add(turtleBox);

    }

    public ColorPicker getPenColor(){
        return penColor;
    }

    public Pane getStartWindow() {
        return startWindow;
    }

    public ComboBox<String> getBackgroundBox() {
        return backgroundBox;
    }

    public ComboBox<String> getTurtleBox() {
        return turtleBox;
    }

    public ComboBox<String> getLanguageBox() {
        return languageBox;
    }

    public String getOverButton() {
        return overButton;
    }

    public String getButtonFill() {
        return buttonFill;
    }

    public abstract void addTitle();

    public abstract void addRectangle();

    public abstract void addLaunchButton();
}
