package BackEndCommands;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents control command types Logo.
 */
public abstract class ControlCommand implements Command {

    protected List<ParseTreeNode> executables;

    @Override
    public abstract double executeCommand(List<ParseTreeNode> args);

    @Override
    public abstract int numArguments();

    public void setExecutables(List<ParseTreeNode> rootsToAdd) {
        executables = rootsToAdd;
    }

}
