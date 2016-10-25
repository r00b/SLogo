package GUI;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/23/2016.
 */
public class VariablesHelp extends HelpMenu {
    private String instructions = "Start by entering a command in the editor that \n\tdeclares a variable. "+
            "\nAll commands with variables that have been run will show \n\tup as buttons in the History box." +
            "\nClick on a variable's name or value to edit it." +
            "\nPress the Apply button in the top to apply your changes." +
            "\nPress the Clear button in the History box to clear history.";

    /**
     *
     * @param s
     */
    public VariablesHelp(Stage s) {
        super(s);
    }

    /**
     *
     */
    public void addNodes(){
        getWindow().getChildren().add(addRectangle());
        getWindow().getChildren().add(addTip(instructions, 110, 110));
    }

    /**
     *
     * @param text
     * @param x
     * @param y
     * @return
     */
    public Text addTip(String text, int x, int y){
        Text instructionText = new Text(text);
        instructionText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        instructionText.setFill(Color.WHITE);
        instructionText.setTranslateX(x);
        instructionText.setTranslateY(y);
        return instructionText;
    }
}
