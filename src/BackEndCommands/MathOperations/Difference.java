package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the difference command in Logo.
 */
public class Difference implements Command {

    public static final int ARGS = 2;

    @Override
    public double executeCommand(List<Double> args) {
        return args.get(0) - args.get(1);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
