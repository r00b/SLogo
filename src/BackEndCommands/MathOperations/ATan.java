package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the ATan command
 * @author ezra
 *
 */
public class ATan implements Command {
	private static final int ARGS = 1;
	
	/**
	 * Returns ATan of degrees
	 */
	@Override
	public double executeCommand(List<Double> args) {
		//Still need to account for zero argument
		return Math.atan(args.get(0));
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}