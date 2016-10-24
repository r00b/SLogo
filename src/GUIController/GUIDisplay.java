package GUIController;

import FrontEndInternalAPI.RenderSprite;
import GUI.ConsoleHelp;
import GUI.DisplayHelp;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIDisplay implements RenderSprite {
    private static final int TURTLE_FIT_SIZE = 40;
    private static final int X_POS = 620;
    private static final int Y_POS = 110;
    private int numSteps = 0;
    private Pane window;
    private ImageView helpButton;
    private ImageView myTurtle, displayGraph;
    private DisplayHelp helpWindow;
    private Color pathColor;
    private ArrayList<Line> turtleMotion = new ArrayList<>();

    public GUIDisplay(Pane p, ImageView turtle, Color pathColor){
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

    public void bindNodes(ReadOnlyDoubleProperty width){
        helpButton.translateXProperty().bind(width.subtract(50));
    }

    public void moveTurtle(int x, int y){
        numSteps++;
        drawNewLine(new Point((int)myTurtle.getTranslateX(),
                (int)myTurtle.getTranslateY()), new Point(x, y));
        myTurtle.setTranslateX(X_POS + x);
        myTurtle.setTranslateY(Y_POS + y);
    }

    private void drawNewLine(Point origin, Point destination){
        Line newLine = new Line(X_POS + origin.getX(), Y_POS + origin.getY(),
                X_POS + destination.getX(), Y_POS + destination.getY());
        newLine.setFill(pathColor);
        newLine.setStrokeWidth(5);
        newLine.setId("Step" + numSteps);
        turtleMotion.add(newLine);
        window.getChildren().add(newLine);
    }

    public ImageView getGraph(){
        return displayGraph;
    }

    @Override
    public void updateNodes() {

    }

    @Override
    public void updateDisplayOptions() {

    }

    @Override
    public void resetIDE() {

    }
}
