package GUI;

import FrontEndInternalAPI.ButtonMenu;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private Rectangle backdrop;
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
        backdrop = new Rectangle(1580, 90, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(10);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
//        backdrop.setOnMouseMoved(e -> handle(e));
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }

//    private void handle(MouseEvent e){
//        if(e.getX() > backdrop.getTranslateX()
//                && e.getX() < backdrop.getWidth()
//                && e.getY() > backdrop.getTranslateY()
//                && e.getY() < backdrop.getHeight()){
//            backdrop.opacityProperty().setValue(0.8);
//        }
////        if(e.getX() > 10
////                && e.getX() < 1580
////                && e.getY() > 10
////                && e.getY() < 90){
////            backdrop.opacityProperty().setValue(0.8);
////        }
//        else backdrop.opacityProperty().setValue(0.5);
//    }

    private void addTextLabel(){
        Text label = new Text("Options");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        label.setTranslateX(20);
        label.setTranslateY(30);
        window.getChildren().add(label);
    }

    public void addButtons(){
        Image playButton = new Image(getClass().getClassLoader()
                                     .getResourceAsStream("images/play.png"));
        ImageView playImg = new ImageView(playButton);
        playImg.setFitWidth(40);
        playImg.setFitHeight(40);
        Button play = new Button("Play", playImg);
        play.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        play.setTranslateX(30);
        play.setTranslateY(40);
        Button options = newButton("Options", 150, 45);
        window.getChildren().add(play);
        window.getChildren().add(options);
        
    }

    @Override
    public Button newButton(String text, int x, int y) {
        Button newButton = new Button("Options");
        newButton.setStyle(overButton);
        newButton.setOnMouseEntered(e -> {
            newButton.setStyle(buttonFill);
            backdrop.opacityProperty().setValue(0.8);
        });
        newButton.setOnMouseExited(e -> newButton.setStyle(overButton));
        newButton.setTranslateX(x);
        newButton.setTranslateY(y);

        return newButton;

    }

    public Rectangle getBackdrop(){
        return backdrop;
    }
}
