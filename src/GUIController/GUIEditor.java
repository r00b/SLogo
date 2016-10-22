package GUIController;

import FrontEndExternalAPI.Editor;
import javafx.scene.control.TextArea;
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
    private Rectangle backdrop;
    private String defaultCommand = "Enter command here";
    private TextArea textArea;

    public GUIEditor(Pane p, Paint borderColor) {
        this.window = p;
        this.border = borderColor;
        drawEditor();
        addTextLabel();
        addTextArea();
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
        textArea.setText("> " + defaultCommand);
        textArea.setOnMouseClicked(e -> {
            if (textArea.getText().equals("> " + defaultCommand))
                textArea.setText("> ");
        });
        textArea.opacityProperty().setValue(0.5);
        textArea.setOnMouseEntered(e -> {
            backdrop.opacityProperty().setValue(0.8);
            textArea.opacityProperty().setValue(0.8);
        });
        textArea.setOnMouseExited(e -> textArea.opacityProperty().setValue(0.5));
        window.getChildren().add(textArea);
    }

    public Rectangle getBackdrop(){
        return backdrop;
    }

    @Override
    public String enterPressed() {
        return textArea.getText(); //WE ONLY WANT THE LATEST COMMAND, NOT THE WHOLE THING.
        //MUST IMPROVE THIS LATER
    }

    public void startNewCommand(){
        textArea.setText(textArea.getText() + "\n + > ");
    }
}
