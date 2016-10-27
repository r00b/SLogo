package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;
import org.apache.velocity.runtime.directive.Parse;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the tan command in Logo.
 */
public class Tangent implements Command {

    private static final int ARGS = 1;

    /**
	 * Returns the tan of the arg angle in degrees
	 */
    @Override
    public double executeCommand(List<ParseTreeNode> args) {
        ParseTreeNode arg1 = args.get(0);
        return Math.tan(Math.toRadians(arg1.executeCommand(arg1.getChildren())));
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}