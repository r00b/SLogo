package BackEndCommands.TurtleCommands;

import BackEndCommands.TurtleCommand;
import BackEndInterpreter.ParseTreeNode;

public class ID extends TurtleCommand {
    private static final int ARGS = 0;

    /**
     *
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        return properties.getCurrentID();
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
