package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the Log command
 * @author ezra
 *
 */
public class Log implements Command {

	private static final int ARGS = 1;
	
	/**
	 * Returns the natural log of the argument
	 */
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