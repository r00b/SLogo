package BackEndCommands.TurtleCommands;

import BackEndCommands.TurtleCommand;
import BackEndInterpreter.ParseTreeNode;

/**
 * @author ezra
 */
public class Turtles extends TurtleCommand {
    private static final int ARGS = 0;

    /**
     *
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        return properties.getTurtleCount();
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
