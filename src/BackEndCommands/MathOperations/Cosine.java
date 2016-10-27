package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the cos command in Logo.
 */
public class Cosine implements Command {

    private static final int ARGS = 1;

    /**
	 * Returns cos of angle of degrees
	 */
    @Override
    public double executeCommand(List<ParseTreeNode> args) {
        ParseTreeNode arg1 = args.get(0);
        return Math.cos(arg1.executeCommand(arg1.getChildren()));
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}