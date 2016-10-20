package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Random Command
 * @author ezra
 *
 */
public class Random implements Command {
	private static final int ARGS = 1;
	
	/**
	 * Returns a random double between zero and argument specifiec
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return Math.random() * args.get(0);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}