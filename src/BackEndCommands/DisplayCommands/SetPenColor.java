package BackEndCommands.DisplayCommands;

import BackEndCommands.DisplayCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * @author ezra
 */
public class SetPenColor extends DisplayCommand {
    private static final int ARGS = 1;

    /**
     * Sets the pen color of the display
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        double value = arg1.executeCommand(arg1);
        properties.setPenColor(value);
        return value;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
