package BackEndCommands;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents the beginning of a list in Logo.
 */
public class ListStart implements Command {

    private static final int ARGS = 1;

    @Override
    public double executeCommand(List<ParseTreeNode> args) {
    	double answer = 0;
    	for (ParseTreeNode node : args) {
    		answer = node.getCommandObj().executeCommand(node.getChildren());
    	}
        return answer;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
