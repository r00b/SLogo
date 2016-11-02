package BackEndInternalAPI;

import javafx.collections.ObservableMap;

import java.util.Map;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class holds information about variable and method mappings that are used
 *         when executing Logo commands. Specifically, this class holds data structures for
 *         normal variable definitions, method definitions, and temporary method variables.
 */
public class Mappings {

    private ObservableMap<String, Double> myVariables;
    private Map<String, Double> myMethodVariables; // temporary map for method variables
    private Map<String, LogoMethod> myMethods;
    private Map<String, Integer> myMethodDeclarations;

    public Mappings(ObservableMap<String, Double> variables, Map<String, LogoMethod> methods, Map<String, Double> methodVariables, Map<String, Integer> methodDeclarations) {
        myVariables = variables;
        myMethods = methods;
        myMethodVariables = methodVariables;
        myMethodDeclarations = methodDeclarations;
    }

    /**
     * Get the map of defined variables to their values
     *
     * @return the map of defined variables to their values
     */
    public ObservableMap<String, Double> getMyVariables() {
        return myVariables;
    }

    /**
     * Get the map of temporary method variables to their values
     *
     * @return the map of temporary method variables to their values
     */
    public Map<String, Double> getMyMethodVariables() {
        return myMethodVariables;
    }

    /**
     * Get the map of methods to their LogoMethods (representation
     * of the actions a method performs)
     *
     * @return the map of methods to their LogoMethods
     */
    public Map<String, LogoMethod> getMyMethods() {
        return myMethods;
    }

    /**
     * Get the map of methods to the number of arguments
     * they take
     *
     * @return the map of methods to the number of arguments they take
     */
    public Map<String, Integer> getMyMethodDeclarations() {
        return myMethodDeclarations;
    }
}