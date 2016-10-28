package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * Executes the Pow command
 * @author ezra
 *
 */
public class Power implements Command {

	private static final int ARGS = 2;

	public void setProperties(Object o) {
		return;
	}

	/**
	 * Returns the first argument to the power of the second value
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        ParseTreeNode arg2 = node.getChild(1);
        double value1 = arg1.executeCommand(arg1);
        double value2 = arg2.executeCommand(arg2);
        return Math.pow(value1,value2);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}