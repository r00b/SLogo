package BackEndCommands.BooleanOperations;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the And command
 * @author ezra
 *
 */
public class And implements Command{
	private static final int ARGS = 2;
	
	/**
	 * Returns 1 if two inputs are non zero and 0 otherwise
	 */
	@Override
	public double executeCommand(List<ParseTreeNode> args) {
		ParseTreeNode arg1 = args.get(0);
		ParseTreeNode arg2 = args.get(1);
		double value1 = arg1.executeCommand(arg1.getChildren());
		double value2 = arg2.executeCommand(arg2.getChildren());
		double answer = 0;
		if (value1 != 0 && value2 != 0) {
			answer++;
		}
		return answer;
	}
	
    @Override
    public int numArguments() {
        return ARGS;
    }
}