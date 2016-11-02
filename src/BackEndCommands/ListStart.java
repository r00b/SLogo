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

    public void setProperties(Object o) {
        return;
    }

    @Override
    public double executeCommand(ParseTreeNode node) {
        List<ParseTreeNode> args = node.getChildren();
        double answer = 0;
        for (ParseTreeNode child : args) {
            answer = child.executeCommand(child);
        }
        return answer;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
