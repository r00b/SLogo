package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Remainder command
 * @author ezra
 *
 */
public class Remainder implements Command {
	private static final int ARGS = 2;
	
	/**
	 * Returns the remainder of the first arg divided by the second arg
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return args.get(0) % args.get(1);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}