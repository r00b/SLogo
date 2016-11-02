package BackEndCommands.TurtleCommands;


import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes IsPenDown command
 * @author ezra
 *
 */
public class PenDown extends TurtleCommand {
	private static final int ARGS = 0;

	/**
	 * Sets path visible property to false
	 * Returns 0
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		properties.setPathVisibleProperty(true);
		return 1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
