package BackEndCommands.ControlOperations;

import java.util.List;

import BackEndInternalAPI.Command;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a variable in Logo.
 */
public class Variable implements Command {
    private static final int ARGS = 0;

    @Override
    public double executeCommand(List<Double> args) {
        return args.get(0);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
