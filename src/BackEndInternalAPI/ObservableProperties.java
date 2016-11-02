package BackEndInternalAPI;


import GUIController.GUIDisplay;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;

/**
 * A class that contains all the observable properties from the front end and is passed
 * to commands. Note that an instance of this class will be passed to the backend and for the commands to alter
 *
 * @author ezra
 */
public class ObservableProperties implements ObservableManager{
	private double myId;
	private BooleanProperty imageVisibleProperty; //observable list of booleans changes https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ListChangeListener.Change.html
	private DoubleProperty rotateProperty;
	private double xProperty;
	private double yProperty;
	private BooleanProperty pathVisibleProperty;  
	private BooleanProperty newLineProperty; 
	private BooleanProperty clearScreenProperty;
	
	
	public ObservableProperties(ImageView turtle, GUIDisplay myDisplay, double id) {
		myId = id;
		imageVisibleProperty = new SimpleBooleanProperty(true);
		rotateProperty = new SimpleDoubleProperty(0);
		xProperty = 0;
		yProperty = 0;
		pathVisibleProperty = new SimpleBooleanProperty(true);
		newLineProperty = new SimpleBooleanProperty(false);
		clearScreenProperty = new SimpleBooleanProperty(false);
		turtle.rotateProperty().bind(rotateProperty);
		turtle.visibleProperty().bind(imageVisibleProperty);
		setupListeners(myDisplay);
	}
	
	private void setupListeners(GUIDisplay myDisplay) {
		newLineProperty.addListener((observable, oldValue, newValue) -> {
				//If new value is true we need to draw a new line
				if (newValue) {
					myDisplay.moveTurtle(getXProperty(), getYProperty(), myId);
				}
				newLineProperty.set(false);
		});
		
    	pathVisibleProperty.addListener((observable, oldValue, newValue) -> myDisplay.setVisibility(newValue));
    	
    	clearScreenProperty.addListener((observable, oldValue, newValue) -> {
				//If new value is true we need to draw a new line
				if (newValue) {
					myDisplay.clearScreen();
				}
				clearScreenProperty.set(false);
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
		return xProperty;
	}
	public void setXProperty(double value) {
		xProperty = value;
	}
	public double getYProperty() {
		return yProperty;
	}
	
	public void setYProperty(double value) {
		yProperty = value;
	}
	public boolean getPathVisibleProperty() {
		return pathVisibleProperty.get();
	}

	
	/**
	 * Calculates the distance between two points. Method is called by the Home, ClearScreen, SetXY commands
	 * @param x2
	 * @param x1
	 * @param y2
	 * @param y1
	 * @return The distance between two points 
	 */
	public double calculateTotalDistance(double x1, double y1) {
		return Math.sqrt(Math.pow(getXProperty() - x1, 2) + Math.pow(getYProperty() - y1, 2));
	}
	
	/**
	 * Calculates the X distance the turtle travels when it moves. Called by forward and back commands
	 * @param hyp Distance of the hypotenuse 
	 * @return X distance traveled
	 */
	public double calculateXDistance(ParseTreeNode hyp, boolean sign) {
		double value = hyp.executeCommand(hyp);
		if (!sign) {
			value = -value;
		}
		double angle = getRotateProperty();
		double answer = Math.sin(Math.toRadians(angle)) * value;
		//Second and fourth quadrant are actually flipped so you need to multiply by negative one
		return answer;
	}

	/**
	 * Calculates the Y distance the turtle travels when it moves. Called by the forward and back commands
	 * @param hyp
	 * @return Y distance traveled
	 */
	public double calculateYDistance(ParseTreeNode hyp, boolean sign) {
		double value = hyp.executeCommand(hyp);
		if (!sign) {
			value = -value;
		}
		double angle = getRotateProperty();
		double answer = Math.cos(Math.toRadians(angle)) * value;
		return answer;
	}

	@Override
	public void setNewLineProperty(boolean value) {
		newLineProperty.set(value);
	}

	@Override
	public void setClearScreenProperty(boolean value) {
		clearScreenProperty.set(value);
	}

	@Override
	public void setImageVisibleProperty(boolean value) {
		imageVisibleProperty.set(value);
	}

	@Override
	public double setRotateProperty(ParseTreeNode node, boolean isAbsolute, boolean sign) {
		double value = node.executeCommand(node);
		if (isAbsolute) {
			value = value % 360;
			double oldValue = rotateProperty.get();
			rotateProperty.set(value);
			return Math.abs(value - oldValue);
		}
		if (!sign) {
			value = -value;
		}
		rotateProperty.set(getRotateProperty() % 360 + value);
		return Math.abs(value);
	}

	@Override
	public void setPathVisibleProperty(boolean value) {
		pathVisibleProperty.set(value);
	}

	@Override
	public double calculateDegrees(ParseTreeNode node1, ParseTreeNode node2) {
		double x = node1.executeCommand(node1);
		double y = node2.executeCommand(node2);
		double answer;
		double angle = Math.atan(x / y);
		angle = Math.toDegrees(angle);
		// Second quadrant
		if (x > 0 && y < 0) {
			answer = 90 - angle;
		}
		// Third quadrant
		else if (x < 0 && y < 0) {
			answer = 180 + angle;
		}
		// Fourth quadrant
		else if (x < 0 && y > 0) {
			answer = 270 - angle;
		}
		// Default is first quadrant
		else {
			answer = angle;
		}
		double oldDegrees = rotateProperty.get();
		rotateProperty.set(answer);
		return Math.abs(answer - oldDegrees);
	}
	public double getID() {
		return myId;
	}
}
