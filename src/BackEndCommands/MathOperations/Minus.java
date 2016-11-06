package BackEndCommands.MathOperations;

import BackEndInterpreter.Command;
import BackEndInterpreter.ParseTreeNode;


/**
 * Executes the Minus command
 *
 * @author ezra
 */
public class Minus implements Command {

    private static final int ARGS = 1;

    public void setProperties(Object o) {
        return;
    }

    /**
     * Returns the negated value of the argument
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        double value1 = arg1.executeCommand(arg1);
        return value1 * -1;

    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}