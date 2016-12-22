package BackEndCommands.DisplayCommands;

import BackEndCommands.DisplayCommand;
import BackEndInterpreter.ParseTreeNode;

/**
 * @author Robert Steilberg
 *         <p>
 *         Executes the CLEARSTAMPS command, which removes all stamps that have been made and
 *         returns 1 if there were any stamps cleared, 0 otherwise.
 */
public class ClearStamps extends DisplayCommand {

    private static final int ARGS = 0;

    /**
     * Clears all stamps that have been made on the display
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        return properties.clearStamps();
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
