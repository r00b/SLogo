package GUIController;

import FrontEndExternalAPI.History;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIHistory implements History {
    private Pane window;
    private Paint border;

    public GUIHistory(Pane p, Paint bordercoloir){
        this.window = p;
        this.border = bordercoloir;
        drawHistory();
    }

    private void drawHistory(){
        Rectangle backdrop = new Rectangle(600, 220, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(660);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
        window.getChildren().add(backdrop);
    }

    @Override
    public void addCommand() {

    }

    @Override
    public void callCommand() {

    }
}
