package GUIController;

import BackEndInterpreter.ObservableProperties;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.util.List;

public class Turtle {
    private ImageView turtleView;
    private double id;
    private boolean isActive;
    private boolean visibility = true;
    private List<Line> turtleMotion = new ArrayList<>();
    private ObservableProperties properties;
    public Turtle(ObservableProperties newProperty, ImageView myImage) {
        turtleView = myImage;
        properties = newProperty;
    }

    public void setImage(Image img){
        turtleView.setImage(img);
    }
    
    public ImageView getImage(){
        return turtleView;
    }
    
    public double getID(){
        return id;
    }
    
    public void setID(double i){
        id = i;
    }
    
    public boolean isActive(){
        return isActive;
    }

    public void setVisibility(boolean isVisible){
        visibility = isVisible;
    }

    public boolean isVisible(){
        return visibility;
    }

    public void drawLine(){

    }
    public List<Line> getLines() {
    	return turtleMotion;
    }

    public ObservableProperties getProperties(){
        return properties;
    }

}

