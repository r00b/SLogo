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
import javafx.scene.shape.Line;
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
    private ComboBox<Line> lineBox;
    private Color defaultPen = Color.MIDNIGHTBLUE;
    private String defaultBackground = "Nebula";
    private String defaultTurtle = "Turtle";
    private String defaultLanguage = "English";
    private String chosenBackground = "";
    private String chosenTurtle = "";
    private NodeFactory myFactory = new NodeFactory();
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

    /**
     *
     * @param s
     */
    public OptionsMenu (Stage s){
        stage = s;
    }

    /**
     *
     * @return
     */
    public Parent setUpWindow(){
        startWindow = new Pane();
        startWindow.setPrefSize(START_MENU_WIDTH, START_MENU_HEIGHT);
        Image background = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/nebula.jpg"));
        ImageView backgroundImageMainScreen = new ImageView(background);
        backgroundImageMainScreen.setFitWidth(START_MENU_WIDTH + 50);
        backgroundImageMainScreen.setFitHeight(START_MENU_HEIGHT);
        startWindow.getChildren().add(backgroundImageMainScreen);
        addNodes();
        return startWindow;
    }

    private void addNodes(){
        addTitle();
        addRectangle();
        addLineStylePicker();
        changePenColor();
        changeBackground();
        changeSpriteImage();
        changeLanguage();
        addLaunchButton();
    }

    /**
     *
     */
    public void setParameters() {
        setBackgroundString();
        setTurtleString();
        initIDE(chosenBackground, chosenTurtle);
    }

    public void setBackgroundString(){
        switch (getBackgroundBox().getValue()){
            case "Circuits":
                chosenBackground = "images/background.jpg";
                break;
            case "Floating Cubes":
                chosenBackground = "images/floatingCubes.jpg";
                break;
            case "Nebula":
                chosenBackground = "images/nebula.jpg";
                break;
            case "Metal Sheets":
                chosenBackground = "images/dark-wallpaper-2.jpg";
                break;
            case "Spinning Screens":
                chosenBackground = "images/spinningScreens.jpg";
                break;
        }
    }

    public void setTurtleString(){
        switch (getTurtleBox().getValue()){
            case "Turtle":
                chosenTurtle = "images/turtle.png";
                break;
            case "Drake":
                chosenTurtle = "images/drake.png";
                break;
            case "Heart":
                chosenTurtle = "images/heart.png";
                break;
            case "Young Rob":
                chosenTurtle = "images/robby.png";
                break;
            case "Prof. Duvall":
                chosenTurtle = "images/duvall.png";
                break;

        }

    }

    public String getTurtleString(){
        return chosenTurtle;
    }

    /**
     *
     * @param paint
     * @param background
     * @param turtle
     * @param language
     */
    public void setDefaults(Color paint, String background, String turtle, String language){
        defaultPen = paint;
        defaultBackground = background;
        defaultTurtle = turtle;
        defaultLanguage = language;
    }

    /**
     *
     */
    public void addLineStylePicker(){
        System.setProperty("glass.accessible.force", "false");
        Line line0 = new Line(20, 40, 120, 40);
        Line line1 = new Line(20, 40, 120, 40);
        double[] x = {25d, 20d, 5d, 20d};
        line1.getStrokeDashArray().addAll(25d, 20d, 5d, 20d);

        Line line2 = new Line(20, 60, 120, 60);
        line2.getStrokeDashArray().addAll(50d, 40d);

        Line line3 = new Line(20, 80, 120, 80);
        line3.getStrokeDashArray().addAll(25d, 10d);

        Line line4 = new Line(20, 100, 120, 100);
        line4.getStrokeDashArray().addAll(2d);

        Line line5 = new Line(20, 120, 120, 120);
        line5.getStrokeDashArray().addAll(2d, 21d);
        ObservableList<Line> backgroundOptions =
                FXCollections.observableArrayList(line0, line1, line2, line3, line4, line5);
        lineBox = new ComboBox(backgroundOptions);
        lineBox.setValue(line0);
        lineBox.setTranslateX(DROP_DOWN_X_VALUE);
        lineBox.setTranslateY(300);
        Label backgroundLabel = generateLabel("Select pen style", 125, 300);
        getStartWindow().getChildren().addAll(backgroundLabel, lineBox);
    }

    /**
     *
     */
    @Override
    public void changePenColor() {
        penColor = generateColorPicker(defaultPen, DROP_DOWN_X_VALUE, 250);
        Label penLabel = generateLabel("Select pen color", 125, 250);
        startWindow.getChildren().addAll(penColor, penLabel);
    }

    /**
     *
     * @param defaultColor
     * @param x
     * @param y
     * @return
     */
    public ColorPicker generateColorPicker(Color defaultColor, int x, int y){
        ColorPicker penColor = new ColorPicker();
        penColor.setTranslateX(x);
        penColor.setTranslateY(y);
        penColor.setValue(defaultColor);
        return penColor;
    }

    /**
     *
     */
    @Override
    public void changeBackground() {
        System.setProperty("glass.accessible.force", "false");
        backgroundBox = new ComboBox(backgroundOptions);
        backgroundBox.setValue(defaultBackground);
        backgroundBox.setTranslateX(DROP_DOWN_X_VALUE);
        backgroundBox.setTranslateY(400);
        Label backgroundLabel = generateLabel("Select background image", 125, 400);
        startWindow.getChildren().addAll(backgroundLabel, backgroundBox);
    }

    /**
     *
     */
    @Override
    public void changeSpriteImage() {
        System.setProperty("glass.accessible.force", "false");
        turtleBox = new ComboBox(turtleOptions);
        turtleBox.setValue(defaultTurtle);
        turtleBox.setTranslateX(DROP_DOWN_X_VALUE);
        turtleBox.setTranslateY(350);
        Label turtleLabel = generateLabel("Select turtle image", 125, 350);
        startWindow.getChildren().addAll(turtleLabel, turtleBox);
    }

    /**
     *
     * @param text
     * @param x
     * @param y
     * @return
     */
    public Label generateLabel(String text, int x, int y){
        Label label = new Label(text);
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        return label;
    }

    @Override
    public void changeLanguage() {
        System.setProperty("glass.accessible.force", "false");
        languageBox = new ComboBox(languageOptions);
        languageBox.setValue(defaultLanguage);
        languageBox.setTranslateX(DROP_DOWN_X_VALUE);
        languageBox.setTranslateY(450);
        Label languageLabel = generateLabel("Select language", 125, 450);
        startWindow.getChildren().addAll(languageLabel, languageBox);
    }

    /**
     *
     * @return
     */
    public ColorPicker getPenColor(){
        return penColor;
    }

    /**
     *
     * @return
     */
    public Pane getStartWindow() {
        return startWindow;
    }

    /**
     *
     * @return
     */
    public ComboBox<String> getBackgroundBox() {
        return backgroundBox;
    }

    /**
     *
     * @return
     */
    public ComboBox<String> getTurtleBox() {
        return turtleBox;
    }

    /**
     *
     */
    public ComboBox<Line> getLineBox() { return lineBox; }

    /**
     *
     * @return
     */
    public ComboBox<String> getLanguageBox() {
        return languageBox;
    }

    /**
     *
     * @return
     */
    public Stage getStage(){
        return stage;
    }

    /**
     *
     * @return
     */
    public abstract void addTitle();

    /**
     *
     * @return
     */
    public abstract void addRectangle();

    /**
     *
     * @return
     */
    public abstract void addLaunchButton();

    /**
     *
     * @return
     */
    public abstract void initIDE(String background, String turtle);
}
