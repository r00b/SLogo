package BackEndCommands.ControlOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents the beginning of a list in Logo.
 */
public class If implements Command {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<Double> args) {
        // if args0 = 1 {


        return args.get(args.size() - 1);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
