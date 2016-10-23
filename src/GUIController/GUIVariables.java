package GUIController;

import FrontEndExternalAPI.Variables;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIVariables implements Variables {
    private Pane window;
    private Paint border;
    private Rectangle backdrop;

    public GUIVariables(Pane p, Paint bodercolor){
        this.window = p;
        this.border = bodercolor;
        drawVariables();
        addTextLabel();
        addHelpButton();
    }

    private void drawVariables(){
        backdrop = new Rectangle(600, 230, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(110);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }

    private void addTextLabel(){
        Text label = new Text("Declared Variables");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        label.setTranslateX(20);
        label.setTranslateY(130);
        window.getChildren().add(label);
    }

    private void addHelpButton(){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/help.png"));
        ImageView helpButton = new ImageView(newImage);
        helpButton.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        helpButton.setTranslateX(backdrop.getTranslateX() + backdrop.getWidth() - 35);
        helpButton.setTranslateY(backdrop.getTranslateY() + 10);
        helpButton.setFitWidth(30);
        helpButton.setFitHeight(30);
        window.getChildren().add(helpButton);
    }

    @Override
    public void addVariable() {

    }

    @Override
    public ArrayList<Integer> getAllVariables() {
        return null;
    }
}
