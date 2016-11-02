package GUIController;

import BackEndInternalAPI.ObservableProperties;
import Base.OptionsMenu;
import FrontEndInternalAPI.DisplayMappings;
import FrontEndInternalAPI.RenderSprite;
import GUI.DisplayHelp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
//
//import java.awt.*;
//import java.awt.Button;
import javax.swing.*;

import BackEndExternalAPI.RGB;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIDisplay implements RenderSprite {
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int BOUNCER_SPEED = 300;
    private static final int TURTLE_FIT_SIZE = 40;
    private static final int X_POS = 620;
    private static final int Y_POS = 110;
    private static boolean visibility = true;
    private int numSteps = 0;
    private int strokeWidth = 5;
    private Timeline animation = new Timeline();
    private Pane window;
    private Line myPath;
    private ImageView helpButton;
    private Button optionsButton;
    private ImageView myTurtle, displayGraph;
    private DisplayHelp helpWindow;
    private Paint pathColor;
    Stage s = new Stage();
    private DisplayMenu myOptions = new DisplayMenu(s);
    private ArrayList<Line> turtleMotion = new ArrayList<>();
    //    private ArrayList<Turtle> myTurtles = new ArrayList<>();
    private HashMap<Double, Turtle> myTurtles = new HashMap<>();
    private String currentTurtle;
    private DisplayMappings displayMappings = new DisplayMappings();

    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";

    /**
     * @param p
     * @param turtle
     * @param pathColor
     */
    public GUIDisplay(Pane p, ImageView turtle, Paint pathColor, Line lineType) {
        this.window = p;
        this.myTurtle = turtle;
        this.pathColor = pathColor;
        this.myPath = lineType;
        drawDisplay();
        addDisplayControlButtons();
        addTextLabel();
//        addMoreTurtlesButton();
//        addTurtle();
        addHelpButton();

    }

    public void setInitialTurtle(String initialTurtle) {
        currentTurtle = initialTurtle;
    }

    private void drawDisplay() {
        Image newImg = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/graphPaper.gif"));
        displayGraph = new ImageView(newImg);
        displayGraph.setFitWidth(770);
        displayGraph.setFitHeight(480);
        displayGraph.setTranslateY(Y_POS);
        displayGraph.setTranslateX(X_POS);
//        displayGraph.setstr
        displayGraph.opacityProperty().setValue(0.9);
        Color color = Color.SLATEBLUE;
        double hue = myOptions.map((color.getHue() + 180) % 360, 0, 360, -1, 1);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue);
        colorAdjust.setSaturation(myOptions.getDisplayColor().getSaturation());
        colorAdjust.setBrightness(myOptions.getDisplayColor().getBrightness());
        displayGraph.setEffect(colorAdjust);
        window.getChildren().add(displayGraph);
    }

    //Really Need to Refactor this
    public ObservableProperties addTurtle(double newID){

        ImageView myNewTurtle = new ImageView();
        myNewTurtle.setImage(myTurtle.getImage());
        myNewTurtle.setTranslateX(displayGraph.getTranslateX() + (displayGraph.getFitWidth() / 2));
        myNewTurtle.setTranslateY(displayGraph.getTranslateY() + (displayGraph.getFitHeight() / 2));
//        myNewTurtle.setX(displayGraph.getTranslateX() + (displayGraph.getFitWidth() / 2));
//        myNewTurtle.setY(displayGraph.getTranslateY() + (displayGraph.getFitHeight() / 2));
        myNewTurtle.setFitHeight(TURTLE_FIT_SIZE);
        myNewTurtle.setFitWidth(TURTLE_FIT_SIZE);
//<<<<<<< HEAD
        ObservableProperties turtleProperty = new ObservableProperties(myNewTurtle, this, newID);
        Turtle newTurtle = new Turtle(turtleProperty, myNewTurtle);
//        newTurtle.getImage().setImage(myNewTurtle.getImage());/
//=======
//        Turtle newTurtle = new Turtle(myNewTurtle);
//        //newTurtle.setImage(myNewTurtle.getImage());
//>>>>>>> 6d7baa0e2be735f93a550ac1686f96641304d2da
        newTurtle.setID(newID);
        myTurtles.put(newID, newTurtle);
        makeTooltip(newID);
        window.getChildren().add(newTurtle.getImage());
        myTurtles.get(newID).getImage().setOnMouseClicked(e -> togglePen(newID));
        return turtleProperty;
    }


    //PEN TOGGLES HERE
    //TODO Need to call observable properties to update penup and pendown
    private void togglePen(double newID) {
//        visibility = !visibility;
        myTurtles.get(newID).setVisibility(!myTurtles.get(newID).isVisible());
        myTurtles.get(newID).getProperties()
                .setPathVisibleProperty(myTurtles.get(newID).isVisible());

    }

    private void makeTooltip(double newID) {
        double yLoc = 0 - getTurtleLocation(newID).getY();
        Tooltip t = new Tooltip("X: " + myTurtles.get(newID).getProperties().getXProperty() + "\n" + "Y: " + myTurtles.get(newID).getProperties().getYProperty() + "\n"
                + "Rotation: " + myTurtles.get(newID).getImage().getRotate() + "\n" +
                "Turtle ID: " + newID);
        Tooltip.install(myTurtles.get(newID).getImage(), t);
    }

    private void addTextLabel() {
        Text label = new Text("Display");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setTranslateX(630);
        label.setTranslateY(130);
        window.getChildren().add(label);
    }

    private void addOptionsButton() {
        Stage s = new Stage();
        optionsButton = new Button("Display Options");
        optionsButton.setStyle(overButton);
        optionsButton.setOnMouseEntered(e -> optionsButton.setStyle(buttonFill));
        optionsButton.setOnMouseExited(e -> optionsButton.setStyle(overButton));
        optionsButton.setOnMouseClicked(e -> updateDisplayOptions());
        optionsButton.setTranslateX(760);
        optionsButton.setTranslateY(50);
        window.getChildren().add(optionsButton);
    }

    private void addHelpButton() {
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

    public String getTurtleStr() {
        if (myOptions != null)
            return myOptions.getTurtleString();
        else
            return null;
    }

    public DisplayMenu getMyOptions() {
        return myOptions;
    }

    private void helpHandler() {
        Stage s = new Stage();
        helpWindow = new DisplayHelp(s);
        helpWindow.init();
    }

    /**
     * @param width
     */
    public void bindNodes(ReadOnlyDoubleProperty width) {
        helpButton.translateXProperty().bind(width.subtract(50));
    }

    public void moveTurtle(double x, double y, double id) {

        numSteps++;
        drawNewLine(x, y, id);
//        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
//                ee -> {
//                    double newX = turtleX + x;
//                    double newY = turtleY - y;
//                    System.out.println("turtleY = " + turtleY);
//                    System.out.println("Y = " + y);
//
//                    System.out.println("after keyframe: \nnewX = " + newX);
//                    System.out.println("newY = " + newY);
//                    step(SECOND_DELAY, newX, newY, id);
//                });

            //whenever the animation plays, it's what we want to happen in each moment in time.
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(frame);
//        animation.play();
//        if (newX < displayGraph.getTranslateX()){
//            myTurtles.get(id).getImage().setX(displayGraph.getX());
//        }
//        else if (newX > displayGraph.getTranslateX() + displayGraph.getFitWidth()){
//            myTurtles.get(id).getImage().setX(displayGraph.getTranslateX() + displayGraph.getFitWidth());
//        }
//        else if (newY < displayGraph.getTranslateY()){
//            myTurtles.get(id).getImage().setY(displayGraph.getTranslateY());
//        }
//        else if (newY > displayGraph.getTranslateY() + displayGraph.getFitHeight()){
//            myTurtles.get(id).getImage().setY(displayGraph.getTranslateY() + displayGraph.getFitHeight());
//        }
//        else {
//            myTurtles.get(id).getImage().setX(newX);
//            myTurtles.get(id).getImage().setY(-newY);
//        }
        double transOrig = myTurtles.get(id).getImage().getTranslateY();
        double getOrig = myTurtles.get(id).getImage().getY();
        System.out.println("turtle orig translate y: " + transOrig);
        System.out.println("turtle orig get y: " + getOrig);


        myTurtles.get(id).getImage().setX(x);
        myTurtles.get(id).getImage().setY(-y);

//        System.out.println("turtle new translate y: " + myTurtles.get(id).getImage().getTranslateY());
//        System.out.println("turtle new get y: " + myTurtles.get(id).getImage().getY());
//
        if (y != 0 && myTurtles.get(id).getImage().getTranslateY() + myTurtles.get(id).getImage().getY() < displayGraph.getTranslateY()){
            System.out.println("translate less than ");
            myTurtles.get(id).getImage().setY(0);
            myTurtles.get(id).getProperties().setYProperty(displayGraph.getY() + 20);
            myTurtles.get(id).getImage().setTranslateY(displayGraph.getTranslateY());
        }
        if (y != 0
                && myTurtles.get(id).getImage().getTranslateY()
                + myTurtles.get(id).getImage().getY() > displayGraph.getTranslateY() + displayGraph.getFitHeight()){
            System.out.println("translate greater than ");
            myTurtles.get(id).getImage().setY(0);
            myTurtles.get(id).getProperties().setYProperty(displayGraph.getY() + displayGraph.getFitHeight() - 20);
            myTurtles.get(id).getImage().setTranslateY(displayGraph.getTranslateY() + displayGraph.getFitHeight() - 30);
        }
        if (x != 0 && myTurtles.get(id).getImage().getTranslateX() + myTurtles.get(id).getImage().getX() < displayGraph.getTranslateX()){
            System.out.println("translate less than ");
            myTurtles.get(id).getImage().setX(0);
            myTurtles.get(id).getProperties().setXProperty(displayGraph.getX() + 20);
            myTurtles.get(id).getImage().setTranslateX(displayGraph.getTranslateX());
        }
        if (x != 0 && myTurtles.get(id).getImage().getTranslateX() + myTurtles.get(id).getImage().getX() > displayGraph.getTranslateX() + displayGraph.getFitWidth()){
            System.out.println("translate greater than ");
            myTurtles.get(id).getImage().setX(0);
            myTurtles.get(id).getProperties().setXProperty(displayGraph.getX() + displayGraph.getFitWidth() - 20);
            myTurtles.get(id).getImage().setTranslateX(displayGraph.getTranslateX() + displayGraph.getFitWidth() - 30);
        }
    }

    private void drawNewLine(double x, double y, double id){
        double xDest = myTurtles.get(id).getImage().getTranslateX() + x + 20;
        double yDest = myTurtles.get(id).getImage().getTranslateY() - y + 20;
//        Line newLine = new Line(origin.getX() + 20, origin.getY() + 20,
//                X_POS + destination.getX() + 20, Y_POS + destination.getY() + 20);
        double xFrom =  myTurtles.get(id).getImage().getTranslateX() +  myTurtles.get(id).getImage().getX() + 20;
        double yFrom =  myTurtles.get(id).getImage().getTranslateY() +  myTurtles.get(id).getImage().getY() + 20;
        System.out.println("old points " + xFrom + " "+ yFrom);
        System.out.println("new points " + (xFrom + x) + " " + (yFrom - y));

        if (yDest < displayGraph.getTranslateY()){
            yDest = displayGraph.getTranslateY() + 20;
        }
        if (yDest > displayGraph.getTranslateY() + displayGraph.getFitHeight()){
            yDest = displayGraph.getTranslateY() + displayGraph.getFitHeight() - 20;
        }
        if (xDest < displayGraph.getTranslateX()){
            xDest = displayGraph.getTranslateX() + 20;
        }
        if (xDest > displayGraph.getTranslateX() + displayGraph.getFitWidth()){
            xDest = displayGraph.getTranslateX() + displayGraph.getFitWidth() - 20;
        }
        Line newLine = new Line(xFrom, yFrom, xDest, yDest);

        makeTooltip(id);



//    private void drawNewLine(double x, double y, double id) {
//        Line newLine = new Line(origin.getX() + 20, origin.getY() + 20,
//                X_POS + destination.getX() + 20, Y_POS + destination.getY() + 20);

        newLine.setFill(pathColor);
        newLine.setStroke(pathColor);
        newLine.setStrokeWidth(strokeWidth);
        newLine.setId("Step" + numSteps);
        newLine.getStrokeDashArray().addAll(myPath.getStrokeDashArray());
        newLine.setVisible(myTurtles.get(id).isVisible());
        //turtleMotion.add(newLine);
        myTurtles.get(1.0).getLines().add(newLine);
        window.getChildren().add(window.getChildren().size() - 1, newLine);

    }

    private void step(double elapsedTime, double x, double y, double id){
        int xMult = 1;
        int yMult = 1;
        boolean up = false, down = false, left = false, right = false;
        double currentX = myTurtles.get(id).getImage().getTranslateX();
        double currentY = myTurtles.get(id).getImage().getTranslateY();
//        double x = currentX + xChange;
//        double y = currentY - yChange;
//        double currentX =  myTurtles.get(id).getImage().getTranslateX() +  myTurtles.get(id).getImage().getX() + 20;
//        double currentY =  myTurtles.get(id).getImage().getTranslateY() +  myTurtles.get(id).getImage().getY() + 20;
        System.out.println("dest coordinates: " + x + ", " + y);
        System.out.println("Turtle coordinates: " + currentX + ", " + currentY);
        if(x < currentX) {
            xMult = -1;
            left = true;
        }
        if(x > currentX) right = true;
        if (y < currentY) {
            yMult = -1;
            up = true;
            System.out.println("true set");
        }
        if (y < currentY) down = true;
        double singleStepChange = BOUNCER_SPEED * elapsedTime;
        double stepX = currentX + (xMult * singleStepChange);
        double stepY = currentY + (yMult * singleStepChange);

        if(currentX == x && currentY == y) animation.stop();
        if (currentX != x) myTurtles.get(id).getImage().setTranslateX(stepX);
        if(currentY != y) myTurtles.get(id).getImage().setTranslateY(stepY);

        if (up){
            if(left){
                if(currentY <= y && currentX <= x){
                    animation.stop();
                    System.out.println("up left");
                }
            }
            else if(right){
                if(currentY <= y && currentX >= x){
                    animation.stop();
                    System.out.println("up right");
                }
            }

            if(currentY <= y){
                animation.stop();
                System.out.println("up: turtle y = " + currentY + " dest y = " + y);
            }
        }
        if (down){
            if(left){
                if(currentY >= y && currentX <= x){
                    animation.stop();
                    System.out.println("down left");
                }
            }
            else if(right){
                if(currentY >= y && currentX >= x){
                    animation.stop();
                    System.out.println("down right");
                }
            }
        }
//        drawNewLine(stepX, stepY, id);
//        for(int i = 0; i < gems.size(); i++){
//            gems.get(i).setY(singleStepChange(gems, elapsedTime, i));
//
//            gemBelowScreen(i, gems);
//
//            //end this loop so the gems don't keep falling in the background when
//            //user levels up!!
//            if(gameover || levelup) break;
//        }
//        myLevel.stepOnce(s);
//        myLevel.updatePoints(score.wasBonus(), score.pointsJustAdded());
//        if(myLevel.gameover) gameOver();
//        if(myLevel.isLeveledUp() && isLevel2){
//            victoryScene();
//        }
//        else if(myLevel.isLeveledUp()){
//            animation.stop();
//            levelUp();
//        }
    }

    public void stepOnce(double elapsedTime) {

    }

//    protected double singleStepChange(ImageView turtle, double elapsedTime) {
//        return turtle + BOUNCER_SPEED * elapsedTime;
//    }
//    /**
//     *
//     */
//    public void drawNewLine(){
//    	double centerX =  20;
//		double centerY = 20;
//        boolean yBoundUpper = false;
//		Line newPath;
//        double yDest = myTurtle.getY() + myTurtle.getTranslateY() + centerY;
//        System.out.println("line dest is " + yDest);
////        System.out.println("getY() is " + yDest);
////        System.out.println("getTranslateY() is " + myTurtle.getTranslateY());
////        if(yDest < Y_POS){
////            yDest = Y_POS + centerY;
////            yBoundUpper = true;
////        }
//
//    	if (turtleMotion.size() < 1) {
//
//    		newPath = new Line(centerX + myTurtle.getTranslateX(),
//                    centerY + myTurtle.getTranslateY(),
//                    myTurtle.getX() + myTurtle.getTranslateX() + centerX,
//                    myTurtle.getY() + myTurtle.getTranslateY() + centerY);
////            newPath = new Line(centerX + myTurtle.getTranslateX(),
////                    centerY + myTurtle.getTranslateY(),
////                    myTurtle.getX() + myTurtle.getTranslateX() + centerX,
////                    yDest);
//    	}
//    	else{
//    		newPath = new Line(turtleMotion.get(turtleMotion.size() - 1).getEndX(),
//                    turtleMotion.get(turtleMotion.size() - 1).getEndY(),
//    				myTurtle.getX() + myTurtle.getTranslateX() + centerX,
//                    myTurtle.getY() + myTurtle.getTranslateY() + centerY);
////            newPath = new Line(turtleMotion.get(turtleMotion.size() - 1).getEndX(),
////                    turtleMotion.get(turtleMotion.size() - 1).getEndY(),
////                    myTurtle.getX() + myTurtle.getTranslateX() + centerX,
////                    yDest);
//    	}
//
////    	System.out.println(pathColor);
//    	newPath.setFill(pathColor);
//        newPath.setStroke(pathColor);
//        newPath.setStrokeWidth(strokeWidth);
////        newPath.setStrokeDashOffset(2);
//        newPath.getStrokeDashArray().addAll(myPath.getStrokeDashArray());
//        newPath.setId("Step" + numSteps);
//        newPath.setVisible(visibility);
//        turtleMotion.add(newPath);
//        window.getChildren().add(newPath);
//
//
//        if(yBoundUpper) myTurtle.setTranslateY(Y_POS);
//        //window.getChildren().remove(myTurtle);
//        //window.getChildren().add(myTurtle);
//
//    	//bool.set(false);
//    }

    //DONT NEED IT ANYMORE


    /**
     *
     */
    public void clearScreen(double id) {
        window.getChildren().removeAll(myTurtles.get(id).getLines());
        myTurtles.get(id).getLines();
        turtleMotion.clear();
        //clearScreenProperty.set(false);
    }

    private void addDisplayControlButtons() {
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/clear.png"));
        ImageView clearImg = new ImageView(newImage);
        Button clear = newButton("Clear", clearImg, (int) displayGraph.getTranslateX() + 380, 50);
        clear.setOnMouseClicked(e -> {
            window.getChildren().removeAll(turtleMotion);
            turtleMotion.clear();
        });
        newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/options.png"));
        ImageView optionsImg = new ImageView(newImage);
        optionsButton = newButton("Display Options", optionsImg, 840, 50);
        optionsButton.setOnMouseClicked(e -> {
            updateDisplayOptions();
        });
        newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/reset.png"));
        ImageView resetImg = new ImageView(newImage);
        Button reset = newButton("Reset", resetImg, 1090, 50);
        reset.setOnMouseClicked(e -> resetIDE());
//        optionsButton.setTranslateX(760);
//        optionsButton.setTranslateY(120);
        window.getChildren().addAll(clear, optionsButton, reset);
//        window.getChildren().add(clear);
    }

    private Button newButton(String text, ImageView imgV, int x, int y) {
        imgV.setFitWidth(25);
        imgV.setFitHeight(25);
        Button run = new Button(text, imgV);
        run.setStyle(overButton);
        run.setOnMouseEntered(e -> {
            run.setStyle(buttonFill);
        });
        run.setOnMouseExited(e -> run.setStyle(overButton));
        run.setTranslateX(x);
        run.setTranslateY(y);
        return run;
    }


    /**
     * @param isVisible
     */
    public void setVisibility(boolean isVisible, double newID) {
        visibility = isVisible;
        myTurtles.get(newID).setVisibility(isVisible);
    }

    /**
     * @return
     */
    public ImageView getGraph() {
        return displayGraph;
    }

    /**
     * @param newID
     * @return
     */
    public Point getTurtleLocation(double newID) {
        return new Point((int) myTurtles.get(newID).getImage().getX(), (int) myTurtles.get(newID).getImage().getY());
    }

    @Override
    public void updateNodes() {

    }

    public Paint getPenColor() {
        return pathColor;
    }

    @Override
    public void updateDisplayOptions() {
//        myOptions.setDefaults(pathColor, currentTurtle);
        myOptions.initPopup();

    }

    public void applyDisplayChanges() {
        pathColor = myOptions.getPenColor().getValue();
//        applyDisplayChanges(myOptions.getTurtleBox().getValue());
        myOptions.setTurtleString();
        myTurtle.setImage(myOptions.generateTurtleImage());
        for (Turtle turtle : myTurtles.values()) {
            turtle.setImage(myOptions.generateTurtleImage());
        }
        myPath = myOptions.getLineBox().getValue();
        createDisplayShading();
        strokeWidth = myOptions.getStrokeWidth();
//        setVisibility(!myOptions.isPenUp());
        //makeTooltip();
    }

    private void createDisplayShading() {
        double hue = myOptions.map((myOptions.getDisplayColor().getHue() + 180) % 360, 0, 360, -1, 1);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue);
        colorAdjust.setSaturation(myOptions.getDisplayColor().getSaturation());
        colorAdjust.setBrightness(myOptions.getDisplayColor().getBrightness());
        displayGraph.setEffect(colorAdjust);
    }

    @Override
    public void resetIDE() {
        window.getChildren().remove(myTurtle);
        window.getChildren().removeAll(turtleMotion);
        turtleMotion.clear();
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/graphPaper.gif"));
//        drawDisplay();
        displayGraph.setEffect(null);
        displayGraph.setImage(newImage);
        newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/turtle.png"));
//        ImageView defaultTurtle = new ImageView(newImage);
//        myTurtle = defaultTurtle;
        myTurtle.setImage(newImage);
        myTurtle.setTranslateX(displayGraph.getTranslateX() + (displayGraph.getFitWidth() / 2));
        myTurtle.setTranslateY(displayGraph.getTranslateY() + (displayGraph.getFitHeight() / 2));
//        addTurtle();
        Paint defaultColor = Color.MIDNIGHTBLUE;
        pathColor = defaultColor;
        myPath.getStrokeDashArray().clear();
//        newImage = new Image(getClass().getClassLoader()
//                .getResourceAsStream("images/graphPaper.png"));
//        ImageView defaultDisplay = new ImageView(newImage);
//        displayGraph = defaultDisplay;
        strokeWidth = 5;
//        addTextLabel();
//        addHelpButton();
//        addDisplayControlButtons();
    }

    class DisplayMenu extends OptionsMenu {

        private static final int DROP_DOWN_X_VALUE = 400;
        private static final int PEN_MIN = 0;
        private static final int PEN_MAX = 10;
        private static final int PEN_INIT = 5;
        private ColorPicker displayColor = new ColorPicker();
        private Slider slider;
        private CheckBox penUpBox;

        /**
         * @param s
         */
        public DisplayMenu(Stage s) {
            super(s);
            displayColor.setValue(Color.MIDNIGHTBLUE);
        }

        public void addNodes() {
            addTitle();
            addRectangle();
            changePenColor();
            changeSpriteImage();
            addLaunchButton();
            addLineStylePicker();
            changeDisplayColor();
            addPenSizeSlider();
//            addCheckBox();
        }

        @Override
        public void addTitle() {
            Text title = new Text("Select your preferences for the display.");
            title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            title.setFill(Color.WHITE);
            title.setTranslateX(30);
            title.setTranslateY(130);
            getStartWindow().getChildren().add(title);
        }

        @Override
        public void addRectangle() {
            Rectangle backdrop = new Rectangle(500, 340, Color.MIDNIGHTBLUE);
            backdrop.setTranslateY(180);
            backdrop.setTranslateX(100);
            backdrop.opacityProperty().setValue(0.5);
            getStartWindow().getChildren().add(backdrop);
        }

        @Override
        public void addLaunchButton() {
            Button newButton = new javafx.scene.control.Button("Apply");
            newButton.setStyle(getOverButton());
            newButton.setOnMouseEntered(e -> newButton.setStyle(getButtonFill()));
            newButton.setOnMouseExited(e -> newButton.setStyle(getOverButton()));
            newButton.setOnMouseClicked(e -> {
                applyDisplayChanges();
                getStage().close();
            });
            newButton.setTranslateX(300);
            newButton.setTranslateY(500);
//        newButton.setOnMouseClicked(e -> setParameters());
            getStartWindow().getChildren().add(newButton);
        }

        private void addPenSizeSlider(){
            slider = new Slider(0, 10, 5);
            slider.setShowTickMarks(true);
            slider.setShowTickLabels(true);
            slider.setMajorTickUnit(2);
            slider.setMinorTickCount(1);
            slider.setSnapToTicks(true);
            slider.setTranslateX(DROP_DOWN_X_VALUE);
            slider.setTranslateY(200);

            Label sliderLabel = generateLabel("Choose pen size \t\t" + (int) slider.getValue(), 125, 200);

            slider.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov,
                                    Number old_val, Number new_val) {
                    sliderLabel.setText("Choose pen size \t\t" + String.format("%.0f", new_val));
                }
            });

            getStartWindow().getChildren().addAll(slider, sliderLabel);
        }

        @Override
        public void initIDE(String background, String turtle) {
//nope
        }

        public Image generateTurtleImage() {
            Image newImg = new Image(getClass().getClassLoader()
                    .getResourceAsStream(getTurtleString()));
            return newImg;
        }

        public void changeDisplayColor() {
            displayColor = generateColorPicker(Color.MIDNIGHTBLUE, 400, 400);
            Label penLabel = generateLabel("Select display color", 125, 400);
            getStartWindow().getChildren().add(displayColor);
            getStartWindow().getChildren().add(penLabel);
        }

