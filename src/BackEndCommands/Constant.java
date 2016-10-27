package BackEndCommands;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a constant in Logo.
 */
public class Constant implements Command {

    private static final int ARGS = 0;

    @Override
    public double executeCommand(ParseTreeNode node) {
        return Double.parseDouble(node.getCommand());
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
