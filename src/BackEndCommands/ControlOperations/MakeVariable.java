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
        ParseTreeNode variable = node.getChild(0);
        ParseTreeNode value = node.getChild(1);
        String varName = variable.getRawCommand();
        double varVal = value.executeCommand(value);
        getVariables().put(varName,varVal);
        return varVal;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
