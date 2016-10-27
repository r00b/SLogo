package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the YCor command
 * @author ezra
 *
 */
public class YCor extends TurtleCommand {
	private static final int ARGS = 0;
	
	/**
	 * Gets the y property value and returns it
	 */
	@Override
	public double executeCommand(List<ParseTreeNode> args) {
		return properties.getYProperty();
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}