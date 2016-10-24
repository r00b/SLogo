package BackEndCommands;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents
 */
public class NoType implements Command {

    private static final int ARGS = 0;

    @Override
    public double executeCommand(List<Double> args) {
        return args.get(args.size() - 1);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
