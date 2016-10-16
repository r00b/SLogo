package GUIController;

import FrontEndExternalAPI.Editor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIEditor implements Editor {
    private Pane window;
    private Paint border;

    public GUIEditor(Pane p, Paint borderColor){
        this.window = p;
        this.border = borderColor;
        drawEditor();
    }

    private void drawEditor(){
        Rectangle backdrop = new Rectangle(970, 280, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(600);
        backdrop.setTranslateX(620);
        backdrop.opacityProperty().setValue(0.5);
        window.getChildren().add(backdrop);
    }


    @Override
    public void enterPressed() {

    }
}
