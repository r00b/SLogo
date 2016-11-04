package Base;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.w3c.dom.css.Rect;

import java.awt.*;

/**
 * Created by Delia on 11/3/2016.
 */
public class NodeFactory {

    private String overBigButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-font: 35 arial;" +
            "-fx-text-fill: white;";
    private String bigButtonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-font: 35 arial;" +
            "-fx-text-fill: white;";

    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";

    private String textFieldFill = "-fx-background-color: linear-gradient(#00110e, #4d66cc);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";

    public NodeFactory(){

    }

    public Button makeButton(String text, ImageView img, double x, double y){
        img.setFitWidth(25);
        img.setFitHeight(25);
        Button newButton = new Button(text, img);
        newButton.setStyle(overButton);
        newButton.setOnMouseExited(e -> newButton.setStyle(overButton));
        newButton.setTranslateX(x);
        newButton.setTranslateY(y);
        return newButton;
    }

    public Button makeButton(String text, int x, int y){
        return null;
    }

    public ImageView makeHelpButton(double x, double y){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/help.png"));
        ImageView helpButton = new ImageView(newImage);
        helpButton.setTranslateX(x);
        helpButton.setTranslateY(y);
        helpButton.setFitWidth(30);
        helpButton.setFitHeight(30);
        return helpButton;
    }

    public Text makeTitle(String text, int x, int y){
        Text label = new Text(text);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setTranslateX(x);
        label.setTranslateY(y);
        return label;
    }

    public Text makePopupText(String text, int x, int y, int fontsize){
        Text title = new Text(text);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, fontsize));
        title.setFill(Color.WHITE);
        title.setTranslateX(x);
        title.setTranslateY(y);
        return title;
    }

    public TextField makeTextField(String promptText, double prefWidth, int x, int y){
        TextField newTextField = new TextField();
        newTextField.setPromptText(promptText);
        newTextField.setMaxWidth(prefWidth);
        newTextField.setTranslateX(x);
        newTextField.setTranslateY(y);
        newTextField.setStyle(textFieldFill);
        return newTextField;
    }

    public Rectangle makeBackdrop(Paint border, int width, int height, int x, int y){
        Rectangle backdrop = new Rectangle(width, height, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateX(x);
        backdrop.setTranslateY(y);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        return backdrop;
    }

    public Rectangle makeBlueBackdrop(int width, int height, int x, int y){
        Rectangle backdrop = new Rectangle(width, height, Color.MIDNIGHTBLUE);
        backdrop.setTranslateY(x);
        backdrop.setTranslateX(y);
        backdrop.opacityProperty().setValue(0.5);
        return backdrop;
    }

    public ColorAdjust makeEffect(Color color){
        double hue = map((color.getHue() + 180) % 360, 0, 360, -1, 1);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue);
        colorAdjust.setSaturation(color.getSaturation());
        colorAdjust.setBrightness(color.getBrightness());
        return colorAdjust;
    }

    private double map(double value, double start, double stop, double targetStart, double targetStop) {
        return targetStart + (targetStop - targetStart) * ((value - start) / (stop - start));
    }

    public String getButtonFill(){
        return buttonFill;
    }

    public String getOverButton(){
        return overButton;
    }

    public String getOverBigButton(){
        return overBigButton;
    }

    public String getBigButtonFill(){
        return bigButtonFill;
    }
}
