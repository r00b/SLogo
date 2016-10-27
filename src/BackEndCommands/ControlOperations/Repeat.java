package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeExecutor;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;
import java.util.Map;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a repeat statement in Logo.
 */
public class Repeat extends ControlCommand {

    private static final int ARGS = 2;
    private Map<String, Double> variables;

    @Override
    public double executeCommand(List<ParseTreeNode> args) {
    	ParseTreeNode arg1 = args.get(0);
		ParseTreeNode arg2 = args.get(1);
		double limit = arg1.getCommandObj().executeCommand(arg1.getChildren());
        double result = 0;
        for (double i = 1.0; i < limit + 1; i++) {
            variables.put(":repcount",i);
            result = arg2.getCommandObj().executeCommand(arg2.getChildren());
            System.out.println(result);
        }
        return result;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
