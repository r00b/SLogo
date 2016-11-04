package GUIController;

import Base.NodeFactory;
import Base.OptionsMenu;
import FrontEndExternalAPI.StartMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class GUIStartMenu extends OptionsMenu {

    private static final int START_MENU_WIDTH = 700;
    private static final int START_MENU_HEIGHT = 600;
    private static final int DROP_DOWN_X_VALUE = 400;

    private NodeFactory myFactory = new NodeFactory();


    private static final LinearGradient textAndBoxGradient = new LinearGradient(0d, 1d, 1d, 0d, true,
            CycleMethod.NO_CYCLE,
            new Stop(0, Color.WHITE),
            new Stop(0.15, Color.HONEYDEW),
            new Stop(0.3, Color.LIGHTBLUE),
            new Stop(0.45, Color.LIGHTSTEELBLUE),
            new Stop(0.6, Color.LIGHTBLUE),
            new Stop(0.75, Color.HONEYDEW),
            new Stop(1, Color.WHITE));

    /**
     *
     * @param s
     */
    public GUIStartMenu(Stage s) {
        super(s);
    }

    @Override
    /**
     *
     */
    public void addTitle() {
        BigNameText title = new BigNameText("Welcome to \n\tSLogo");
        title.setTranslateX(125);
        title.setTranslateY(75);
        getStartWindow().getChildren().add(title);

    }

    /**
     *
     */
    public void addLaunchButton(){
        Button newButton = new Button("Launch SLogo");
        newButton.setStyle(myFactory.getOverBigButton());
        newButton.setOnMouseEntered(e -> newButton.setStyle(myFactory.getBigButtonFill()));
        newButton.setOnMouseExited(e -> newButton.setStyle(myFactory.getOverBigButton()));
        newButton.setTranslateX(300);
        newButton.setTranslateY(500);
        newButton.setOnMouseClicked(e -> setParameters());
        getStartWindow().getChildren().add(newButton);
    }

    /**
     *
     */
    public void addRectangle(){
        Rectangle backdrop = new Rectangle(500, 290, Color.MIDNIGHTBLUE);
        backdrop.setTranslateY(230);
        backdrop.setTranslateX(100);
        backdrop.opacityProperty().setValue(0.5);
        getStartWindow().getChildren().add(backdrop);
    }

    /**
     *
     * @param chosenBackground
     * @param chosenTurtle
     */
    public void initIDE(String chosenBackground, String chosenTurtle) {
        GUIManager newGUI = new GUIManager(getPenColor().getValue(), chosenBackground, chosenTurtle, getLanguageBox().getValue(), getLineBox().getValue());
        newGUI.init();
    }

    private static class BigNameText extends StackPane {
        /**
         * @param Name
         */
        public BigNameText(String Name) {
            Text titleText = new Text(Name);
            titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
            titleText.setFill(textAndBoxGradient);
            getChildren().add(titleText);
        }
    }

}