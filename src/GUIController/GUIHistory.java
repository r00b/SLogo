package GUIController;
import Base.NodeFactory;
import FrontEndExternalAPI.History;
import GUI.HistoryHelp;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIHistory implements History {
    private Pane window;
    private Paint border;
    private Rectangle backdrop;
    private ListView<Button> list;
    private ObservableList<Button> oldCommands;
    private String redoCommand;
    private HistoryHelp helpWindow;
    private NodeFactory myFactory = new NodeFactory();

    /**
     *
     * @param p
     * @param bordercoloir
     */
    public GUIHistory(Pane p, Paint bordercoloir){
        this.window = p;
        this.border = bordercoloir;
        drawHistory();
        addTextLabel();
        addHelpButton();
        addListView();
        addClearButton();
    }

    private void drawHistory(){
        backdrop = myFactory.makeBackdrop(border, 600, 580, 10, 600);
        window.getChildren().add(backdrop);
    }
    private void addTextLabel(){
        Text label = myFactory.makeTitle("History", 20, 620);
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        window.getChildren().add(label);
    }

    private void addHelpButton(){
        ImageView helpButton = myFactory.makeHelpButton(backdrop.getTranslateX() + backdrop.getWidth() - 35,
                backdrop.getTranslateY() + 10);
        helpButton.setOnMouseClicked(e -> helpHandler());
        helpButton.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        window.getChildren().add(helpButton);
    }

    private void helpHandler(){
        Stage s = new Stage();
        helpWindow = new HistoryHelp(s);
        helpWindow.init();
    }
    
    private void addClearButton(){
        Button clearButton = myFactory.makeClearButton(200, 600);
        clearButton.setOnMouseEntered(e -> {
            backdrop.opacityProperty().setValue(0.8);
            clearButton.setStyle(myFactory.getButtonFill());
        });
        clearButton.setOnMouseClicked(e -> clear());
        window.getChildren().add(clearButton);
    }

    @Override
    public Rectangle getBackdrop(){
        return backdrop;
    }

    @Override
    public void addCommand(String text) {
        Button newCommand = new Button(text);
        newCommand.setStyle(myFactory.getOverButton());
        newCommand.setOnMouseEntered(e -> {
            newCommand.setStyle(myFactory.getButtonFill());
            backdrop.opacityProperty().setValue(0.8);
        });
        newCommand.setOnMouseExited(e -> newCommand.setStyle(myFactory.getOverButton()));
        newCommand.setOnMouseClicked(e -> {
            unBold();
            newCommand.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            callCommand(newCommand.getText());
        });
        oldCommands.add(0, newCommand);
        list.setItems(oldCommands);
    }

    private void unBold(){
        for(int i = 0; i < list.getItems().size(); i++){
            list.getItems().get(i).setFont(Font.font("Verdana", 15));
        }
    }

    private void addListView(){
        list = new ListView<Button>();
        list.setOrientation(Orientation.HORIZONTAL);
        list.setTranslateX(20);
        list.setTranslateY(625);
        list.setPrefSize(580, 155);
        list.opacityProperty().setValue(0.5);
        list.setOnMouseEntered(e -> {
            list.opacityProperty().setValue(0.8);
            backdrop.opacityProperty().setValue(0.8);
        });
        list.setOnMouseExited(e -> list.opacityProperty().setValue(0.5));
        oldCommands = FXCollections.observableArrayList();
        oldCommands = FXCollections.observableArrayList();
        window.getChildren().add(list);
    }

    @Override
    public void bindNodes(ReadOnlyDoubleProperty height){
        list.prefHeightProperty().bind(height.subtract(650));
    }

    @Override
    public void callCommand(String str) {
        redoCommand = str;
    }

    /**
     *
     * @return
     */
    public String getRedoCommand(){
        return redoCommand;
    }
    
    private void clear(){
        oldCommands.clear();
    }
}
