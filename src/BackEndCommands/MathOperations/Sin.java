package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Sin command
 * @author ezra
 *
 */
public class Sin implements Command {
	private static final int ARGS = 1;
	
	/**
	 * Returns the degrees of the sin with angle being the arg given
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return Math.sin(args.get(0)) ;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}