package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the log command in Logo.
 */
public class NaturalLog implements Command {

    private static final int ARGS = 1;

    public void setProperties(Object o) {
        return;
    }

    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        double value1 = arg1.executeCommand(arg1);
        return Math.log(value1);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}