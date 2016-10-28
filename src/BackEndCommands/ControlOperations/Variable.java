package BackEndCommands.ControlOperations;

import java.util.List;
import java.util.Map;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a variable in Logo.
 */
public class Variable extends ControlCommand {
    private static final int ARGS = 0;

    @Override
    public double executeCommand(ParseTreeNode node) {
        String varKey = node.getCommand();

        if (getMethodVariables().get(varKey) != null) {
            return getMethodVariables().get(varKey);
        }
        if (getVariables().get(varKey) != null) {
            return getVariables().get(varKey);
        }

        return 0;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
