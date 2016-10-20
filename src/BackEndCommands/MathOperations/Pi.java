package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the pi command in Logo.
 */
public class Pi implements Command {

    private static final int ARGS = 0;

    @Override
    public double executeCommand(List<Double> args) {
        return Math.PI;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}