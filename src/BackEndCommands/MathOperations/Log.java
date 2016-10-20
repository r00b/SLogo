package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Log command
 * @author ezra
 *
 */
public class Log implements Command {
	private static final int ARGS = 1;
	
	/**
	 * Returns the natural log of the argument
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return Math.log(args.get(0));
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}