package BackEndInternalAPI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

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

	public ObservableProperties() {
		imageVisibleProperty = new SimpleBooleanProperty(true);
		rotateProperty = new SimpleDoubleProperty(0);
		xProperty = new SimpleDoubleProperty(0);
		yProperty = new SimpleDoubleProperty(0);
		pathVisibleProperty = new SimpleBooleanProperty(true);
		newLineProperty = new SimpleBooleanProperty(false);
		clearScreenProperty = new SimpleBooleanProperty(false);
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

	public DoubleProperty getYProperty() {
		return yProperty;
	}

	public BooleanProperty getPathVisibleProperty() {
		return pathVisibleProperty;
	}
}
