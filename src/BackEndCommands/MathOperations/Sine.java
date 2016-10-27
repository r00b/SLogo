package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the sin command in Logo.
 */
public class Sine implements Command {

    private static final int ARGS = 1;

    /**
	 * Returns the sin with angle being the arg given
	 */
    @Override
    public double executeCommand(List<ParseTreeNode> args) {
        ParseTreeNode arg1 = args.get(0);
        return Math.sin(Math.toRadians(arg1.executeCommand(arg1.getChildren())));
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}