//        private void addCheckBox() {
//            penUpBox = new CheckBox("penup");
//            penUpBox.setTranslateX(DROP_DOWN_X_VALUE);
//            penUpBox.setTranslateY(450);
//            Label penUpLabel = generateLabel("Check to make pen invisible", 125, 450);
//            getStartWindow().getChildren().addAll(penUpLabel, penUpBox);
//        }

        /**
         *
         */
        public void initPopup() {
            getStage().setTitle("Options");
            getStage().setScene(new Scene(setUpWindow()));
            getStage().show();
        }

        public double map(double value, double start, double stop, double targetStart, double targetStop) {
            return targetStart + (targetStop - targetStart) * ((value - start) / (stop - start));
        }


        public Color getDisplayColor() {
            return displayColor.getValue();
        }

        public int getStrokeWidth() {
            return (int) slider.getValue();
        }

        public boolean isPenUp() {
            return penUpBox.isSelected();
        }
    }

	public void changePenColor(Double newValue) {
		Color color = displayMappings.getPenColor(newValue.intValue());
		pathColor = color;
	}

	public void setPenSize(Double newValue) {
		strokeWidth = newValue.intValue();
	}

	public void changeImage(Double newValue) {
//		myTurtle.setImage(displayMappings.getTurtleImage(newValue.intValue()));
        Image newImg = displayMappings.getTurtleImage(newValue.intValue());
//        ImageView myNewImage = new ImageView(newImg);
        
        myTurtle.setImage(newImg);
		for (Turtle turtle : myTurtles.values()) {
			turtle.setImage(newImg);
		}
	}

	public void setBackgroundImage(Double newValue) {
		Color color = displayMappings.getBackgroundColor(newValue.intValue());
        double hue = myOptions.map((color.getHue() + 180) % 360, 0, 360, -1, 1);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue);
        colorAdjust.setSaturation(myOptions.getDisplayColor().getSaturation());
        colorAdjust.setBrightness(myOptions.getDisplayColor().getBrightness());
        displayGraph.setEffect(colorAdjust);
		//TODO Call whatever sets the color and change it
        //SET HUES FOR THESE
	}

	public void changePalette(RGB newValue) {
        Color color = new Color(newValue.getRed(), newValue.getGreen(), newValue.getBlue(), 1.0);
        double hue = myOptions.map((color.getHue() + 180) % 360, 0, 360, -1, 1);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue);
        colorAdjust.setSaturation(myOptions.getDisplayColor().getSaturation());
        colorAdjust.setBrightness(myOptions.getDisplayColor().getBrightness());
        displayGraph.setEffect(colorAdjust);
		// TODO Auto-generated method stub
	}
}
