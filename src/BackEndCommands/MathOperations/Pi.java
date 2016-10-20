package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Pi command
 * @author ezra
 *
 */
public class Pi implements Command {
	private static final int ARGS = 0;
	
	/**
	 * Returns the value of Pi
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return Math.PI;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}