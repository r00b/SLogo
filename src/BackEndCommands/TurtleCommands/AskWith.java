package BackEndCommands.TurtleCommands;

import BackEndCommands.TurtleCommand;
import BackEndInterpreter.ParseTreeNode;

public class AskWith extends TurtleCommand {
    private static final int ARGS = 2;

    /**
     *
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        return properties.performAskWith(node);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
