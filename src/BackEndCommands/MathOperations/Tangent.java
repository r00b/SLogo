package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the tan command in Logo.
 */
public class Tangent implements Command {

    private static final int ARGS = 1;

    @Override
    public double executeCommand(List<Double> args) {
        return Math.tan(args.get(0));
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}