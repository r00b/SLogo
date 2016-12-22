package GUI;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayHelp extends HelpMenu {
    private String instructions = "This will show the results of your inputs by \n " +
            "moving the turtle to follow your commands.";

    /**
     * @param s
     */
    public DisplayHelp(Stage s) {
        super(s);
    }

    /**
     *
     */
    public void addNodes() {
        getWindow().getChildren().add(addRectangle());
        getWindow().getChildren().add(addTip(instructions, 110, 110));
    }

    /**
     * @param text
     * @param x
     * @param y
     * @return
     */
    public Text addTip(String text, int x, int y) {
        Text instructionText = new Text(text);
        instructionText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        instructionText.setFill(Color.WHITE);
        instructionText.setTranslateX(x);
        instructionText.setTranslateY(y);
        return instructionText;
    }
}

