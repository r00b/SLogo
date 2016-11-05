package BackEndCommands;

import BackEndInterpreter.Command;
import BackEndInterpreter.ParseTreeNode;

import java.util.List;

/**
 * @author Robert Steilberg
 *         <p>
 *         This command instance represents the beginning of a group in Logo.
 */
public class GroupStart implements Command {

    private static final int ARGS = 1;

    public void setProperties(Object o) {
        return;
    }

    @Override
    public double executeCommand(ParseTreeNode node) {
        List<ParseTreeNode> args = node.getChildren();
        double answer = 0;
        for (ParseTreeNode child : args) {
            answer += child.executeCommand(child);
        }
        return answer;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
