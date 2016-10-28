package BackEndCommands.BooleanOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the And command
 * @author ezra
 *
 */
public class And implements Command {

	private static final int ARGS = 2;

	public void setProperties(Object o) {
		return;
	}

	/**
	 * Returns 1 if two inputs are non zero and 0 otherwise
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		ParseTreeNode arg2 = node.getChild(1);
		double value1 = arg1.executeCommand(arg1);
		double value2 = arg2.executeCommand(arg2);
		if (value1 != 0 && value2 != 0) {
			return 1;
		}
		return 0;
	}
	
    @Override
    public int numArguments() {
        return ARGS;
    }
}