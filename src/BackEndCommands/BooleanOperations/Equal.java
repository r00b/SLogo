package BackEndCommands.BooleanOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * Executes the Equal command
 * @author ezra
 *
 */
public class Equal implements Command {
	private static final int ARGS = 2;
	
	/**
	 * Returns 1 if the two args are equal to each other 0 otherwise
	 */
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (args.get(0).equals(args.get(1))) {
			answer++;
		}
		return answer;
	}



    @Override
    public int numArguments() {
        return ARGS;
    }

}