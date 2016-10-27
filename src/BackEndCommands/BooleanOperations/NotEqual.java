package BackEndCommands.BooleanOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * Executes the NotEqual command
 * @author ezra
 *
 */
public class NotEqual implements Command{

	private static final int ARGS = 2;
	
	/**
	 * Returns 1 if the two commands are not equal 0 otherwise
	 */
	@Override
	public double executeCommand(List<ParseTreeNode> args) {
        ParseTreeNode arg1 = args.get(0);
        ParseTreeNode arg2 = args.get(1);
        Double ans1 = arg1.executeCommand(arg1.getChildren());
        Double ans2 = arg2.executeCommand(arg2.getChildren());
        if (ans1.equals(ans2)) {
            return 0;
        }
        return 1;
	}

    @Override
    public int numArguments() {
        return ARGS;
    }
}