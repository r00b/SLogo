package BackEndCommands.BooleanOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * Executes the Not command
 * @author ezra
 *
 */
public class Not implements Command{
	private static final int ARGS = 1;
	
	/**
	 * Returns 1 if the arg is zero 0 otherwise
	 */
	@Override
	public double executeCommand(List<ParseTreeNode> args) {
		ParseTreeNode arg1 = args.get(0);
		if (arg1.executeCommand(arg1.getChildren()) == 0) {
			return 1;
		}
		return 0;
	}

    @Override
    public int numArguments() {
        return ARGS;
    }
}