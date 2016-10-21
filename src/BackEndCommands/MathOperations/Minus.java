package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;


/**
 * Executes the Minus command
 * @author ezra
 *
 */
public class Minus implements Command {

    private static final int ARGS = 1;
	
	/**
	 * Returns the negated value of the argument
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return args.get(0) * -1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}