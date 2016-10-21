package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeExecutor;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a repeat statement in Logo.
 */
public class Repeat extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<Double> args) {
        double result = 0;
        ParseTreeExecutor executor = new ParseTreeExecutor();
        for (int i = 1; i < args.get(0) + 1; i++) {
            executor.setVariable(":repcount",i);
            result = executor.executeTree(executables.get(1));
            System.out.println(result);
        }
        return result;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
