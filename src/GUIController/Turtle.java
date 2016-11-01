package GUIController;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Turtle {
    private ImageView turtleView;
    private double id;
    private boolean isActive;
    private ArrayList<Line> turtleMotion = new ArrayList<>();
    public Turtle(){
        
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

    public void drawLine(){

    }


}

