package BackEndCommands.BooleanOperations;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the Equal command
 * @author ezra
 *
 */
public class Equal implements Command {
	private static final int ARGS = 2;
	
	/**
	 * Returns 1 if the two args are equal to each other 0 otherwise
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		ParseTreeNode arg2 = node.getChild(1);
		Double value1 = arg1.executeCommand(arg1);
		Double value2 = arg2.executeCommand(arg2);
		double answer = 0;
		if (value1.equals(value2)) {
			answer++;
		}
		return answer;
	}



    @Override
    public int numArguments() {
        return ARGS;
    }

}