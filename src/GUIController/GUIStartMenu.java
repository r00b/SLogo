package GUIController;

import FrontEndExternalAPI.StartMenu;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUIStartMenu implements StartMenu {

    private static final int START_MENU_WIDTH = 700;
    private static final int START_MENU_HEIGHT = 600;
    private Stage stage, stageNew;
    private static final LinearGradient textAndBoxGradient = new LinearGradient(0d, 1d, 1d, 0d, true,
            CycleMethod.NO_CYCLE,
            new Stop[]{
                    new Stop(0, Color.WHITE),
                    new Stop(0.15, Color.TURQUOISE),
                    new Stop(0.3, Color.LIGHTBLUE),
                    new Stop(0.45, Color.SLATEBLUE),
                    new Stop(0.6, Color.LIGHTBLUE),
                    new Stop(0.75, Color.TURQUOISE),
                    new Stop(0.9, Color.WHITE),
                    new Stop(1, Color.WHITE)
            });

    public GUIStartMenu(Stage s) {
        stage = s;
    }

    public Parent setUpWindow(){
        Pane startWindow = new Pane();
        startWindow.setPrefSize(START_MENU_WIDTH, START_MENU_HEIGHT);
        Image background = new Image(getClass().getClassLoader()
                .getResourceAsStream("background.jpg"));
        ImageView backgroundImageMainScreen = new ImageView(background);
        backgroundImageMainScreen.setFitWidth(START_MENU_WIDTH + 50);
        backgroundImageMainScreen.setFitHeight(START_MENU_HEIGHT);
        startWindow.getChildren().add(backgroundImageMainScreen);

        BigNameText title = new BigNameText("Welcome to SLogo");
        title.setTranslateX(125);
        title.setTranslateY(125);
        startWindow.getChildren().add(title);

        return startWindow;
    }

    @Override
    public void setParameters() {

    }

    @Override
    public void initIDE() {

    }

    private static class BigNameText extends StackPane {
        /**
         * @param Name
         */
        public BigNameText(String Name) {
            Text titleText = new Text(Name);
            titleText.setFont(Font.font("Rockwell", FontWeight.BOLD, 60));
            titleText.setFill(textAndBoxGradient);
            getChildren().add(titleText);
        }
    }
}