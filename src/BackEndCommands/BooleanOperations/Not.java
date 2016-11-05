package BackEndCommands.BooleanOperations;

import BackEndInterpreter.Command;
import BackEndInterpreter.ParseTreeNode;


/**
 * Executes the Not command
 *
 * @author ezra
 */
public class Not implements Command {
    private static final int ARGS = 1;

    public void setProperties(Object o) {
        return;
    }

    /**
     * Returns 1 if the arg is zero 0 otherwise
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        double value1 = arg1.executeCommand(arg1);
        if (value1 == 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}