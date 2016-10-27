package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

/**
 * Executes PenDown command
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
	public double executeCommand(List<Double> args) {
		properties.getPathVisibleProperty().set(true);

		return 1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
