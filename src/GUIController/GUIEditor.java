package GUIController;

import FrontEndExternalAPI.Editor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
        addTextLabel();
    }

    private void drawEditor(){
        Rectangle backdrop = new Rectangle(970, 280, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(600);
        backdrop.setTranslateX(620);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }

    private void addTextLabel(){
        Text label = new Text("Editor");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setTranslateX(630);
        label.setTranslateY(620);
        window.getChildren().add(label);
    }

    @Override
    public void enterPressed() {

    }
}
