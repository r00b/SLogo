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
public class Unknown implements Command {

    private static final int ARGS = 0;

    public void setProperties(Object o) {
        return;
    }

    @Override
    public double executeCommand(ParseTreeNode node) {
        return 0.0;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
