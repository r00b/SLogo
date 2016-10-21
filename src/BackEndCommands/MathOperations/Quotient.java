package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the quotient command in Logo.
 */
public class Quotient implements Command {

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