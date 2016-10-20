package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Difference command
 * @author ezra
 *
 */
public class Difference implements Command {
	public static final int ARGS = 2;
	
	/**
	 * Returns the difference between the two arguments
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return args.get(0) - args.get(1);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}
