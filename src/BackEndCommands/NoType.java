package BackEndCommands;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents non-conventional commands in Logo
 *         (i.e. method calls for user-defined functions).
 */
public class NoType implements Command {

    private static final int ARGS = 0;

    @Override
    public double executeCommand(List<ParseTreeNode> args) {
        return 0.0;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
