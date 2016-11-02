package GUIController;

import BackEndInternalAPI.ObservableProperties;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class Turtle {
    private ImageView turtleView;
    private double id;
    private boolean isActive;
    private boolean visibility = true;
    private List<Line> turtleMotion = new ArrayList<>();
    private ObservableProperties properties;
    public Turtle(ObservableProperties newProperty){
        properties = newProperty;
    }
    
    public void setImage(ImageView img){
        turtleView = img;
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

