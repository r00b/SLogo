package BackEndCommands.BooleanOperations;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the Or command
 * @author ezra
 *
 */
public class Or implements Command{
	private static final int ARGS = 2;
	
	/**
	 * Returns 1 if one of the arguments doesn't equal 0, 0 otherwise
	 */
	@Override
	public double executeCommand(List<ParseTreeNode> args) {
		ParseTreeNode arg1 = args.get(0);
		ParseTreeNode arg2 = args.get(1);
		Double ans1 = arg1.executeCommand(arg1.getChildren());
		Double ans2 = arg2.executeCommand(arg2.getChildren());
		if (ans1 != 0 || ans2 != 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}