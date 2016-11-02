package BackEndCommands.TurtleCommands;


import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the ShowTurtle Command
 * @author ezra
 *
 */
public class ShowTurtle extends TurtleCommand {
	private static final int ARGS = 0;

	/**
	 * Sets imageView visible property to true
	 * Returns 1
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		properties.setImageVisibleProperty(true);
		return 1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
