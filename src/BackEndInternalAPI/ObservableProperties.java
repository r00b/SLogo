package BackEndInternalAPI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * A class that contains all the observable properties from the front end and is passed 
 * to commands.
 * NOTE MAKING THESE PROPERTIES STATIC RIGHT NOW SO ALL OF THE MODEL CAN ACCESS IT
 * NOTE ALSO NEED TO CHANGE ALL COMMANDS TO REFERENCE GETTER
 * @author ezra
 *
 */
public class ObservableProperties {
	public static BooleanProperty imageVisibleProperty;
	public static DoubleProperty rotateProperty;
	public static DoubleProperty xProperty;
	public static DoubleProperty yProperty;
	public static BooleanProperty pathVisibleProperty; 
	
	public ObservableProperties() {
		imageVisibleProperty = new SimpleBooleanProperty(true);
		rotateProperty = new SimpleDoubleProperty(0);
		xProperty = new SimpleDoubleProperty(0);
		yProperty = new SimpleDoubleProperty(0);
		pathVisibleProperty = new SimpleBooleanProperty(true);
	}

	public static BooleanProperty getImageVisibleProperty() {
		return imageVisibleProperty;
	}

	public static DoubleProperty getRotateProperty() {
		return rotateProperty;
	}

	public static DoubleProperty getxProperty() {
		return xProperty;
	}

	public static DoubleProperty getyProperty() {
		return yProperty;
	}

	public static BooleanProperty getPathVisibleProperty() {
		return pathVisibleProperty;
	}
}
