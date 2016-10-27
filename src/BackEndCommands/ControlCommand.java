package BackEndCommands;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents control command types Logo.
 */
public abstract class ControlCommand implements Command {

   // protected List<ParseTreeNode> executables;
    protected Map<String, Double> variables = new HashMap<String, Double>();

    @Override
    public abstract double executeCommand(List<ParseTreeNode> args);

    @Override
    public abstract int numArguments();

    public void setExecutables(List<ParseTreeNode> rootsToAdd) {
        executables = rootsToAdd;
    }

}
