package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the IsShowing command
 * @author ezra
 *
 */
public class IsShowing extends TurtleCommand {
	private static final int ARGS = 0;
	
	/**
	 * Gets the visible turtle property value and return 1 if its visible and 0 if its not
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		double answer = 0;
		if (properties.getImageVisibleProperty()) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}