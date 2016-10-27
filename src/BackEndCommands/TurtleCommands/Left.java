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
	public double executeCommand(List<ParseTreeNode> args) {
		ParseTreeNode arg1 = args.get(0);
		double value1 = arg1.executeCommand(arg1.getChildren());
		properties.getRotateProperty().set(properties.getRotateProperty().get() % 360 - value1);
		return value1;

	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
