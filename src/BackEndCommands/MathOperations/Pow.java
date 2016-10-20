package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Pow command
 * @author ezra
 *
 */
public class Pow implements Command {
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