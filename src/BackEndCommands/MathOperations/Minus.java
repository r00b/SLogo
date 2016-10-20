package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the minus command in Logo.
 */
public class Minus implements Command {

    private static final int ARGS = 1;
	
	@Override
	public double executeCommand(List<Double> args) {
		return args.get(0) * -1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}