package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * executes the Heading command
 * @author ezra
 *
 */
public class Heading extends TurtleCommand {
	private static final int ARGS = 0;
	
	/**
	 * Returns the rotate property value
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		return properties.getRotateProperty().get();
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}