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

    public void setProperties(Object o) {
        return;
    }

	/**
	 * Returns 1 if the two commands are not equal 0 otherwise
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        ParseTreeNode arg2 = node.getChild(1);
        Double value1 = arg1.executeCommand(arg1);
        Double value2 = arg2.executeCommand(arg2);
        if (value1.equals(value2)) {
            return 0;
        }
        return 1;
	}

    @Override
    public int numArguments() {
        return ARGS;
    }
}