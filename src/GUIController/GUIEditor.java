package GUIController;

import FrontEndExternalAPI.Editor;
import GUI.EditorHelp;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIEditor implements Editor {
    private Pane window;
    private Paint border;
    private Rectangle backdrop;
    private String defaultCommand = "Enter command here";
    private TextArea textArea;
    private EditorHelp helpWindow;
    private ImageView helpButton;
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";

    public GUIEditor(Pane p, Paint borderColor) {
        this.window = p;
        this.border = borderColor;
        drawEditor();
        addTextLabel();
        addTextArea();
        addHelpButton();
//        addRunButton();
    }

    private void drawEditor(){
        backdrop = new Rectangle(970, 280, Color.WHITE);
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
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        label.setTranslateX(630);
        label.setTranslateY(620);
        window.getChildren().add(label);
    }

    private void addTextArea(){
        textArea = new TextArea();
        textArea.setTranslateX(630);
        textArea.setTranslateY(640);
        textArea.setPrefSize(940, 240);
        textArea.setWrapText(true);
        textArea.setText("> " + defaultCommand);
        textArea.positionCaret(textArea.getText().length());
        textArea.setOnMouseClicked(e -> {
            if (textArea.getText().equals("> " + defaultCommand))
                textArea.setText("> ");
            textArea.positionCaret(textArea.getText().length());
        });
        textArea.opacityProperty().setValue(0.5);
        textArea.setOnMouseEntered(e -> {
            backdrop.opacityProperty().setValue(0.8);
            textArea.opacityProperty().setValue(0.8);
        });
        textArea.setOnMouseExited(e -> textArea.opacityProperty().setValue(0.5));
        window.getChildren().add(textArea);
    }

    private void addHelpButton(){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/help.png"));
        helpButton = new ImageView(newImage);
        helpButton.setOnMouseClicked(e -> helpHandler());
        helpButton.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        helpButton.setTranslateX(backdrop.getTranslateX() + backdrop.getWidth() - 35);
        helpButton.setTranslateY(backdrop.getTranslateY() + 10);
        helpButton.setFitWidth(30);
        helpButton.setFitHeight(30);
        window.getChildren().add(helpButton);
    }

    private void helpHandler(){
        Stage s = new Stage();
        helpWindow = new EditorHelp(s);
        helpWindow.init();
    }

    private void addRunButton(){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/play.png"));
        ImageView imgV = new ImageView(newImage);
        imgV.setFitWidth(25);
        imgV.setFitHeight(25);
        Button run = new Button("Run", imgV);
        run.setStyle(overButton);
        run.setOnMouseEntered(e -> {
            run.setStyle(buttonFill);
            backdrop.opacityProperty().setValue(0.8);
        });
        run.setOnMouseExited(e -> run.setStyle(overButton));
        run.setTranslateX(700);
        run.setTranslateY(600);
        window.getChildren().add(run);
    }

    public Rectangle getBackdrop(){
        return backdrop;
    }


    public void startNewCommand(){

        textArea.setText(textArea.getText() + "\n> ");
    }

    public void bindNodes(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height){
        textArea.prefWidthProperty().bind(width.subtract(650));
        textArea.prefHeightProperty().bind(height.subtract(660));
        helpButton.translateXProperty().bind(width.subtract(50));
    }

    @Override
    public String getCurrentText() {
        return textArea.getText();
    }
}
