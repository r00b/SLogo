package BackEndCommands.MathOperations;

import BackEndInterpreter.Command;
import BackEndInterpreter.ParseTreeNode;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the tan command in Logo.
 */
public class Tangent implements Command {

    private static final int ARGS = 1;

    public void setProperties(Object o) {
        return;
    }

    /**
     * Returns the tan of the arg angle in degrees
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        double value1 = arg1.executeCommand(arg1);
        return Math.tan(Math.toRadians(value1));
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}