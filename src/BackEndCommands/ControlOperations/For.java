package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.ParseTreeExecutor;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a dotimes statement in Logo.
 */
public class For extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<ParseTreeNode> args) {
    	ParseTreeNode arg1 = args.get(0);
		ParseTreeNode arg2 = args.get(1);
		String variable = arg1.getChild(0).getCommand();
		variables.put(variable, 1.0);
        double start = arg1.getChild(1).executeCommand(arg1.getChild(1).getChildren());
        double end = arg1.getChild(1).executeCommand(arg1.getChild(2).getChildren());
        double increment = arg1.getChild(1).executeCommand(arg1.getChild(3).getChildren());

        double result = 0;

        for (double i = start; i < end; i += increment) {
            variables.put(variable,i);
            result = arg2.getCommandObj().executeCommand(arg2.getChildren());
            //System.out.println(result);
        }
        return result;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
