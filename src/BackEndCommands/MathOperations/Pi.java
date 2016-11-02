package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the Pi command
 *
 * @author ezra
 */
public class Pi implements Command {

    private static final int ARGS = 0;

    public void setProperties(Object o) {
        return;
    }

    /**
     * Returns the value of Pi
     */
    @Override
    public double executeCommand(ParseTreeNode node) {
        return Math.PI;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}