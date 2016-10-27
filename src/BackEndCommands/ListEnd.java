package BackEndCommands;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents the end of a list in Logo.
 */
public class ListEnd implements Command {

    private static final int ARGS = 0;

    @Override
    public double executeCommand(List<ParseTreeNode> args) {
        return args.get(args.size() - 1).getValue();
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
