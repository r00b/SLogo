package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the Difference command
 * @author ezra
 *
 */
public class Difference implements Command {
	public static final int ARGS = 2;
	
	/**
	 * Returns the difference between the two arguments
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        ParseTreeNode arg2 = node.getChild(1);
        double value1 = arg1.executeCommand(arg1);
        double value2 = arg2.executeCommand(arg2);
		return value1 - value2;
	}

    @Override
    public int numArguments() {
        return ARGS;
    }
}
