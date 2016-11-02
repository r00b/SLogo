package BackEndCommands;

import BackEndInternalAPI.*;
import javafx.collections.ObservableMap;

import java.util.Map;

/**
 * @author Robert H. Steilberg II
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents control command types Logo.
 */
public abstract class ControlCommand implements Command {

    private Mappings maps; // holds variable and method declarations

    /**
     * Sets a mapping object since all control commands need
     * access to variable and method mappings
     *
     * @param o is the object to set
     */
    @Override
    public void setProperties(Object o) {
        if (o.getClass().equals(Mappings.class)) {
            maps = (Mappings) o;
        }
    }

    protected ObservableMap<String, Double> getVariables() {
        return maps.getMyVariables();
    }

    protected Map<String, Double> getMethodVariables() {
        return maps.getMyMethodVariables();
    }

    protected Map<String, LogoMethod> getMethods() {
        return maps.getMyMethods();
    }

    protected Map<String, Integer> getMethodDeclarations() {
        return maps.getMyMethodDeclarations();
    }

    @Override
    public abstract double executeCommand(ParseTreeNode node);

    @Override
    public abstract int numArguments();
}