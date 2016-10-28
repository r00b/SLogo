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

    private Mappings maps;


    @Override
    public void setProperties(Object o) {
        if (o.getClass().equals(Mappings.class)) {
            maps = (Mappings) o;
        }
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
