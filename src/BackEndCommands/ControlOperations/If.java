package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents an if statement in Logo.
 */
public class If extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(ParseTreeNode node) {
    	ParseTreeNode arg1 = node.getChild(0);
		ParseTreeNode arg2 = node.getChild(1);
		Double value1 = arg1.getCommandObj().executeCommand(arg1);
        if (value1 == 0) {
            return 0;
        } else {
            return arg2.getCommandObj().executeCommand(arg2);
        }
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
