package BackEndInternalAPI;

import GUIController.GUIDisplay;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;

/**
 * A class that contains all the observable properties from the front end and is passed 
 * to commands. Note that an instance of this class will be passed to the backend and for the commands to alter
 * @author ezra
 *
 */
public class ObservableProperties implements ObservableManager{
	private  BooleanProperty imageVisibleProperty;
	private  DoubleProperty rotateProperty;
	private  DoubleProperty xProperty;
	private  DoubleProperty yProperty;
	private  BooleanProperty pathVisibleProperty; 
	private BooleanProperty newLineProperty;
	private BooleanProperty clearScreenProperty;

	public ObservableProperties(ImageView turtle, GUIDisplay myDisplay) {
		imageVisibleProperty = new SimpleBooleanProperty(true);
		rotateProperty = new SimpleDoubleProperty(0);
		xProperty = new SimpleDoubleProperty(turtle.getX());
//		xProperty.great
		yProperty = new SimpleDoubleProperty(turtle.getY());
		pathVisibleProperty = new SimpleBooleanProperty(true);
		newLineProperty = new SimpleBooleanProperty(false);
		clearScreenProperty = new SimpleBooleanProperty(false);
		turtle.xProperty().bind(xProperty);
		turtle.yProperty().bind(yProperty);
		turtle.rotateProperty().bind(rotateProperty);
		turtle.visibleProperty().bind(imageVisibleProperty);
		setupListeners(myDisplay);
	}
	
	private void setupListeners(GUIDisplay myDisplay) {
		// TODO Auto-generated method stub
		newLineProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//If new value is true we need to draw a new line
				if (newValue) {
					myDisplay.drawNewLine();
				}
				newLineProperty.set(false);
			}
    	});
		
    	pathVisibleProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				myDisplay.setVisibility(newValue);
			}
    	});
    	
    	clearScreenProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//If new value is true we need to draw a new line
				if (newValue) {
					myDisplay.clearScreen();
				}
				clearScreenProperty.set(false);
			}
    	});
	}

	public boolean getNewLineProperty() {
		return newLineProperty.get();
	}

	public boolean getClearScreenProperty() {
		return clearScreenProperty.get();
	}

	public boolean getImageVisibleProperty() {
		return imageVisibleProperty.get();
	}

	public double getRotateProperty() {
		return rotateProperty.get();
	}

	public double getXProperty() {
		return xProperty.get();
	}
	public void setXProperty(double value) {
		xProperty.set(value);
	}
	public double getYProperty() {
		return -yProperty.get();
	}
	
	public void setYProperty(double value) {
		yProperty.set(-value);
	}
	public boolean getPathVisibleProperty() {
		return pathVisibleProperty.get();
	}

	@Override
	public void addActiveTurtle(double id) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Calculates the distance between two points. Method is called by the Home, ClearScreen, SetXY commands
	 * @param x2
	 * @param x1
	 * @param y2
	 * @param y1
	 * @return The distance between two points 
	 */
	public double calculateTotalDistance(double x2, double x1, double y2, double y1) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	/**
	 * Calculates the X distance the turtle travels when it moves. Called by forward and back commands
	 * @param hyp Distance of the hypotenuse 
	 * @return X distance traveled
	 */
	public double calculateXDistance(double hyp) {
		double angle = getRotateProperty();
		double answer = Math.sin(Math.toRadians(angle)) * hyp;
		//Second and fourth quadrant are actually flipped so you need to multiply by negative one
		return answer;
	}

	/**
	 * Calculates the Y distance the turtle travels when it moves. Called by the forward and back commands
	 * @param hyp
	 * @return Y disntace traveled
	 */
	public double calculateYDistance(double hyp) {
		double angle = getRotateProperty();
		double answer = Math.cos(Math.toRadians(angle)) * hyp;
		return answer;
	}

	@Override
	public void setNewLineProperty(boolean value) {
		// TODO Auto-generated method stub
		newLineProperty.set(value);
	}

	@Override
	public void setClearScreenProperty(boolean value) {
		// TODO Auto-generated method stub
		clearScreenProperty.set(value);
	}

	@Override
	public void setImageVisibleProperty(boolean value) {
		// TODO Auto-generated method stub
		imageVisibleProperty.set(value);
	}

	@Override
	public void setRotateProperty(double value) {
		// TODO Auto-generated method stub
		rotateProperty.set(value);
	}

	@Override
	public void setPathVisibleProperty(boolean value) {
		// TODO Auto-generated method stub
		pathVisibleProperty.set(value);
	}
}
