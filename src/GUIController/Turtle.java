package GUIController;

import javafx.scene.image.ImageView;

public class Turtle {
    private ImageView turtleView;
    private int id;
    private boolean isActive;
    public Turtle(){
        
    }
    
    public void setImage(ImageView img){
        turtleView = img;
    }
    
    public ImageView getImage(){
        return turtleView;
    }
    
    public int getID(){
        return id;
    }
    
    public void setID(int i){
        id = i;
    }
    
    public boolean isActive(){
        return isActive;
    }


}

