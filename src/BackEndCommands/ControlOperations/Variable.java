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
    public double executeCommand(List<ParseTreeNode> args) {
        String varKey = args.get(0).getCommand();
        if (getVariables().get(varKey) == null) {
            return 0;
        }
        return getVariables().get(varKey);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
