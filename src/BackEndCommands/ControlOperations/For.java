package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.ParseTreeNode;



/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a dotimes statement in Logo.
 */
public class For extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(ParseTreeNode node) {
    	ParseTreeNode arg1 = node.getChild(0);
		ParseTreeNode commandBody = node.getChild(1);
		String variable = arg1.getChild(0).getRawCommand();
		getVariables().put(variable, 1.0);
        double start = arg1.getChild(1).executeCommand(arg1.getChild(1));
        double end = arg1.getChild(2).executeCommand(arg1.getChild(2));
        double increment = arg1.getChild(3).executeCommand(arg1.getChild(3));
        double result = 0;
        for (double i = start; i < end; i += increment) {
            getVariables().put(variable,i);
            result = commandBody.executeCommand(commandBody);
        }
        return result;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
