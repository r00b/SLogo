package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeExecutor;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents an else if statement in Logo.
 */
public class IfElse extends ControlCommand {

    private static final int ARGS = 3;

    @Override
    public double executeCommand(ParseTreeNode node) {
    	ParseTreeNode arg1 = node.getChild(0);
		ParseTreeNode arg2 = node.getChild(1);
		ParseTreeNode arg3 = node.getChild(2);
		Double value1 = arg1.getCommandObj().executeCommand(arg1);
        if (value1 == 0) {
            return arg3.getCommandObj().executeCommand(arg3);
        } else {
            return arg2.getCommandObj().executeCommand(arg2);
        }
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
