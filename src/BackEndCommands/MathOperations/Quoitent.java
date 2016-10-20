package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Quotient command
 * @author ezra
 *
 */
public class Quoitent implements Command {
	private static final int ARGS = 2;
	
	/**
	 * Returns the first argument divided by the second argument
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return args.get(0) / args.get(1);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}