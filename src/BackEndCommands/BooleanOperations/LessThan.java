package BackEndCommands.BooleanOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the less? command in Logo.
 */
public class LessThan implements Command {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<ParseTreeNode> args) {
    	ParseTreeNode arg1 = args.get(0);
		ParseTreeNode arg2 = args.get(1);
		double value1 = arg1.executeCommand(arg1.getChildren());
		double value2 = arg2.executeCommand(arg2.getChildren());
        double answer = 0;
        if (value1 < value2) {
            answer++;
        }
        return answer;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}