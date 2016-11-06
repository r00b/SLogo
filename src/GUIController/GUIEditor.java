package GUIController;
import Base.NodeFactory;
import FrontEndExternalAPI.Editor;
import GUI.EditorHelp;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
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
    private static final int BACKDROP_X = 10;
    private static final int BACKDROP_Y = 350;
    private static final int BACKDROP_WIDTH = 600;
    private static final int BACKDROP_HEIGHT = 240;
    private Pane window;
    private Paint border;
    private Rectangle backdrop;
    private String defaultCommand = "Enter command here";
    private TextArea textArea;
    private ImageView helpButton;
    private Stage myStage;
    private EditorHelp helpWindow;
    private NodeFactory myFactory = new NodeFactory();

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
        myStage = stage;
    }

    private void drawEditor() {
        backdrop = myFactory.makeBackdrop(border, BACKDROP_WIDTH, BACKDROP_HEIGHT, BACKDROP_X, BACKDROP_Y);
        window.getChildren().add(backdrop);
    }

    private void addTextLabel() {
        Text label = myFactory.makeTitle("Editor", BACKDROP_X + 10, BACKDROP_Y + 20);
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
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
        helpButton = myFactory.makeHelpButton(backdrop.getTranslateX() + backdrop.getWidth() - 35,
                backdrop.getTranslateY() + 10);
        helpButton.setOnMouseClicked(e -> helpHandler());
        helpButton.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        window.getChildren().add(helpButton);
    }

    private void helpHandler() {
        Stage s = new Stage();
        helpWindow = new EditorHelp(s);
        helpWindow.init();
    }

    private void addButtons() {
        Button clear = myFactory.makeClearButton(BACKDROP_X + 180, BACKDROP_Y);
        clear.setOnMouseEntered(e -> {
            clear.setStyle(myFactory.getButtonFill());
            backdrop.opacityProperty().setValue(0.8);
        });
        clear.setOnMouseClicked(e -> textArea.setText("> Enter command here"));

        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/open.png"));
        ImageView clearImg = new ImageView(newImage);
        Button openFileButton = myFactory.makeButton("Open file", clearImg, BACKDROP_X + 330, BACKDROP_Y);
        openFileButton.setOnMouseEntered(e -> {
            openFileButton.setStyle(myFactory.getButtonFill());
            backdrop.opacityProperty().setValue(0.8);
        });
        openFileButton.setOnMouseClicked(e -> openFile());

        newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/save.png"));
        clearImg = new ImageView(newImage);
        Button saveFileButton = myFactory.makeButton("Save file", clearImg, BACKDROP_X + 450, BACKDROP_Y);
        saveFileButton.setOnMouseEntered(e -> {
            saveFileButton.setStyle(myFactory.getButtonFill());
            backdrop.opacityProperty().setValue(0.8);
        });
        saveFileButton.setOnMouseClicked(e -> saveFile());

        window.getChildren().addAll(clear, openFileButton, saveFileButton);
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
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void openFile() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(myStage);
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

    @Override
    public Rectangle getBackdrop() {
        return backdrop;
    }

    @Override
    public void startNewCommand() {
        textArea.setText(textArea.getText() + "\n> ");
    }

    @Override
    public String getCurrentText() {
        return textArea.getText();
    }

    @Override
    public void redoCommand(String str) {
        textArea.setText(textArea.getText() + "\n> " + str);
    }
}
