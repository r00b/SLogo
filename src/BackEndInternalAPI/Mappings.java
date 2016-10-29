package BackEndInternalAPI;

import java.util.Map;

/**
 * Created by Robert on 10/27/16.
 */
public class Mappings {

    private Map<String, Double> myVariables;
    private Map<String, Double> myMethodVariables;
    private Map<String, LogoMethod> myMethods;

    public Mappings(Map<String, Double> variables, Map<String, Double> methodVariables,  Map<String, LogoMethod> methods) {
        myVariables = variables;
        myMethodVariables = methodVariables;
        myMethods = methods;
    }

    public Map<String, Double> getMyVariables() {
        return myVariables;
    }

    public Map<String, Double> getMyMethodVariables() {
        return myMethodVariables;
    }

    public Map<String, LogoMethod> getMyMethods() {
        return myMethods;
    }


}
