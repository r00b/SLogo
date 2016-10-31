package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents the make command in Logo.
 */
public class MakeVariable extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        ParseTreeNode arg2 = node.getChild(1);
        String varName = arg1.getRawCommand();
        double varVal = arg2.executeCommand(arg2);
        getVariables().put(varName,varVal);
        return varVal;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
