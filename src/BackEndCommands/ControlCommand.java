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
    private Map<String, Double> myVariables;

    public void setVariables(Map<String, Double> vars) {
        myVariables = vars;
    }

    protected Map<String, Double> getVariables() {
        return myVariables;
    }

    @Override
    public abstract double executeCommand(ParseTreeNode node);

    @Override
    public abstract int numArguments();



}
