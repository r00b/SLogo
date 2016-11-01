package GUIController;

import FrontEndExternalAPI.Editor;
import GUI.EditorHelp;
import javafx.beans.property.ReadOnlyDoubleProperty;
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
import java.util.Scanner;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIEditor implements Editor {
    private Pane window;
    private Paint border;
    private Rectangle backdrop;
    private String defaultCommand = "Enter command here";
    private String currentDir = System.getProperty("user.home");
    private TextArea textArea;
    private EditorHelp helpWindow;
    private FileChooser fileChooser = new FileChooser();
    private ImageView helpButton;
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";

    /**
     *
     * @param p
     * @param borderColor
     */
    public GUIEditor(Pane p, Paint borderColor) {
        this.window = p;
        this.border = borderColor;
        drawEditor();
        addTextLabel();
        addTextArea();
        addHelpButton();
        addButtons();
//        addRunButton();
    }

    /**
     *
     */
    private void drawEditor(){
        backdrop = new Rectangle(970, 280, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(600);
        backdrop.setTranslateX(620);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }

    private void addTextLabel(){
        Text label = new Text("Editor");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        label.setTranslateX(630);
        label.setTranslateY(620);
        window.getChildren().add(label);
    }

    private void addTextArea(){
        textArea = new TextArea();
        textArea.setTranslateX(630);
        textArea.setTranslateY(640);
        textArea.setPrefSize(940, 240);
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

    private void addHelpButton(){
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

    private void helpHandler(){
        Stage s = new Stage();
        helpWindow = new EditorHelp(s);
        helpWindow.init();
    }

    private void addButtons(){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/clear.png"));
        ImageView clearImg = new ImageView(newImage);
        Button clear = newButton("Clear", clearImg, 800, 600);
        clear.setOnMouseClicked(e -> textArea.setText("> Enter command here"));
        newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/upload.png"));
        clearImg = new ImageView(newImage);
        Button upload = newButton("Upload file", clearImg, 900, 600);
        upload.setOnMouseClicked(e -> uploadFile());
        window.getChildren().addAll(clear, upload);
    }

    private Button newButton(String text, ImageView imgV, int x, int y){
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

    private void uploadFile(){
        Stage stage = new Stage();
//        fileChooser
//        fileChooser.setInitialDirectory();
//
//        FileChooser fileChooser = new FileChooser();
//
////Extention filter
////        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
////        fileChooser.getExtensionFilters().add(extentionFilter);
//
////Set to user directory or go to default if cannot access
//        File userDirectory = new File(userDirectoryString);
//        if(!userDirectory.canRead()) {
//            userDirectory = new File("c:/");
//        }
//        fileChooser.setInitialDirectory(userDirectory);
//
////Choose the file
//        File chosenFile = fileChooser.showOpenDialog(stage);
////Make sure a file was selected, if not return default
//        String path;
//        if(chosenFile != null) {
//            path = chosenFile.getPath();
//            userDirectoryString = path;
//        } else {
//            //default return value
//            path = null;
//        }



        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
//        String directoryPath;
//        directoryPath = file.getAbsolutePath();
//        File init
//        fileChooser.setInitialDirectory(file);
//        fileChooser.setInitialDirectory(fileChooser.sets);
//        if(file != null){
//            openf
//        }
        if(textArea.getText().equals("> " + defaultCommand)){
            textArea.setText("> ");
        }
        try {
            Scanner s = new Scanner(file).useDelimiter("\n");
            while (s.hasNext()) {
//                if (s.hasNextInt()) { // check if next token is an int
//                    textArea.appendText(s.nextInt() + " "); // display the found integer
//                } else {
                    textArea.appendText(s.next() + " \n"); // else read the next token
//                }
//                textArea.appendText(s.next());
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        catch (NullPointerException e){
            System.out.println("Reached null value in file");
        }
    }

    private void fileToEditor(FileChooser fileChooser){
//        fileChooser.ge
    }

    /**
     *
     * @return
     */
    public Rectangle getBackdrop(){
        return backdrop;
    }

    /**
     *
     */
    public void startNewCommand(){
        textArea.setText(textArea.getText() + "\n> ");
    }

    /**
     *
     * @param width
     * @param height
     */
    public void bindNodes(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height){
        textArea.prefWidthProperty().bind(width.subtract(650));
        textArea.prefHeightProperty().bind(height.subtract(660));
        helpButton.translateXProperty().bind(width.subtract(50));
    }

    @Override
    /**
     *
     */
    public String getCurrentText() {
        return textArea.getText();
    }

    /**
     *
     * @param str
     */
    public void redoCommand(String str) {
        textArea.setText(textArea.getText() + "\n> " + str);
    }


}
