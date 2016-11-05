package BackEndCommands.TurtleQueries;

import BackEndCommands.TurtleCommand;
import BackEndInterpreter.ParseTreeNode;

/**
 * executes the Heading command
 *
 * @author ezra
 */
public class Heading extends TurtleCommand {
    private static final int ARGS = 0;

    /**
     * Returns the rotate property value
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        return properties.getRotateProperty();
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}