package BackEndCommands.TurtleQueries;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the IsPenDown command
 *
 * @author ezra
 */
public class IsPenDown extends TurtleCommand {
    private static final int ARGS = 0;

    @Override

    /**
     * Gets the path visible property and return 1 if its true and 0 if its false
     */
    public double executeCommand(ParseTreeNode node) {
        double answer = 0;
        if (properties.getPathVisibleProperty()) {
            answer++;
        }
        return answer;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}