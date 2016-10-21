package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;


/**
 * Executes the Sum command
 * @author ezra
 *
 */
public class Sum implements Command {
	private static final int ARGS = 2;
	
	/**
	 * Returns the sum of the two arguments
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return args.get(0) + args.get(1);
	}
	@Override
	public int numArguments() {
		return ARGS;
	}
}
