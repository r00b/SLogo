package BackEndCommands;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ObservableProperties;

/**
 * NOTE SHOULD MAKE SET FOR X AND Y THAT TAKE INTO ACCOUNT SIZE TURTLE AND CENTER IT
 * An abstract class that all Turtle Commands and Turtle Queries extend. This parent class is needed
 * because all these commands need access to ObservableProperties whether it is for getting or setting
 * All children of this class have access to an instance of the ObservableProperties class
 * @author ezra
 *
 */
public abstract class TurtleCommand implements Command {
	protected ObservableProperties properties;
	@Override
	public double executeCommand(List<Double> args) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numArguments() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Sets the properties file in the class.
	 * @param prop
	 */
	public void setProperties(ObservableProperties prop) {
		properties = prop;
	}
	
	/**
	 * Calculates the distance between two points. Method is called by the Home, ClearScreen, SetXY commands
	 * @param x2
	 * @param x1
	 * @param y2
	 * @param y1
	 * @return The distance between two points 
	 */
	protected double calculateTotalDistance(double x2, double x1, double y2, double y1) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	/**
	 * Calculates the X distance the turtle travels when it moves. Called by forward and back commands
	 * @param hyp Distance of the hypotenuse 
	 * @return X distance traveled
	 */
	protected double calculateXDistance(double hyp) {
		double angle = Math.toDegrees(properties.getRotateProperty().get());
		double answer = Math.cos(angle) * hyp;
		//Second and fourth quadrant are actually flipped so you need to multiply by negative one
		if ((angle > 270) || (angle>90 && angle < 180)) {
			answer = answer  * -1;
		}
		return answer;
	}

	/**
	 * Calculates the Y distance the turtle travels when it moves. Called by the forward and back commands
	 * @param hyp
	 * @return Y disntace traveled
	 */
	protected double calculateYDistance(double hyp) {
		double angle = Math.toDegrees(properties.getRotateProperty().get());
		double answer = Math.sin(angle) * hyp;
		if ((angle > 270) || (angle>90 && angle < 180)) {
			answer = answer  * -1;
		}
		return answer;
	}
	
}
