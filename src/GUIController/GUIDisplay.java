package GUIController;

import FrontEndInternalAPI.RenderSprite;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIDisplay implements RenderSprite {
    private static final int TURTLE_FIT_SIZE = 100;
    private Pane window;
    private ImageView helpButton;
    private ImageView myTurtle, displayGraph;

    public GUIDisplay(Pane p, ImageView turtle){
        this.window = p;
        this.myTurtle = turtle;
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
        displayGraph.setTranslateY(110);
        displayGraph.setTranslateX(620);
        displayGraph.opacityProperty().setValue(0.8);
        window.getChildren().add(displayGraph);
    }

    private void addTurtle(){
        myTurtle.setTranslateX(700);
        myTurtle.setTranslateY(200);
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
        helpButton.setTranslateX(displayGraph.getTranslateX() + displayGraph.getFitWidth() - 35);
        helpButton.setTranslateY(displayGraph.getTranslateY() + 10);
        helpButton.setFitWidth(30);
        helpButton.setFitHeight(30);
        window.getChildren().add(helpButton);
    }

    public void bindNodes(ReadOnlyDoubleProperty width){
        helpButton.translateXProperty().bind(width.subtract(50));
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
