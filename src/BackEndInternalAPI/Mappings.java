package BackEndInternalAPI;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableMap;

import java.util.Map;

/**
 * Created by Robert on 10/27/16.
 */
public class Mappings {

    private ObservableMap<String,Double> myVariables;
    private Map<String, Double> myMethodVariables;
    private Map<String, LogoMethod> myMethods;

    public Mappings(ObservableMap<String,Double> variables, Map<String, Double> methodVariables,  Map<String, LogoMethod> methods) {
        myVariables = variables;
        myMethodVariables = methodVariables;
        myMethods = methods;
    }

    public ObservableMap<String,Double> getMyVariables() {
        return myVariables;
    }

    public Map<String, Double> getMyMethodVariables() {
        return myMethodVariables;
    }

    public Map<String, LogoMethod> getMyMethods() {
        return myMethods;
    }


}
