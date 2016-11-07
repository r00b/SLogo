package GUIController;
import BackEndInterpreter.FrontendObservableProperties;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import java.util.List;

/**
 * Created by Grayson on 11/01/2016.
 */
public class Turtle {
    private ImageView turtleView;
    private double id;
    private boolean visibility = true;
    private List<Line> turtleMotion = new ArrayList<>();
    private FrontendObservableProperties properties;
    public Turtle(FrontendObservableProperties newProperty, ImageView myImage) {
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

    public void setVisibility(boolean isVisible){
        visibility = isVisible;
    }

    public boolean isVisible(){
        return visibility;
    }

    public List<Line> getLines() {
    	return turtleMotion;
    }

    public FrontendObservableProperties getProperties(){
        return properties;
    }

}

