package GUI;

//import com.sun.org.apache.xerces.internal.util.URI;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.*;

/**
 * Created by Delia on 10/26/2016.
 */
public class CommandHelp extends HelpMenu {
    private Alert alert;

    private String instructions = "For help on commands, check this link for a complete list. \n";
    private String inst1 = "For commands in the extension, follow this link.";

    /**
     * @param s
     */
    public CommandHelp(Stage s) {
        super(s);
    }

    public void addNodes() {
        getWindow().getChildren().add(addRectangle());
        getWindow().getChildren().add(addTip(instructions, 110, 110));
        getWindow().getChildren().add(addTip(inst1, 110, 200));
        Button basic = addButton("Basic commands", 200, 130);
        basic.setOnMouseClicked(e -> followURL(basic.getText()));
        getWindow().getChildren().add(basic);
        Button extended = addButton("Extended commands", 200, 230);
        extended.setOnMouseClicked(e -> followURL(extended.getText()));
        getWindow().getChildren().add(extended);
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

    private void followURL(String buttonText) {
        try {
            if (buttonText.equals("Basic commands")) {
                Desktop.getDesktop().browse(new URI("http://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands.php"));
            } else {
                Desktop.getDesktop().browse(new URI("http://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands2_J2W.php"));
            }
//            errorPopup("url clicked");
        } catch (MalformedURLException e) {
            errorPopup("This URL is not formed correctly. MalformedURLException.");
        } catch (IOException e) {
            errorPopup("The IO of this URL is incorrect. IOException.");
        } catch (URISyntaxException e) {
            errorPopup("The syntax of this URL is incorrect. URISyntaxException.");
        }
    }

    /**
     * Generates error popup window displaying message for why the value is wrong.
     * Notifies user that default value will be used instead
     *
     * @param errorText String describing the error
     */
    private void errorPopup(String errorText) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("XML Error");

        String s = errorText;
        alert.setContentText(s);
        alert.showAndWait();
    }
}
