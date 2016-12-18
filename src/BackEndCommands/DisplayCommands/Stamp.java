package BackEndCommands.DisplayCommands;

import BackEndCommands.DisplayCommand;
import BackEndInterpreter.ParseTreeNode;

/**
 * @author Robert Steilberg
 *         <p>
 *         Executes the STAMP command, which draws the image of the turtle in its current
 *         settings on the workspace display and returns the index of the turtle's image
 *         used for the stamp.
 */
public class Stamp extends DisplayCommand {

    private static final int ARGS = 0;

    /**
     * Stamps the current turtle to the display
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        return properties.makeStamp();
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
