package GUI;

//import com.sun.org.apache.xerces.internal.util.URI;
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

    private String instructions = "For help on commands, check this link for a complete list. \n";
    private String inst1 = "For commands in the extension, follow this link.";
    /**
     * @param s
     */
    public CommandHelp(Stage s) {
        super(s);
    }

    public void addNodes(){
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

    private void followURL(String buttonText){
        try{
            if(buttonText.equals("Basic commands")){
                Desktop.getDesktop().browse(new URI("http://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands.php"));
            }
            else{
                Desktop.getDesktop().browse(new URI("http://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands2_J2W.php"));
            }
            System.out.println("url clicked");
        }
        catch (MalformedURLException e) {
            System.out.println("malformed thrown");
        }
        catch (IOException e) {
            System.out.println("IO thrown");
        }
        catch (URISyntaxException e) {
            System.out.println("Syntax thrown");
        }
    }
}
