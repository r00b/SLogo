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
	public double setRotateProperty(ParseTreeNode value, boolean isAbsolute, boolean sign);
	public double getXProperty();
	public void setXProperty(double value);
	public double getYProperty();
	public void setYProperty(double value);
	public boolean getPathVisibleProperty();
	public void setPathVisibleProperty(boolean value);
	
	double calculateDegrees(ParseTreeNode x, ParseTreeNode y);
	/**
	 * Calculates the distance between two points. Method is called by the Home, ClearScreen, SetXY commands
	 * @param x
	 * @param y
	 * @return The distance between point specified and current turtle position 
	 */
	double calculateTotalDistance(double x, double y1);
	/**
	 * Calculates the X distance the turtle travels when it moves. Called by forward and back commands
	 * @param hyp Distance of the hypotenuse 
	 * @return X distance traveled
	 */
	double calculateXDistance(ParseTreeNode hyp, boolean sign);

	/**
	 * Calculates the Y distance the turtle travels when it moves. Called by the forward and back commands
	 * @param hyp
	 * @return Y disntace traveled
	 */
	double calculateYDistance(ParseTreeNode hyp, boolean sign);
}
