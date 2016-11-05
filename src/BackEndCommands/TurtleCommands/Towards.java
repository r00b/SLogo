package BackEndCommands.TurtleCommands;

import BackEndInterpreter.ParseTreeNode;
import BackEndCommands.TurtleCommand;

/**
 * Executes the Towards command
 *
 * @author ezra
 */
public class Towards extends TurtleCommand {
    private static final int ARGS = 2;

    /**
     * Turns the turtle to face the point given and updates the rotation
     * Returns the degrees the image rotates to get to new point clockwise
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        ParseTreeNode arg2 = node.getChild(1);
        double degrees = properties.calculateDegrees(arg1, arg2);
        return Math.abs(degrees);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
