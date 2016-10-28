package BackEndCommands;

import BackEndInternalAPI.*;

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
    private Map<String, Double> myMethodVariables;
    private Map<String, LogoMethod> myMethods;
    private Mappings maps;


    @Override
    public void setProperties(Object o) {
        if (o.getClass().equals(Mappings.class)) {
            maps = (Mappings) o;
        }
    }


    public void setInfo(Map<String, Double> variables, Map<String, Double> methodVariables, Map<String, LogoMethod> methods) {
        myVariables = variables;
        myMethodVariables = methodVariables;
        myMethods = methods;
    }


    protected Map<String, Double> getVariables() {
        return maps.getMyVariables();
    }

    protected Map<String, Double> getMethodVariables() {
        return maps.getMyMethodVariables();
    }

    protected Map<String, LogoMethod> getMethods() {
        return maps.getMyMethods();
    }

    @Override
    public abstract double executeCommand(ParseTreeNode node);

    @Override
    public abstract int numArguments();


}
