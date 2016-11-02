package BackEndCommands.TurtleCommands;


import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the SetHeading Command
 *
 * @author ezra
 */
public class SetHeading extends TurtleCommand {
    private static final int ARGS = 1;

    /**
     * Turns turtle to absolute heading
     * Returns degrees moved. Note that returns the amount of degrees moved clockwise to get to new point
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        double answer = properties.setRotateProperty(arg1, true, true);
        return answer;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
