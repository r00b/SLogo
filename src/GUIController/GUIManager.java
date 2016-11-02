package GUIController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import BackEndCommands.TurtleCommands.SetXY;
import BackEndExternalAPI.CommandParser;

import BackEndInternalAPI.DisplayProperties;
import BackEndInternalAPI.ObservableComposite;

import FrontEndExternalAPI.GUIController;
import FrontEndInternalAPI.ButtonMenu;
import GUI.HelpMenu;
import GUI.OptionsPopup;
import javafx.beans.property.SimpleStringProperty;
//<<<<<<< HEAD
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//=======

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//>>>>>>> cb214ea01bc3998ac851846c19dee87231d16a99
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//import org.apache.commons.lang.ArrayUtils;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIManager implements GUIController {
    /**
     * http://www.javaworld.com/article/2071784/enterprise-java/java-xml-mapping-made-easy-with-jaxb-2-0.html
     * https://docs.oracle.com/javase/tutorial/jaxp/xslt/generatingXML.html#gghhj
     */
    public static final int IDE_WIDTH = 1400;
    public static final int IDE_HEIGHT = 800;
    private Color penColor;
    private ImageView background, turtle;
    private Stage stage;
    private Pane window;
    private String backgroundStr, turtleStr, language;
    private Line line;

    private GUIConsole myConsole;
    private GUIEditor myEditor;
    private GUIHistory myHistory;
    private GUIVariables myVariables;
    private ObservableComposite turtleProperties;
    private DisplayProperties displayProperties;
    private GUIDisplay myDisplay;
    private GUIButtonMenu myButtonMenu;
    private Scene myWindow;
    private CommandParser commandParser;
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";

    private SimpleStringProperty myLanguage;

    /**
     * @param penColor
     * @param background
     * @param turtle
     * @param language
     */
    public GUIManager(Color penColor, String background, String turtle, String language, Line lineType) {
        this.penColor = penColor;
        this.backgroundStr = background;
        this.turtleStr = turtle;
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
        this.language = language;
        myLanguage = new SimpleStringProperty(language);
        this.line = lineType;

    }

    @Override
    public void init() {
        //create histoy, console, editor, display, myVariables, button menu
        stage = new Stage();
        stage.setTitle("Slogo");
        Scene myScene = new Scene(setUpWindow());

        myWindow = myScene;
//        myWindow.setOnMouseClicked(e -> );
        stage.setScene(myWindow);

        turtleProperties = setupBindings();
        displayProperties = new DisplayProperties(myDisplay);
        commandParser = new CommandParser(myLanguage, turtleProperties, displayProperties, myVariables);
        myVariables.setVariableSetter(commandParser);
//        commandParser.setProperties(properties); note: robert commented this out and used in constructor instead
        //properties.getRotateProperty().set(0);
        SetXY fd = new SetXY();
        fd.setProperties(turtleProperties);
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(50.0);
        list.add(-75.0);
        stage.setScene(myScene);
        stage.show();
    }

    private Parent setUpWindow() {
        window = new Pane();
        window.setPrefSize(IDE_WIDTH, IDE_HEIGHT);
        window.getChildren().add(background);
        myConsole = new GUIConsole(window, penColor);
        myEditor = new GUIEditor(window, penColor, stage);
        myHistory = new GUIHistory(window, penColor);
        myVariables = new GUIVariables(window, penColor);
        myDisplay = new GUIDisplay(window, turtle, penColor, line);
        myButtonMenu = new GUIButtonMenu(window, penColor);
        addRunButton();
        addHistoryButton();
        addMoreTurtlesButton();
//        setParamBindings(); //How should I make this work
        setSizeBindings();
        return window;
    }

    //don't think i understand binding that well yet but this doesn't work for some reason

    private void setSizeBindings() {
        background.fitWidthProperty().bind(window.widthProperty());
        background.fitHeightProperty().bind(window.heightProperty());
        myDisplay.bindNodes(window.widthProperty());
//        myDisplay.getGraph().fitWidthProperty().bind(window.widthProperty().subtract(630));
        myConsole.getBackdrop().widthProperty().bind(window.widthProperty().subtract(630));
        myConsole.getBackdrop().heightProperty().bind(window.heightProperty().subtract(610));
        myConsole.bindNodes(window.widthProperty(), window.heightProperty());
        myButtonMenu.getBackdrop().widthProperty().bind(window.widthProperty().subtract(20));
        myHistory.getBackdrop().heightProperty().bind(window.heightProperty().subtract(610));
//        myHistory.getListView().heightProperty().bind
    }

    private ObservableComposite setupBindings() {
//    	ObservableProperties property = new ObservableProperties(turtle, myDisplay, 1);
        ObservableComposite answer = new ObservableComposite(myDisplay);
        return answer;
    }


    //    private void handleKeyInput (KeyCode code){
