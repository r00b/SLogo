package BackEndCommands.TurtleCommands;

import java.util.List;
import BackEndInternalAPI.ParseTreeNode;
import BackEndCommands.TurtleCommand;
/**
 * Executes the Towards command
 * @author ezra
 *
 */
public class Towards extends TurtleCommand{
	private static final int ARGS = 2;

	/**
	 * Turns the turtle to face the point given and updates the rotation
	 * Returns the degrees the image rotates to get to new point clockwise
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		ParseTreeNode arg2 = node.getChild(1);
		double value1 = arg1.executeCommand(arg1);
		double value2 = arg2.executeCommand(arg2);
		double degrees = calculateDegrees(value1, value2);
		double oldDegrees = properties.getRotateProperty();
		properties.setRotateProperty(degrees);
		return Math.abs(degrees - oldDegrees);
	}
	@Override
	public int numArguments() {
		return ARGS;
	}

	/**
	 * Calculates the degrees and adjusts the angle depending on what quadrant it is in
	 * @param x x coord
	 * @param y y coord
	 * @return Returns the angle in degrees the position given is
	 */
	private double calculateDegrees(double x, double y) {
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
		return answer;
	}
}
