package BackEndCommands.BooleanOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Or command
 * @author ezra
 *
 */
public class Or implements Command{
	private static final int ARGS = 2;
	
	/**
	 * Returns 1 if one of the arguments doesn't equal 0, 0 otherwise
	 */
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (args.get(0) != 0 || args.get(1) != 0) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}