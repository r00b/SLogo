package BackEndCommands.TurtleQueries;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the XCor command
 *
 * @author ezra
 */
public class XCor extends TurtleCommand {
    private static final int ARGS = 0;

    /**
     * Gets the x property value and return the value
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        return properties.getXProperty();
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}