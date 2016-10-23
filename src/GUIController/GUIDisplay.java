package GUIController;

import FrontEndInternalAPI.RenderSprite;
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
    private ImageView myTurtle, displayGraph;

    public GUIDisplay(Pane p, ImageView turtle){
        this.window = p;
        this.myTurtle = turtle;
        drawDisplay();
        addTextLabel();
        addTurtle();
    }

    private void drawDisplay(){
        Image newImg = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/graphPaper.gif"));
        displayGraph = new ImageView(newImg);
        displayGraph.setFitWidth(970);
        displayGraph.setFitHeight(480);
        displayGraph.setTranslateY(110);
        displayGraph.setTranslateX(620);
        displayGraph.opacityProperty().setValue(0.8);
        window.getChildren().add(displayGraph);
    }

    private void addTurtle(){
    	//NEED TO SET TRANSLATE SO THE TURTLE STARTS IN THE MIDDLE OF THE DISPLAY
    	//Also consider using JavaFX object like a pane and putting the turtle in there so display doesn't get resized
        myTurtle.setTranslateX(1000);
        myTurtle.setTranslateY(275);
        //myTurtle.setX(100);
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
