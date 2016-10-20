package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the atan command in Logo.
 */
public class ArcTangent implements Command {
    private static final int ARGS = 1;

    @Override
    public double executeCommand(List<Double> args) {
        //Still need to account for zero argument
        return Math.atan(args.get(0));
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}