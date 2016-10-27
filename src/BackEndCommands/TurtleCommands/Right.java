package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the right command
 * @author ezra
 *
 */
public class Right extends TurtleCommand {
	private static final int ARGS = 1;

	/**
	 * Updates the rotation of image clockwise and updates the rotation property
	 * Returns the degrees rotated
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		double value1 = arg1.executeCommand(arg1);
		properties.getRotateProperty().set(properties.getRotateProperty().get() % 360 + value1);
		return value1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
