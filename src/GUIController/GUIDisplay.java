package GUIController;

import FrontEndInternalAPI.RenderSprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIDisplay implements RenderSprite {
    private static final int TURTLE_FIT_SIZE = 100;
    private Pane window;
    private ImageView myTurtle;

    public GUIDisplay(Pane p, ImageView turtle){
        this.window = p;
        this.myTurtle = turtle;
        drawDisplay();
        addTurtle();
    }

    private void drawDisplay(){
        Image newImg = new Image(getClass().getClassLoader()
                .getResourceAsStream("graphPaper.gif"));
        ImageView displayGraph = new ImageView(newImg);
        displayGraph.setFitWidth(970);
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
