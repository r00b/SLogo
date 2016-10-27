package GUI;
import Base.OptionsMenu;
import FrontEndInternalAPI.Options;
import GUIController.GUIManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.swing.text.html.Option;
/**
 * Created by Delia on 10/17/2016.
 */
public class OptionsPopup extends OptionsMenu {
//    private Stage stage;
    public GUIManager myGUI;

    /**
     *
     * @param s
     * @param penColor
     * @param background
     * @param turtle
     * @param language
     */
    public OptionsPopup(Stage s, Color penColor, String background, String turtle, String language){
        super(s);
        super.setDefaults(penColor, background, turtle, language);
//        setDefaults(penColor, background, turtle, language);
    }

    /**
     *
     */
    public void initPopup(){
        getStage().setTitle("Options");
        getStage().setScene(new Scene(setUpWindow()));
        getStage().show();
    }

    @Override
    /**
     *
     */
    public void addTitle() {
    }
    @Override
    public void addRectangle() {
    }
    @Override
    public void addLaunchButton() {
        Button newButton = new Button("Apply");
        newButton.setStyle(getOverButton());
        newButton.setOnMouseEntered(e -> newButton.setStyle(getButtonFill()));
        newButton.setOnMouseExited(e -> newButton.setStyle(getOverButton()));
        newButton.setTranslateX(300);
        newButton.setTranslateY(500);
        newButton.setOnMouseClicked(e -> setParameters());
        getStartWindow().getChildren().add(newButton);
    }
    @Override
    public void initIDE(String background, String turtle) {
        myGUI = new GUIManager(getPenColor().getValue(), background, turtle, getLanguageBox().getValue());
        myGUI.init();
    }
}