package GUIController;

import java.util.ArrayList;

import BackEndCommands.TurtleCommands.Back;
import BackEndCommands.TurtleCommands.Forward;
import BackEndCommands.TurtleCommands.Right;
import BackEndCommands.TurtleCommands.SetHeading;
import BackEndCommands.TurtleCommands.SetXY;
import BackEndCommands.TurtleCommands.Towards;
import BackEndInternalAPI.ObservableProperties;
import FrontEndExternalAPI.GUIController;
import GUI.GUIButtonMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIManager implements GUIController{
    public static final int IDE_WIDTH = 1600;
    public static final int IDE_HEIGHT = 900;
    private Color penColor;
    private ImageView background, turtle;
    private Stage stage;
    private Pane window;
    private String backgroundStr, turtleStr, language;

    private GUIConsole myConsole;
    private GUIEditor myEditor;
    private GUIHistory myHistory;
    private GUIVariables myVariables;
    private GUIDisplay myDisplay;
    private GUIButtonMenu myButtonMenu;

    public GUIManager(Color penColor, String background, String turtle, String language){

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
    }

    @Override
    public void init() {
        //create histoy, console, editor, display, variables, button menu
        stage = new Stage();
        stage.setTitle("Slogo");
        stage.setScene(new Scene(setUpWindow()));
        ObservableProperties properties = setupBindings();
        //properties.getRotateProperty().set(0);
        SetXY fd = new SetXY();
        fd.setProperties(properties);
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(50.0);
        list.add(-75.0);
        //System.out.println(turtle.getY());
        fd.executeCommand(list);
        System.out.println(turtle.getX());
        System.out.println(turtle.getY());
        System.out.println(turtle.getRotate());
        stage.show();
    }

    private Parent setUpWindow(){
        window = new Pane();
        window.setPrefSize(IDE_WIDTH, IDE_HEIGHT);
        window.getChildren().add(background);
        myConsole = new GUIConsole(window, penColor);
        myEditor = new GUIEditor(window, penColor);
        myHistory = new GUIHistory(window, penColor);
        myVariables = new GUIVariables(window, penColor);
        myDisplay = new GUIDisplay(window, turtle);
        myButtonMenu = new GUIButtonMenu(window, penColor);
        myButtonMenu.setDefaults(penColor, backgroundStr, turtleStr, language);
//        setParamBindings(); //How should I make this work
        setSizeBindings();
        return window;
    }

    //don't think i understand binding that well yet but this doesn't work for some reason
    private void setParamBindings(){
        background.imageProperty().bind(myButtonMenu.getOptionsPopup().getChosenBackground().imageProperty());
        turtle.imageProperty().bind(myButtonMenu.getOptionsPopup().getChosenTurtle().imageProperty());
    }

    private void setSizeBindings(){
        background.fitWidthProperty().bind(window.widthProperty());
        background.fitHeightProperty().bind(window.heightProperty());
        myDisplay.getGraph().fitWidthProperty().bind(window.widthProperty().subtract(630));
        myEditor.getBackdrop().widthProperty().bind(window.widthProperty().subtract(630));
        myEditor.getBackdrop().heightProperty().bind(window.heightProperty().subtract(610));
        myButtonMenu.getBackdrop().widthProperty().bind(window.widthProperty().subtract(20));
        myHistory.getBackdrop().heightProperty().bind(window.heightProperty().subtract(670));
    }

    private ObservableProperties setupBindings() {
    	ObservableProperties answer = new ObservableProperties(turtle);
    	answer.getNewLineProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//If new value is true we need to draw a new line
				if (newValue) {
					addNewLine();
				}
			}
    	});
    	answer.getPathVisibleProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				changeLineVisibility(newValue);
			}
    	});
    	answer.getClearScreenProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//If new value is true we need to draw a new line
				if (newValue) {
					clearScreen();
				}
			}
    	});
    	return answer;
    }
    
    //Method to clear screen. Needs to remove all lines from the view.
    //Also need to set ClearScreenProperty to false after we are done
    private void clearScreen() {
    	return;
    }
    
    //Method needs to to update whatever frontend view is trying to create new lines. Sets 
    //visibility of all line properties to the whatever this boolean passed is
    private void changeLineVisibility(boolean penup) {
    	return;
    }
    
    //Method to add new Line to View. Also need to set NewLineProperty to false after we are done
    private void addNewLine() {
		// TODO Auto-generated method stub
		return;
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
