package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeExecutor;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a dotimes statement in Logo.
 */
public class DoTimes extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<Double> args) {
        String variable = executables.get(0).getChild(0).getCommand();
        double result = 0;

        ParseTreeExecutor executor = new ParseTreeExecutor();
        for (double i = executor.getVariable(variable); i < args.get(0); i++) {
            executor.setVariable(variable,i);
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
