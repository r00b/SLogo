package GUI;

import FrontEndInternalAPI.ButtonMenu;
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
public class GUIButtonMenu implements ButtonMenu{
    private Pane window;
    private Paint border;

    public GUIButtonMenu(Pane p, Paint borderColor){
        this.window = p;
        this.border = borderColor;
        drawButtonMenu();
        addTextLabel();
    }

    private void drawButtonMenu(){
        Rectangle backdrop = new Rectangle(1580, 90, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(10);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
        window.getChildren().add(backdrop);
    }

    private void addTextLabel(){
        Text label = new Text("Options");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setTranslateX(20);
        label.setTranslateY(30);
        window.getChildren().add(label);
    }

    @Override
    public void addButton() {

    }
}
