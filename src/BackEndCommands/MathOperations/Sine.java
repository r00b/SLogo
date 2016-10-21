package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the sin command in Logo.
 */
public class Sine implements Command {

    private static final int ARGS = 1;

    /**
	 * Returns the sin with angle being the arg given
	 */
    @Override
    public double executeCommand(List<Double> args) {
        return Math.sin(Math.toRadians(args.get(0)));
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}