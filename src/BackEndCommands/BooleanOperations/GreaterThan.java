package BackEndCommands.BooleanOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the greater? command in Logo.
 */
public class GreaterThan implements Command {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<Double> args) {
        if (args.get(0) > args.get(1)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}