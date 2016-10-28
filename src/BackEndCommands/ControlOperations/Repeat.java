package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a repeat statement in Logo.
 */
public class Repeat extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(ParseTreeNode node) {
    	ParseTreeNode arg1 = node.getChild(0);
		ParseTreeNode arg2 = node.getChild(1);
		double limit = arg1.getCommandObj().executeCommand(arg1);
        double result = 0;
        for (double i = 1.0; i < limit + 1; i++) {
            // TODO REMOVE REPCOUNT FROM VARS AT END OF EXECUTION
            getVariables().put(":repcount",i);
            result = arg2.getCommandObj().executeCommand(arg2);
            System.out.println(result);
        }
        return result;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
