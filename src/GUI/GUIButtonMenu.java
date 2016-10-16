package GUI;

import FrontEndInternalAPI.ButtonMenu;
import javafx.scene.control.Button;
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
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";

    public GUIButtonMenu(Pane p, Paint borderColor){
        this.window = p;
        this.border = borderColor;
        drawButtonMenu();
        addTextLabel();
        addButtons();
    }

    private void drawButtonMenu(){
        Rectangle backdrop = new Rectangle(1580, 90, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(10);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }

    private void addTextLabel(){
        Text label = new Text("Options");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setTranslateX(20);
        label.setTranslateY(30);
        window.getChildren().add(label);
    }

    public void addButtons(){
        Button options = newButton("Options", 30, 40);
        window.getChildren().add(options);
    }

    @Override
    public Button newButton(String text, int x, int y) {
        Button newButton = new Button("Options");
        newButton.setStyle(overButton);
        newButton.setOnMouseEntered(e -> newButton.setStyle(buttonFill));
        newButton.setOnMouseExited(e -> newButton.setStyle(overButton));
        newButton.setTranslateX(x);
        newButton.setTranslateY(y);

        return newButton;

    }
}
