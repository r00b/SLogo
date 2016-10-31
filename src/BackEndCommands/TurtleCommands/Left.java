package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;


/**
 * Executes the left command
 * @author ezra
 *
 */
public class Left extends TurtleCommand {
	private static final int ARGS = 1;

	/**
	 * Updates the rotate property of the imageView counterclockwise by the parameter
	 * Returns the degrees rotated
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		double value1 = arg1.executeCommand(arg1);
		properties.setRotateProperty(properties.getRotateProperty() % 360 - value1);
		return value1;

	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
