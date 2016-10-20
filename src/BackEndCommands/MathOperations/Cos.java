package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes Cos command
 * @author ezra
 *
 */
public class Cos implements Command {
	private static final int ARGS = 1;
	
	/**
	 * Returns cos of angle in degrees
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return Math.cos(args.get(0));
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}