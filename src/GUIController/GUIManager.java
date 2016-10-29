package GUIController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import BackEndCommands.TurtleCommands.Back;
import BackEndCommands.TurtleCommands.Forward;
import BackEndCommands.TurtleCommands.Right;
import BackEndCommands.TurtleCommands.SetHeading;
import BackEndCommands.TurtleCommands.SetXY;
import BackEndCommands.TurtleCommands.Towards;
import BackEndInternalAPI.ObservableProperties;
import BackEndExternalAPI.CommandParser;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.CommandTypeDetector;
import FrontEndExternalAPI.GUIController;
import GUI.GUIButtonMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIManager implements GUIController {
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
    private GUIDisplay myDisplay;
    private GUIButtonMenu myButtonMenu;
    private CommandTypeDetector commandMaker = new CommandTypeDetector();
    private Command newCommand;
    private Scene myWindow;
    private CommandParser commandParser;
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";

    /**
     *
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
        this.line = lineType;
    }

    @Override
    public void init() {
        //create histoy, console, editor, display, myVariables, button menu
        stage = new Stage();
        stage.setTitle("Slogo");
        myWindow = new Scene(setUpWindow());
//        myWindow.setOnMouseClicked(e -> );
        stage.setScene(myWindow);
        ObservableProperties properties = setupBindings();
        commandParser = new CommandParser(properties);
//        commandParser.setProperties(properties); note: robert commented this out and used in constructor instead
        //properties.getRotateProperty().set(0);
        SetXY fd = new SetXY();
        fd.setProperties(properties);
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(50.0);
        list.add(-75.0);
//        System.out.println(turtle.getX());
//        System.out.println(turtle.getY());
//        //fd.executeCommand(list);
//        System.out.println(turtle.getX());
//        System.out.println(turtle.getY());
//        System.out.println(turtle.getRotate());
        Scene myScene = new Scene(setUpWindow());
//        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        stage.setScene(myScene);
        stage.show();
    }

    private Parent setUpWindow() {
        window = new Pane();
        window.setPrefSize(IDE_WIDTH, IDE_HEIGHT);
        window.getChildren().add(background);
        myConsole = new GUIConsole(window, penColor);
        myEditor = new GUIEditor(window, penColor);
        myHistory = new GUIHistory(window, penColor);
        myVariables = new GUIVariables(window, penColor);
        myDisplay = new GUIDisplay(window, turtle, penColor, line);
        myButtonMenu = new GUIButtonMenu(window, penColor);
        addRunButton();
        addHistoryButton();
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
        myEditor.getBackdrop().widthProperty().bind(window.widthProperty().subtract(630));
        myEditor.getBackdrop().heightProperty().bind(window.heightProperty().subtract(610));
        myEditor.bindNodes(window.widthProperty(), window.heightProperty());
        myButtonMenu.getBackdrop().widthProperty().bind(window.widthProperty().subtract(20));
        myHistory.getBackdrop().heightProperty().bind(window.heightProperty().subtract(610));
    }

    private ObservableProperties setupBindings() {
    	ObservableProperties answer = new ObservableProperties(turtle);
    	answer.getNewLineProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//If new value is true we need to draw a new line
				if (newValue) {
					myDisplay.drawNewLine(answer.getNewLineProperty());
				}
			}
    	});
    	answer.getPathVisibleProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				myDisplay.setVisibility(newValue);
			}
    	});
    	answer.getClearScreenProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//If new value is true we need to draw a new line
				if (newValue) {
					myDisplay.clearScreen(answer.getClearScreenProperty());
				}
			}
    	});
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
    public String getLanguage(){
        return language;
    }

    private void addRunButton(){
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
        run.setTranslateX(700);
        run.setTranslateY(600);
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
        String latestCommand = "";
        for (int i = 0; i < splitCommands.length; i++) {
            if (splitCommands[i].length() > 0) {
                latestCommand += commandParser.getAction(splitCommands[i]);
                if(commandParser.getErrors().size() == 0){
                    myHistory.addCommand(splitCommands[i]);
                    myConsole.addConsole("" + latestCommand);
                    Set<String> keyset = commandParser.getVariables().keySet();
                    for(String s : keyset){
                        myVariables.addVariable(s, commandParser.getVariables().get(s));
                    }
                }
                else {
                    Set<String> errors = commandParser.getErrors();
                    for (String s : errors) {
                        myConsole.addConsole(s);
                    }
                }
//
            }

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

    public Scene getMyWindow(){
        return myWindow;
    }
}
