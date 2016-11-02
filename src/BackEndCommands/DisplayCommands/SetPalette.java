package BackEndCommands.DisplayCommands;

import BackEndCommands.DisplayCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * @author ezra
 */
public class SetPalette extends DisplayCommand {
    private static final int ARGS = 4;

    /**
     * Sets the pen color of the display
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        ParseTreeNode arg1 = node.getChild(0);
        double color = arg1.executeCommand(arg1);
        properties.setPenColor(color);
        double red = node.getChild(1).executeCommand(node.getChild(1));
        double green = node.getChild(2).executeCommand(node.getChild(2));
        double blue = node.getChild(3).executeCommand(node.getChild(3));
        properties.setPallete(red, green, blue);
        return color;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
