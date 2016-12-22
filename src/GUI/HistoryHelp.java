package GUI;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/23/2016.
 */
public class HistoryHelp extends HelpMenu {
    private String instructions = "Start by entering a command in the editor and clicking \n\tthe Run button. " +
            "\nAll commands that have been run will show up as buttons \n\tin the History box." +
            "\nPress the Add button in the Variables box to add a \n\tnew variable." +
            "\nPress the Clear button in the Variables box to clear variables.";

    /**
     * @param s
     */
    public HistoryHelp(Stage s) {
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
