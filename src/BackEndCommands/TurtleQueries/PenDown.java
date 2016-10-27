package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the PenDown command
 * @author ezra
 *
 */
public class PenDown extends TurtleCommand {
	private static final int ARGS = 0;
	@Override
	
	/**
	 * Gets the path visible property and return 1 if its true and 0 if its false
	 */
	public double executeCommand(List<ParseTreeNode> args) {
		double answer = 0;
		if (properties.getPathVisibleProperty().get()) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}