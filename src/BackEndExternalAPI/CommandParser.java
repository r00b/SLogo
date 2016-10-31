package BackEndExternalAPI;

import BackEndInternalAPI.*;

import java.util.HashMap;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class interprets a Logo commandType and executes the action associated with it
 *         using the specified arguments. This is done by recursively creating a parse tree
 *         and executing down the nodes of the parse tree, checking for errors along the way.
 */
public class CommandParser {

    private static ObservableComposite myProperties;
    private static HashMap<String, Double> myVariables;
    private static HashMap<String, Double> myMethodVariables;
    private static HashMap<String, LogoMethod> myMethods;

    public CommandParser(ObservableComposite properties) {
        myProperties = properties;
        myVariables = new HashMap<String, Double>();
        myMethodVariables = new HashMap<String, Double>();
        myMethods = new HashMap<String, LogoMethod>();
    }


    // TODO CHANGE THIS SO THAT WE GET VARIABLES VIA OBSERVABLE PROPERTIES OR SOMETHING
    public HashMap<String, Double> getVariables() {
        return myVariables;
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
        double result = root.getCommandObj().executeCommand(root);
        myMethodVariables.clear(); // clear temporary variables
        return result;
    }
}