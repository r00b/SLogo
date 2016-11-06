package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInterpreter.ParseTreeNode;

/**
 * @author Robert Steilberg
 *         <p>
 *         This command instance represents an If statement in Logo.
 */
public class If extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(ParseTreeNode node) {
    	ParseTreeNode condition = node.getChild(0);
		ParseTreeNode body = node.getChild(1);
		Double value = condition.executeCommand(condition);
        if (value == 0) {
            return 0;
        } else {
            return body.executeCommand(body);
        }
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}