package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the quotient command in Logo.
 */
public class Quotient implements Command {

	private static final int ARGS = 2;
	
	/**
	 * Returns the first argument divided by the second argument
	 */
	@Override
	public double executeCommand(List<ParseTreeNode> args) {
		ParseTreeNode arg1 = args.get(0);
		ParseTreeNode arg2 = args.get(1);
		return arg1.executeCommand(arg1.getChildren()) / arg2.executeCommand(arg2.getChildren());
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}