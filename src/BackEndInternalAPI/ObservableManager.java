package BackEndInternalAPI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;

public interface ObservableManager {
	public boolean getNewLineProperty();
	public void setNewLineProperty(boolean value);
	public boolean getClearScreenProperty();
	public void setClearScreenProperty(boolean value);
	public boolean getImageVisibleProperty();
	public void setImageVisibleProperty(boolean value);
	public double getRotateProperty();
	public void setRotateProperty(double value);
	public double getXProperty();
	public void setXProperty(double value);
	public double getYProperty();
	public void setYProperty(double value);
	public boolean getPathVisibleProperty();
	public void setPathVisibleProperty(boolean value);
	public void addActiveTurtle(double id);
	/**
	 * Calculates the distance between two points. Method is called by the Home, ClearScreen, SetXY commands
	 * @param x2
	 * @param x1
	 * @param y2
	 * @param y1
	 * @return The distance between two points 
	 */
	double calculateTotalDistance(double x2, double x1, double y2, double y1);
	/**
	 * Calculates the X distance the turtle travels when it moves. Called by forward and back commands
	 * @param hyp Distance of the hypotenuse 
	 * @return X distance traveled
	 */
	double calculateXDistance(double hyp);

	/**
	 * Calculates the Y distance the turtle travels when it moves. Called by the forward and back commands
	 * @param hyp
	 * @return Y disntace traveled
	 */
	double calculateYDistance(double hyp);
}
