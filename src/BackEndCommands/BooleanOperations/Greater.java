package BackEndCommands.BooleanOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Greater command
 * @author ezra
 *
 */
public class Greater implements Command {
	private static final int ARGS = 2;
	
	/**
	 * Return 1 if the first arg is greater then the second arg 0 otherwise
	 */
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (args.get(0) > args.get(1)) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}