package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.ParseTreeExecutor;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a dotimes statement in Logo.
 */
public class For extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<Double> args) {
        ParseTreeExecutor executor = new ParseTreeExecutor();
        // TODO ENSURE THAT WE ARE DOING AN INCLUSIVE FOR
        String variable = executables.get(0).getChild(0).getCommand();
        double start = executor.executeTree(executables.get(0).getChild(1));
        double end = executor.executeTree(executables.get(0).getChild(2));
        double increment = args.get(0);

        double result = 0;

        for (double i = start; i < end; i += increment) {
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
