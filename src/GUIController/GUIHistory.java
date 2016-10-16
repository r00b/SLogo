package GUIController;

import FrontEndExternalAPI.History;
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
public class GUIHistory implements History {
    private Pane window;
    private Paint border;

    public GUIHistory(Pane p, Paint bordercoloir){
        this.window = p;
        this.border = bordercoloir;
        drawHistory();
        addTextLabel();
    }

    private void drawHistory(){
        Rectangle backdrop = new Rectangle(600, 220, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(660);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }
    private void addTextLabel(){
        Text label = new Text("History");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setTranslateX(20);
        label.setTranslateY(680);
        window.getChildren().add(label);
    }


    @Override
    public void addCommand() {

    }

    @Override
    public void callCommand() {

    }
}
