package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the SetHeading Command
 * @author ezra
 *
 */
public class SetHeading extends TurtleCommand {
	private static final int ARGS = 1;

	/**
	 * Turns turtle to absolute heading
	 * Returns degrees moved. Note that returns the amount of degrees moved clockwise to get to new point
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		double value1 = arg1.executeCommand(arg1);
		//Need to get actual rotation so use % 
		double set_degrees = value1 % 360;
		double current_degrees = properties.getRotateProperty();
		properties.setRotateProperty(value1);
		return Math.abs(set_degrees - current_degrees);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
