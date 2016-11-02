package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents an else if statement in Logo.
 */
public class IfElse extends ControlCommand {

    private static final int ARGS = 3;

    @Override
    public double executeCommand(ParseTreeNode node) {
    	ParseTreeNode condition = node.getChild(0);
		ParseTreeNode trueCommand = node.getChild(1);
		ParseTreeNode falseCommand = node.getChild(2);
		Double value1 = condition.executeCommand(condition);
        if (value1 == 0) {
            return falseCommand.executeCommand(falseCommand);
        } else {
            return trueCommand.executeCommand(trueCommand);
        }
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
