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

    public void setProperties(Object o) {
        return;
    }

    @Override
    public double executeCommand(ParseTreeNode node) {
    	//TODO DO we need this
    	List<ParseTreeNode> args = node.getChildren();
        return args.get(args.size() - 1).getValue();
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
