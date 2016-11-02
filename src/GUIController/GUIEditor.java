package GUIController;

import FrontEndExternalAPI.Editor;
import GUI.EditorHelp;
//import com.intellij.openapi.vcs.changes.FileHolderComposite;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIEditor implements Editor {
    private static final int BACKDROP_X = 10;//620;
    private static final int BACKDROP_Y = 350;//600;
    private static final int BACKDROP_WIDTH = 600;//970;
    private static final int BACKDROP_HEIGHT = 240;//280;
    private Pane window;
    private Paint border;
    private Rectangle backdrop;
    private String defaultCommand = "Enter command here";
    private String currentDir = System.getProperty("user.home");
    private TextArea textArea;
    private EditorHelp helpWindow;
    private ImageView helpButton;
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private Stage myStage;

    /**
     * @param p
     * @param borderColor
     */
    public GUIEditor(Pane p, Paint borderColor, Stage stage) {
        this.window = p;
        this.border = borderColor;
        drawEditor();
        addTextLabel();
        addTextArea();
        addHelpButton();
        addButtons();
//        addRunButton();
        myStage = stage;
    }

    /**
     *
     */
    private void drawEditor() {
        backdrop = new Rectangle(BACKDROP_WIDTH, BACKDROP_HEIGHT, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateX(BACKDROP_X);
        backdrop.setTranslateY(BACKDROP_Y);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }

    private void addTextLabel() {
        Text label = new Text("Editor");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        label.setTranslateX(BACKDROP_X + 10);
        label.setTranslateY(BACKDROP_Y + 20);
        window.getChildren().add(label);
    }

    private void addTextArea() {
        textArea = new TextArea();
        textArea.setTranslateX(BACKDROP_X + 10);
        textArea.setTranslateY(BACKDROP_Y + 40);
        textArea.setPrefSize(BACKDROP_WIDTH - 30, BACKDROP_HEIGHT - 50);
        textArea.setWrapText(true);
        textArea.setText("> " + defaultCommand);
        textArea.positionCaret(textArea.getText().length());
        textArea.setOnMouseClicked(e -> {
            if (textArea.getText().equals("> " + defaultCommand))
                textArea.setText("> ");
            textArea.positionCaret(textArea.getText().length());
        });
        textArea.opacityProperty().setValue(0.5);
        textArea.setOnMouseEntered(e -> {
            backdrop.opacityProperty().setValue(0.8);
            textArea.opacityProperty().setValue(0.8);
        });
        textArea.setOnMouseExited(e -> textArea.opacityProperty().setValue(0.5));
        window.getChildren().add(textArea);
    }

    private void addHelpButton() {
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/help.png"));
        helpButton = new ImageView(newImage);
        helpButton.setOnMouseClicked(e -> helpHandler());
        helpButton.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        helpButton.setTranslateX(backdrop.getTranslateX() + backdrop.getWidth() - 35);
        helpButton.setTranslateY(backdrop.getTranslateY() + 10);
        helpButton.setFitWidth(30);
        helpButton.setFitHeight(30);
        window.getChildren().add(helpButton);
    }

    private void helpHandler() {
        Stage s = new Stage();
        helpWindow = new EditorHelp(s);
        helpWindow.init();
    }

    private void addButtons() {
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/clear.png"));
        ImageView clearImg = new ImageView(newImage);
        Button clear = newButton("Clear", clearImg, BACKDROP_X + 180, BACKDROP_Y);
        clear.setOnMouseClicked(e -> textArea.setText("> Enter command here"));

        newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/open.png"));
        clearImg = new ImageView(newImage);
        Button openFileButton = newButton("Open file", clearImg, BACKDROP_X + 330, BACKDROP_Y);
        openFileButton.setOnMouseClicked(e -> openFile());

        newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/save.png"));
        clearImg = new ImageView(newImage);
        Button saveFileButton = newButton("Save file", clearImg, BACKDROP_X + 450, BACKDROP_Y);
        saveFileButton.setOnMouseClicked(e -> saveFile());

        window.getChildren().addAll(clear, openFileButton, saveFileButton);
    }

    private Button newButton(String text, ImageView imgV, int x, int y) {
        imgV.setFitWidth(25);
        imgV.setFitHeight(25);
        Button run = new Button(text, imgV);
        run.setStyle(overButton);
        run.setOnMouseEntered(e -> {
            run.setStyle(buttonFill);
            backdrop.opacityProperty().setValue(0.8);
        });
        run.setOnMouseExited(e -> run.setStyle(overButton));
        run.setTranslateX(x);
        run.setTranslateY(y);
        return run;
    }

    /**
     * Saves a file of Logo commands
     *
     * @author Robert H. Steilberg II
     */
    private void saveFile() {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("LOGO files (*.logo)", "*.logo");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(myStage);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(getCurrentText().substring(2));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openFile() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(myStage);
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("LOGO files (*.logo)", "*.logo");
//        chooser.getExtensionFilters().add(extFilter);

        if (textArea.getText().equals("> " + defaultCommand)) {
            textArea.setText("> ");
        }
        try {
            Scanner s = new Scanner(file).useDelimiter("\n");
            while (s.hasNext()) {
                textArea.appendText(s.next() + " \n"); // else read the next token
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (NullPointerException e) {
            System.out.println("Reached null value in file");
        }
    }


    /**
     * @return
     */
    public Rectangle getBackdrop() {
        return backdrop;
    }

    /**
     *
     */
    public void startNewCommand() {
        textArea.setText(textArea.getText() + "\n> ");
    }

//    /**
//     *
//     * @param width
//     * @param height
//     */
//    public void bindNodes(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height){
//        textArea.prefWidthProperty().bind(width.subtract(650));
//        textArea.prefHeightProperty().bind(height.subtract(660));
//        helpButton.translateXProperty().bind(width.subtract(50));
//    }

    @Override
    /**
     *
     */
    public String getCurrentText() {
        return textArea.getText();
    }

    /**
     * @param str
     */
    public void redoCommand(String str) {
        textArea.setText(textArea.getText() + "\n> " + str);
    }


}
