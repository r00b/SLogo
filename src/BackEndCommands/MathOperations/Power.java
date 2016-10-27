package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;
import org.apache.velocity.runtime.directive.Parse;

import java.util.List;

/**
 * Executes the Pow command
 * @author ezra
 *
 */
public class Power implements Command {

	private static final int ARGS = 2;
	
	/**
	 * Returns the first argument to the power of the second value
	 */
	@Override
	public double executeCommand(List<ParseTreeNode> args) {
        ParseTreeNode arg1 = args.get(0);
        ParseTreeNode arg2 = args.get(1);
		return Math.pow(arg1.executeCommand(arg1.getChildren()), arg2.executeCommand(arg2.getChildren()));
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}