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

    @Override
    public double executeCommand(List<ParseTreeNode> args) {
        ParseTreeNode arg1 = args.get(0);
        return Math.log(arg1.executeCommand(arg1.getChildren()));
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}