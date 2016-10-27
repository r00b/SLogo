package GUIController;

import Base.OptionsMenu;
import FrontEndInternalAPI.RenderSprite;
import GUI.ConsoleHelp;
import GUI.DisplayHelp;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.Button;
import java.util.ArrayList;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIDisplay implements RenderSprite {
    private static final int TURTLE_FIT_SIZE = 40;
    private static final int X_POS = 620;
    private static final int Y_POS = 110;
    private static boolean visibility = true;
    private int numSteps = 0;
    private Pane window;
    private ImageView helpButton;
    private Button optionsButton;
    private ImageView myTurtle, displayGraph;
    private DisplayHelp helpWindow;
    private Paint pathColor;
    private DisplayMenu myOptions;
    private ArrayList<Line> turtleMotion = new ArrayList<>();

    /**
     *
     * @param p
     * @param turtle
     * @param pathColor
     */
    public GUIDisplay(Pane p, ImageView turtle, Paint pathColor){
        this.window = p;
        this.myTurtle = turtle;
        this.pathColor = pathColor;
        drawDisplay();
        addTextLabel();
        addTurtle();
        addHelpButton();
    }

    private void drawDisplay(){
        Image newImg = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/graphPaper.gif"));
        displayGraph = new ImageView(newImg);
        displayGraph.setFitWidth(770);
        displayGraph.setFitHeight(480);
        displayGraph.setTranslateY(Y_POS);
        displayGraph.setTranslateX(X_POS);
//        displayGraph.setstr
        displayGraph.opacityProperty().setValue(0.9);
        window.getChildren().add(displayGraph);
    }

    private void addTurtle(){
        myTurtle.setTranslateX(displayGraph.getTranslateX() + (displayGraph.getFitWidth() / 2));
        myTurtle.setTranslateY(displayGraph.getTranslateY() + (displayGraph.getFitHeight() / 2));
        myTurtle.setFitHeight(TURTLE_FIT_SIZE);
        myTurtle.setFitWidth(TURTLE_FIT_SIZE);
        window.getChildren().add(myTurtle);
    }

    private void addTextLabel(){
        Text label = new Text("Display");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setTranslateX(630);
        label.setTranslateY(130);
        window.getChildren().add(label);
    }

    private void addOptionsButton(){
        optionsButton = new Button("Display Options");

    }

    private void addHelpButton(){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/help.png"));
        helpButton = new ImageView(newImage);
        helpButton.setOnMouseClicked(e -> helpHandler());
        helpButton.setTranslateX(displayGraph.getTranslateX() + displayGraph.getFitWidth() - 35);
        helpButton.setTranslateY(displayGraph.getTranslateY() + 10);
        helpButton.setFitWidth(30);
        helpButton.setFitHeight(30);
        window.getChildren().add(helpButton);
    }
    
    private void helpHandler(){
        Stage s = new Stage();
        helpWindow = new DisplayHelp(s);
        helpWindow.init();
    }

    /**
     *
     * @param width
     */
    public void bindNodes(ReadOnlyDoubleProperty width){
        helpButton.translateXProperty().bind(width.subtract(50));
    }

    /**
     *
     * @param x
     * @param y
     */
    public void moveTurtle(int x, int y){
        numSteps++;
        drawNewLine(new Point((int)myTurtle.getTranslateX(),
                (int)myTurtle.getTranslateY()), new Point(x, y));
//        System.out.println("turtle original position:" + (int) myTurtle.getTranslateX());
//        System.out.println("translate x of the editor" + X_POS);
        myTurtle.setTranslateX(X_POS + x);
        myTurtle.setTranslateY(Y_POS + y);
    }

    /**
     *
     * @param bool
     */
    public void drawNewLine(BooleanProperty bool ){
    	double centerX =  20;
		double centerY = 20;
		Line newPath;
    	if (turtleMotion.size() < 1) {
    		
    		newPath = new Line(centerX + myTurtle.getTranslateX(),
                    centerY + myTurtle.getTranslateY(),
                    myTurtle.getX() + myTurtle.getTranslateX() + centerX,
                    myTurtle.getY() + myTurtle.getTranslateY() + centerY);
    	}
    	else{
    		newPath = new Line(turtleMotion.get(turtleMotion.size() - 1).getEndX(),
                    turtleMotion.get(turtleMotion.size() - 1).getEndY(),
    				myTurtle.getX() + myTurtle.getTranslateX() + centerX,
                    myTurtle.getY() + myTurtle.getTranslateY() + centerY);
    	}
    	System.out.println(pathColor);
    	newPath.setFill(pathColor);
        newPath.setStroke(pathColor);
        newPath.setStrokeWidth(5);
        newPath.setId("Step" + numSteps);
        newPath.setVisible(visibility);
        turtleMotion.add(newPath);
        window.getChildren().add(newPath);
        window.getChildren().remove(myTurtle);
        window.getChildren().add(myTurtle);
    	
    	bool.set(false);
    }

    //DONT NEED IT ANYMORE

    /**
     *
     * @param origin
     * @param destination
     */
    public void drawNewLine(Point origin, Point destination){
//        Line newLine = new Line(origin.getX() + 20, origin.getY() + 20,
//                X_POS + destination.getX() + 20, Y_POS + destination.getY() + 20);
        System.out.println("my origin: " + origin.getX() + " " + origin.getY());
        System.out.println("my destination: " + destination.getX() + " " + destination.getY());
        Line newLine = new Line(origin.getX() + 20, origin.getY() + 20,
                destination.getX() + 20, destination.getY() + 20);
        newLine.setFill(pathColor);
        newLine.setStroke(pathColor);
        newLine.setStrokeWidth(5);
        newLine.setId("Step" + numSteps);
        newLine.setVisible(visibility);
        turtleMotion.add(newLine);
        window.getChildren().add(newLine);
    }

    /**
     *
     * @param clearScreenProperty
     */
	public void clearScreen(BooleanProperty clearScreenProperty) {
		window.getChildren().removeAll(turtleMotion);
		clearScreenProperty.set(false);
	}

    /**
     *
     * @param isVisible
     */
    public void setVisibility(boolean isVisible){
        visibility = isVisible;
    }

    /**
     *
     * @return
     */
    public ImageView getGraph(){
        return displayGraph;
    }

    /**
     *
     * @return
     */
    public Point getTurtleLocation(){
        return new Point((int)myTurtle.getTranslateX(), (int)myTurtle.getTranslateY());
    }

    @Override
    public void updateNodes() {

    }

    @Override
    public void updateDisplayOptions() {
        Stage s = new Stage();
        myOptions = new DisplayMenu(s);
        myOptions.initPopup();

    }

    private void applyDisplayChanges(){
        pathColor = myOptions.getPenColor().getValue();
    }

    @Override
    public void resetIDE() {

    }

    private class DisplayMenu extends OptionsMenu{

        /**
         * @param s
         */
        public DisplayMenu(Stage s) {
            super(s);
        }

        @Override
        public void addTitle() {

        }

        @Override
        public void addRectangle() {

        }

        @Override
        public void addLaunchButton() {
            javafx.scene.control.Button newButton = new javafx.scene.control.Button("Apply");
            newButton.setStyle(getOverButton());
            newButton.setOnMouseEntered(e -> newButton.setStyle(getButtonFill()));
            newButton.setOnMouseExited(e -> newButton.setStyle(getOverButton()));
//            newButton.setOnMouseClicked(e -> );
            newButton.setTranslateX(300);
            newButton.setTranslateY(500);
//        newButton.setOnMouseClicked(e -> setParameters());
            getStartWindow().getChildren().add(newButton);
        }

        @Override
        public void initIDE(String background, String turtle) {

        }


        /**
         *
         */
        public void initPopup(){
            getStage().setTitle("Options");
            getStage().setScene(new Scene(setUpWindow()));
            getStage().show();
        }
    }
}
