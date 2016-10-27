package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;
import org.apache.velocity.runtime.directive.Parse;

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
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        double value1 = arg1.executeCommand(arg1);
        return Math.cos(value1);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}