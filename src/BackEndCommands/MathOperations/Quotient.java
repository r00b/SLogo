package BackEndCommands.MathOperations;

import BackEndInterpreter.Command;
import BackEndInterpreter.ParseTreeNode;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the quotient command in Logo.
 */
public class Quotient implements Command {

    private static final int ARGS = 2;

    public void setProperties(Object o) {
        return;
    }

    /**
     * Returns the first argument divided by the second argument
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        ParseTreeNode arg2 = node.getChild(1);
        double value1 = arg1.executeCommand(arg1);
        double value2 = arg2.executeCommand(arg2);
        return value1 / value2;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}