//        switch (code) {
//            case ENTER:
//                newCommand = commandMaker.getCommandObj(myEditor.enterPressed());
//                myEditor.startNewCommand();
//                turtle.setTranslateX(turtle.getTranslateX() - 10);
//                break;
//            default:
//        }
//    }
    public String getLanguage() {
        return language;
    }

    private void addRunButton() {
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/play.png"));
        ImageView imgV = new ImageView(newImage);
        imgV.setFitWidth(25);
        imgV.setFitHeight(25);
        Button run = new Button("Run", imgV);
        run.setStyle(overButton);
        run.setOnMouseEntered(e -> {
            run.setStyle(buttonFill);
            myEditor.getBackdrop().opacityProperty().setValue(0.8);
        });
        run.setOnMouseExited(e -> run.setStyle(overButton));
        run.setOnMouseClicked(e -> returnAction());
        run.setTranslateX(80);
        run.setTranslateY(350);
        window.getChildren().add(run);
    }

    private void addHistoryButton() {
        Button hist = new Button("Load");
        hist.setStyle(overButton);
        hist.setOnMouseEntered(e -> {
            hist.setStyle(buttonFill);
            myEditor.getBackdrop().opacityProperty().setValue(0.8);
        });
        hist.setOnMouseExited(e -> hist.setStyle(overButton));
        hist.setOnMouseClicked(e -> getAndLoadHistoryCommand());
        hist.setTranslateX(528);
        hist.setTranslateY(705);
        window.getChildren().add(hist);
    }

    private void addMoreTurtlesButton() {
        TextField enterID = new TextField();
        enterID.setTranslateX(1110);
        enterID.setTranslateY(125);
        enterID.setPromptText("Enter your new turtle's ID");
        enterID.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    turtleProperties.setNewTurtle(Double.parseDouble(enterID.getText()));
                }
            }
        });
        window.getChildren().add(enterID);
