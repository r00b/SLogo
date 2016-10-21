package BackEndCommands.BooleanOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * Executes the NotEqual command
 * @author ezra
 *
 */
public class NotEqual implements Command{
	private static final int ARGS = 2;
	
	/**
	 * Returns 1 if the two commands are not equal 0 otherwise
	 */
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (!(args.get(0).equals(args.get(1)))) {
			answer++;
		}
		return answer;
	}

    @Override
    public int numArguments() {
        return ARGS;
    }
}