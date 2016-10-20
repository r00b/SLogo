package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * Executes the Pow command
 * @author ezra
 *
 */
public class Power implements Command {
	private static final int ARGS = 2;
	
	/**
	 * Returns the first argument to the power of the second value
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return Math.pow(args.get(0), args.get(1));
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}