//        Button addTurtles = new Button("Add Turtles");
//        addTurtles.setTranslateX(1110);
//        addTurtles.setTranslateY(125);
//        addTurtles.setStyle(overButton);
//        addTurtles.setOnMouseEntered(e -> addTurtles.setStyle(buttonFill));
//        addTurtles.setOnMouseExited(e -> addTurtles.setStyle(overButton));
//        addTurtles.setOnMouseClicked(e -> {
//
//        });
//        window.getChildren().add(addTurtles);
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
        String fullText = myEditor.getCurrentText();
        myEditor.startNewCommand();
        String newCommands = fullText.substring(lookForLatest(fullText));
        String[] splitCommands = newCommands.split("\n");

        double result = commandParser.executeCommands(splitCommands);

        if (commandParser.getErrors().size() == 0) {
            myConsole.addConsole(Double.toString(result));
        } else {
            commandParser.getErrors().forEach(myConsole::addConsole);
        }
    }

    private void getAndLoadHistoryCommand() {
        String redoCommand = myHistory.getRedoCommand();
        myEditor.redoCommand(redoCommand);
    }

    private int lookForLatest(String fullText) {
        int startIndex = -1;
        for (int i = fullText.length() - 1; i >= 0; i--) {
            if (fullText.charAt(i) == '>') {
                startIndex = i + 2;
                break;
            }
        }
        return startIndex;
    }

    public Scene getMyWindow() {
        return myWindow;
    }

    private class GUIButtonMenu implements ButtonMenu {
        private Pane window;
        private Paint border;
        private Rectangle backdrop;
        private Stage s = new Stage();
        private String defaultBackground = "Nebula";
        private String defaultLanguage = "English";
        private OptionsPopup myOptions;
        private HelpMenu myHelpMenu;
        private ComboBox<String> backgroundBox, languageBox;
        private ObservableList<String> backgroundOptions =
                FXCollections.observableArrayList(
                        "Circuits",
                        "Floating Cubes",
                        "Nebula",
                        "Metal Sheets",
                        "Spinning Screens"
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
                "-fx-text-fill: white;";
        private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
                "-fx-background-radius: 20;" +
                "-fx-text-fill: white;";

        /**
         * @param p
         * @param borderColor
         */
        public GUIButtonMenu(Pane p, Paint borderColor) {
            this.window = p;
            this.border = borderColor;
//        myOptions = new OptionsPopup();
            drawButtonMenu();
            addTextLabel();
            addButtons();
            addComboBoxes();
        }

        private void drawButtonMenu() {
            backdrop = new Rectangle(1580, 90, Color.WHITE);
            backdrop.setStroke(border);
            backdrop.setStrokeWidth(5);
            backdrop.setTranslateY(10);
            backdrop.setTranslateX(10);
            backdrop.opacityProperty().setValue(0.5);
//        backdrop.setOnMouseMoved(e -> handle(e));
            backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
            backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
            window.getChildren().add(backdrop);
        }

        private void addTextLabel() {
            Text label = new Text("Options");
            label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
            label.setTranslateX(20);
            label.setTranslateY(30);
            window.getChildren().add(label);
        }

        public void loadFile() throws FileNotFoundException {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            //fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setTitle("Load Defaults");
            File file = fileChooser.showOpenDialog(stage);
            FileReader fr = new FileReader(file);
            BufferedReader buffRead = new BufferedReader(fr);
            String line = null;
            int count = 0;
            try {
                while ((line = buffRead.readLine()) != null) {
                    if (count == 0) {
                        languageBox.setValue(line);
                    } else if (count == 1) {
                        backgroundBox.setValue(line);
                    } else if (count == 2) {
                        //myDisplay.setPenColor(line);
                    } else if (count == 3) {
                        turtleStr = "images/" + line;

                    }
                    count++;
                }
                //TODO: Actually update based on what was loaded
                buffRead.close();

            } catch (IOException ex) {
                System.out.println(
                        "Error reading file '"
                                + file + "'");
                // Or we could just do this:
                // ex.printStackTrace();

            }
        }

        public void saveFile() {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setTitle("Save Defaults");
            //Show save file dialog
            File file = fileChooser.showSaveDialog(stage);
            String str = new String();
            if (myDisplay.getTurtleStr() == null) {
                str = turtleStr;
            } else
                str = myDisplay.getTurtleStr();

            try {
                FileWriter fileWriter;
                fileWriter = new FileWriter(file);
                fileWriter.write(languageBox.getValue() + "\n" +
                        backgroundBox.getValue() + "\n" +
                        myDisplay.getPenColor().toString() + "\n" +
                        str.substring(7));

                fileWriter.close();
            } catch (IOException ex) {
                System.out.println("ERROR");
            }
        }

        /**
         *
         */
        @Override
        public void addButtons() {
            Image newImage = new Image(getClass().getClassLoader()
                    .getResourceAsStream("images/play.png"));
            ImageView imgV = new ImageView(newImage);
            Button play = newButton("PLAY", imgV, 30, 40);
            newImage = new Image(getClass().getClassLoader()
                    .getResourceAsStream("images/pause.png"));
            imgV = new ImageView(newImage);
            Button pause = newButton("PAUSE", imgV, 130, 40);
            newImage = new Image(getClass().getClassLoader()
                    .getResourceAsStream("images/stop.png"));
            imgV = new ImageView(newImage);
            Button stop = newButton("STOP", imgV, 240, 40);
//        newImage = new Image(getClass().getClassLoader()
//                .getResourceAsStream("images/options.png"));
//        imgV = new ImageView(newImage);
//        Button options = newButton("OPTIONS", imgV, 340, 40);
//        options.setOnMouseClicked(e -> optionsHandler());
            newImage = new Image(getClass().getClassLoader()
                    .getResourceAsStream("images/help.png"));
            imgV = new ImageView(newImage);
            Button help = newButton("HELP", imgV, 337, 40);
            help.setOnMouseClicked(e -> helpHandler());
            Button save = newButton("Save Defaults", null, 750, 50);
            save.setOnMouseClicked(e -> saveFile());
            window.getChildren().add(save);
            Button load = newButton("Load Defaults", null, 860, 50);
            load.setOnMouseClicked(e -> {
                try {
                    loadFile();
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            window.getChildren().add(load);
            window.getChildren().add(play);
            window.getChildren().add(pause);
            window.getChildren().add(stop);
            window.getChildren().add(help);
//        window.getChildren().add(options);
        }


        public Button newButton(String text, ImageView imgV, int x, int y) {
            if (imgV != null) {
                imgV.setFitWidth(40);
                imgV.setFitHeight(40);
            }
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

        private void addComboBoxes() {
            System.setProperty("glass.accessible.force", "false");
            backgroundBox = new ComboBox<String>(backgroundOptions);
            backgroundBox.setValue(defaultBackground);
            backgroundBox.setTranslateX(440);
            backgroundBox.setTranslateY(50);
//        backgroundBox.setStyle(buttonFill);
//        backgroundBox.style
            window.getChildren().add(backgroundBox);
            languageBox = new ComboBox<String>(languageOptions);
            languageBox.setValue(defaultLanguage);
            languageBox.setTranslateX(610);
            languageBox.setTranslateY(50);
            window.getChildren().add(languageBox);
        }

        /**
         * @param paint
         * @param background
         * @param turtle
         * @param language
         */
        public void setDefaults(Color paint, String background, String turtle, String language) {
            myOptions = new OptionsPopup(s, paint, background, turtle, language);
        }
//
//    private void optionsHandler(){
//        myOptions.initPopup();
//    }

        private void helpHandler() {
            myHelpMenu = new HelpMenu(s);
            myHelpMenu.init();
        }

        /**
         * @return
         */
        public Rectangle getBackdrop() {
            return backdrop;
        }
    }
}
