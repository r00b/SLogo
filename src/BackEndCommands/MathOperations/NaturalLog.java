package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the log command in Logo.
 */
public class NaturalLog implements Command {

    private static final int ARGS = 1;

    @Override
    public double executeCommand(List<Double> args) {
        return Math.log(args.get(0));
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}