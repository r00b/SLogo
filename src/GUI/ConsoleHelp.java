package GUI;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConsoleHelp extends HelpMenu {
    private String instructions = "This field will pop up any error messages and let you \n" +
            "know what you typed incorrectly in your command.";
    public ConsoleHelp(Stage s) {
        super(s);
    }

    public void addNodes(){
        getWindow().getChildren().add(addRectangle());
        getWindow().getChildren().add(addTip(instructions, 110, 110));
    }

    public Text addTip(String text, int x, int y){
        Text instructionText = new Text(text);
        instructionText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        instructionText.setFill(Color.WHITE);
        instructionText.setTranslateX(x);
        instructionText.setTranslateY(y);
        return instructionText;
    }
}
