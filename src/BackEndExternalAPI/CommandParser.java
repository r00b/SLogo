package BackEndExternalAPI;

import BackEndInternalAPI.*;
//import com.sun.tools.javac.code.Attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class interprets a Logo commandType and executes the action associated with it
 *         using the specified arguments. This is done by recursively creating a parse tree
 *         and executing down the nodes of the parse tree, checking for errors along the way.
 */
public class CommandParser {

    private static ObservableProperties myProperties;
    private static HashMap<String, Double> myVariables;
    private static HashMap<String, Double> myMethodVariables;
    private static HashMap<String, LogoMethod> myMethods;
    private static Set<String> myErrors;

    public CommandParser(ObservableProperties properties) {
        myProperties = properties;
        myVariables = new HashMap<String, Double>();
        myMethodVariables = new HashMap<String, Double>();
        myMethods = new HashMap<String, LogoMethod>();
    }


    // TODO CHANGE THIS SO THAT WE GET VARIABLES VIA OBSERVABLE PROPERTIES OR SOMETHING
    public HashMap<String, Double> getVariables() {
        return myVariables;
    }

    public Set<String> getErrors() {
        return myErrors;
    }

    /**
     * Executes the cumulative action associated with a Logo commandType issued
     * from the editor
     *
     * @param command a string containing the commandType issued from the editor
     */
    public double getAction(String command) {
        String[] commands = command.trim().split("\\p{Space}");
        ParseTreeBuilder builder = new ParseTreeBuilder(myVariables, myMethodVariables, myMethods, myProperties);
        ParseTreeNode root = builder.initParseTree(commands);
        myErrors = builder.getErrors();
        if (myErrors.size() != 0) {
            return 0.0;
        }
        double result = root.getCommandObj().executeCommand(root);
        myMethodVariables.clear(); // clear temporary variables
        return result;
    }
}