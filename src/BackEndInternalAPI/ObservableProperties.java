package BackEndInternalAPI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;

/**
 * A class that contains all the observable properties from the front end and is passed 
 * to commands. Note that an instance of this class will be passed to the backend and for the commands to alter
 * @author ezra
 *
 */
public class ObservableProperties {
	private  BooleanProperty imageVisibleProperty;
	private  DoubleProperty rotateProperty;
	private  DoubleProperty xProperty;
	private  DoubleProperty yProperty;
	private  BooleanProperty pathVisibleProperty; 
	private BooleanProperty newLineProperty;
	private BooleanProperty clearScreenProperty;

	public ObservableProperties(ImageView turtle) {
		imageVisibleProperty = new SimpleBooleanProperty(true);
		rotateProperty = new SimpleDoubleProperty(0);
		xProperty = new SimpleDoubleProperty(turtle.getX());
		yProperty = new SimpleDoubleProperty(turtle.getY());
		pathVisibleProperty = new SimpleBooleanProperty(true);
		newLineProperty = new SimpleBooleanProperty(false);
		clearScreenProperty = new SimpleBooleanProperty(false);
		turtle.xProperty().bind(xProperty);
		turtle.yProperty().bind(yProperty);
		turtle.rotateProperty().bind(rotateProperty);
		turtle.visibleProperty().bind(imageVisibleProperty);
	}

	public BooleanProperty getNewLineProperty() {
		return newLineProperty;
	}

	public BooleanProperty getClearScreenProperty() {
		return clearScreenProperty;
	}

	public BooleanProperty getImageVisibleProperty() {
		return imageVisibleProperty;
	}

	public DoubleProperty getRotateProperty() {
		return rotateProperty;
	}

	public DoubleProperty getXProperty() {
		return xProperty;
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
	public BooleanProperty getPathVisibleProperty() {
		return pathVisibleProperty;
	}
}
