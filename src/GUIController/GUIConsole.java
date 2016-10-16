package GUIController;

import FrontEndExternalAPI.Console;
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
public class GUIConsole implements Console {
    private Pane window;
    private Paint border;

    public GUIConsole(Pane p, Paint borderColor){
        this.window = p;
        this.border = borderColor;
        drawConsole();
        addTextLabel();
    }

    @Override
    public void printResult() {

    }

    private void drawConsole(){
        Rectangle backdrop = new Rectangle(600, 300, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(350);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
        window.getChildren().add(backdrop);
    }

    private void addTextLabel(){
        Text label = new Text("Console");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setTranslateX(20);
        label.setTranslateY(370);
        window.getChildren().add(label);
    }
}
