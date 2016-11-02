package GUIController;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class Turtle {
    private ImageView turtleView;
    private double id;
    private boolean isActive;
    private List<Line> turtleMotion = new ArrayList<>();
    public Turtle(ImageView turtle){
        turtleView = turtle;
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

    public void drawLine(){

    }
    public List<Line> getLines() {
    	return turtleMotion;
    }


}

