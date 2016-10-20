package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Tan command
 * @author ezra
 *
 */
public class Tan implements Command {
	private static final int ARGS = 1;
	
	/**
	 * Returns the tan of the arg angle in degrees
	 */
	@Override
	public double executeCommand(List<Double> args) {
		//Still need to account for zero input
		return Math.tan(args.get(0));
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}