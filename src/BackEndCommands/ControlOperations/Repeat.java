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
    	ParseTreeNode limitNode = node.getChild(0);
		ParseTreeNode body = node.getChild(1);
		double limit = limitNode.getCommandObj().executeCommand(limitNode);
        double result = 0;
        for (double i = 1.0; i < limit + 1; i++) {
            // TODO REMOVE REPCOUNT FROM VARS AT END OF EXECUTION
            getVariables().put(":repcount",i);
            result = body.executeCommand(body);
        }
        return result;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
