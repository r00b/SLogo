package BackEndCommands.BooleanOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the and command in Logo.
 */
public class And implements Command {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<Double> args) {
        if (args.get(0).equals(1.0) && args.get(1).equals(1.0)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}