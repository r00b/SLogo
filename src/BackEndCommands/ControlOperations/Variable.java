package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInterpreter.ParseTreeNode;

/**
 * @author Robert Steilberg
 *         <p>
 *         This command instance represents a Variable in Logo.
 */
public class Variable extends ControlCommand {
    private static final int ARGS = 0;

    @Override
    public double executeCommand(ParseTreeNode node) {
        String varKey = node.getRawCommand();
        // first test and see if accessing a method's instance variable
        if (getMethodVariables().get(varKey) != null) {
            return getMethodVariables().get(varKey);
        }
        if (getVariables().get(varKey) != null) {
            return getVariables().get(varKey);
        }
        return 0; // no variable assignment found
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}