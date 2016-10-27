package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;
import groovyjarjarantlr.ParseTree;


/**
 * Executes the Minus command
 * @author ezra
 *
 */
public class Minus implements Command {

    private static final int ARGS = 1;
	
	/**
	 * Returns the negated value of the argument
	 */
	@Override
	public double executeCommand(List<ParseTreeNode> args) {
		ParseTreeNode arg1 = args.get(0);
		return arg1.executeCommand(arg1.getChildren()) * -1;

	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}