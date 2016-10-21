package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeExecutor;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents an else if statement in Logo.
 */
public class IfElse extends ControlCommand {

    private static final int ARGS = 3;

    @Override
    public double executeCommand(List<Double> args) {
        ParseTreeExecutor executor = new ParseTreeExecutor();
        if (args.get(0) == 0) {
            return executor.executeTree(executables.get(2));
        } else {
            return executor.executeTree(executables.get(1));
        }
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
