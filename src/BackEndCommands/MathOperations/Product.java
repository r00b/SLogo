package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;


/**
 * Executes the Product Command
 * @author ezra
 *
 */
public class Product implements Command {
	private static final int ARGS = 2;
	
	/**
	 * Returns the two arguments multiplied by each other
	 */
	@Override
	public double executeCommand(List<Double> args) {
		return args.get(0) * args.get(1);
	}

    @Override
    public int numArguments() {
        return ARGS;
    }
}
