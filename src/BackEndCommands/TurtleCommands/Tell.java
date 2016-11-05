package BackEndCommands.TurtleCommands;

import BackEndCommands.TurtleCommand;
import BackEndInterpreter.ParseTreeNode;

public class Tell extends TurtleCommand {
    private static final int ARGS = 1;

    /**
     *
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        return properties.performTell(node);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
