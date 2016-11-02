package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a dotimes statement in Logo.
 */
public class DoTimes extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode varNode = node.getChild(0).getChild(0);
        ParseTreeNode limitNode = node.getChild(0).getChild(1);
        ParseTreeNode body = node.getChild(1);

        String variable = varNode.getRawCommand();
        double limit = limitNode.executeCommand(limitNode);
//
//        String variable = node.getChild(0).getChild(0).getRawCommand();
//        ParseTreeNode limit = node.getChild(0).getChild(1);
//
//
//
//    	ParseTreeNode arg1 = node.getChild(0);
//		ParseTreeNode arg2 = node.getChild(1);
//		String variable = arg1.getChild(0).getRawCommand();
		getVariables().put(variable, 1.0);
//		double limit = arg1.executeCommand(arg1);
		//double value2 = arg2.executeCommand(arg2.getChildren());
        
        double result = 0;
        for (double i = getVariables().get(variable); i <= limit; i++) {
            getVariables().put(variable,i);
            result = body.executeCommand(body);
        }
        return result;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
