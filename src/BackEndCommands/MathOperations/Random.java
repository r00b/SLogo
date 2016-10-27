package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;


/**
 * Executes the Random Command
 * @author ezra
 *
 */
public class Random implements Command {

	private static final int ARGS = 1;

	/**
	 * Returns a random double between zero and argument specifiec
	 */
	@Override
	public double executeCommand(List<ParseTreeNode> args) {
		ParseTreeNode arg1 = args.get(0);
		return Math.random() * arg1.executeCommand(arg1.getChildren());
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}