package GUIController;

import FrontEndExternalAPI.Variables;
import GUI.VariablesHelp;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.Event;
import java.util.ArrayList;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIVariables implements Variables {
    private Pane window;
    private Paint border;
    private Rectangle backdrop;
    private VariablesHelp helpWindow;
    private TableView table = new TableView();
    private TableColumn variableNameCol, valueCol;
    private final ObservableList<Variable> data = FXCollections.observableArrayList();

    public GUIVariables(Pane p, Paint bodercolor){
        this.window = p;
        this.border = bodercolor;
        drawVariables();
        addTextLabel();
        addHelpButton();
        createTableView();
        addVariableManually();
    }

    private void drawVariables(){
        backdrop = new Rectangle(600, 230, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(110);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }

    private void addTextLabel(){
        Text label = new Text("Declared Variables");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        label.setTranslateX(20);
        label.setTranslateY(130);
        window.getChildren().add(label);
    }

    private void addHelpButton(){
        Image newImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/help.png"));
        ImageView helpButton = new ImageView(newImage);
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
        helpWindow = new VariablesHelp(s);
        helpWindow.init();
    }

    private void createTableView(){
        //WHY CANT I GET THIS TO BE FUCKING EDITABLE
        variableNameCol = new TableColumn("Variable Name");
        variableNameCol.setPrefWidth(250);
        variableNameCol.setCellValueFactory(
                new PropertyValueFactory<Variable, String>("variableName"));
        variableNameCol.setEditable(true);
        variableNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Variable, String>>() {
                    @Override
                    public void handle(CellEditEvent<Variable, String> t) {
                        ((Variable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setVariableName(t.getNewValue());
                    }
                }
        );
        valueCol = new TableColumn("Value");
        valueCol.setPrefWidth(250);
        valueCol.setCellValueFactory(
                new PropertyValueFactory<Variable, Double>("variableValue"));
        valueCol.setEditable(true);
        valueCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Variable, Double>>() {
                    @Override
                    public void handle(CellEditEvent<Variable, Double> t) {
                        ((Variable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setVariableValue(t.getNewValue());
                    }
                }
        );
        table.getColumns().addAll(variableNameCol, valueCol);
        table.setTranslateX(20);
        table.setTranslateY(140);
        table.setPrefSize(570, 190);
        table.opacityProperty().setValue(0.5);
        table.setItems(data);
        table.setOnMouseEntered(e -> {
            table.opacityProperty().setValue(0.8);
            backdrop.opacityProperty().setValue(0.8);
        });
        table.setOnMouseExited(e -> table.opacityProperty().setValue(0.5));
        window.getChildren().add(table);
    }

    @Override
    public void addVariable(String name, double value) {
        boolean contains = false;
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).getVariableName().equals(name.substring(1))){
                contains = true;
                data.get(i).setVariableValue(value);
                System.out.println("the value is " + value);
                break;
            }
        }
        if(!contains) {
            data.add(new Variable(name.substring(1), value));
        }
        table.setItems(data);

        table.setEditable(true);
        System.out.println("the data is " + data.size());
    }

    private void addVariableManually(){
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("Variable name");
        addFirstName.setMaxWidth(variableNameCol.getPrefWidth());
        addFirstName.setTranslateX(20);
        addFirstName.setTranslateY(310);
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(valueCol.getPrefWidth());
        addLastName.setPromptText("Variable value");
        addLastName.setTranslateX(270);
        addLastName.setTranslateY(310);
        final TextField addEmail = new TextField();

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Variable(addFirstName.getText(),
                        Double.parseDouble(addLastName.getText())));
                addFirstName.clear();
                addLastName.clear();
            }
        });
        addButton.setTranslateX(520);
        addButton.setTranslateY(310);
        table.setEditable(true);

        window.getChildren().addAll(addFirstName, addLastName, addButton);
    }

    @Override
    public ArrayList<Integer> getAllVariables() {
        return null;
    }

    public static class Variable {

        private final SimpleStringProperty variableName;
        private final SimpleDoubleProperty variableValue;
//        private final SimpleStringProperty email;

        private Variable(String vName, double vValue) {
            this.variableName = new SimpleStringProperty(vName);
            this.variableValue = new SimpleDoubleProperty(vValue);
//            this.email = new SimpleStringProperty(email);
        }

        public String getVariableName() {
            return variableName.get();
        }

        public void setVariableName(String fName) {
            variableName.set(fName);
        }

        public double getVariableValue() {
            return variableValue.get();
        }

        public void setVariableValue(double fName) {
            variableValue.set(fName);
        }
    }
}
