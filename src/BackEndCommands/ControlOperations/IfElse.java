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
    public double executeCommand(List<ParseTreeNode> args) {
    	ParseTreeNode arg1 = args.get(0);
		ParseTreeNode arg2 = args.get(1);
		ParseTreeNode arg3 = args.get(2);
		Double value1 = arg1.getCommandObj().executeCommand(arg1.getChildren());
        if (value1 == 0) {
            return arg3.getCommandObj().executeCommand(arg3.getChildren());
        } else {
            return arg2.getCommandObj().executeCommand(arg2.getChildren());
        }
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
