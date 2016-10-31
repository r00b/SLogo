package BackEndExternalAPI;

import BackEndInternalAPI.*;
import GUIController.GUIVariables;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class interprets a Logo command and executes the action(s) associated with it
 *         using the specified arguments. This is done by recursively creating a parse tree
 *         and executing down the nodes of the parse tree, checking for errors along the way.
 *         <p>
 *         Dependencies: ParseTreeBuilder, ObservableProperties
 */
public class CommandParser {

    private static ObservableProperties myTurtleProperties;
    private static ObservableMap<String, Double> myVariables;
    private static HashMap<String, Double> myMethodVariables; // temporary map for method variables
    private static HashMap<String, LogoMethod> myMethods;
    private static SimpleStringProperty myLanguageBinding;
    private static HashSet<String> myErrors;

    public CommandParser() {
        myMethodVariables = new HashMap<String, Double>();
        myMethods = new HashMap<String, LogoMethod>();
    }

    /**
     * Initializes the String binding that for language detection
     *
     * @param languageBinding the SimpleStringProperty representing the bound String
     */
    public void initLanguageBinding(SimpleStringProperty languageBinding) {
        myLanguageBinding = languageBinding;
    }

    /**
     * Initializes the observable properties for Turtle manipulation
     *
     * @param properties the ObservableProperties object representing bound Turtle properties
     */
    public void initTurtlePropertiesBinding(ObservableProperties properties) {
        myTurtleProperties = properties;
    }

    /**
     * Initializes the global variable map
     *
     * @param variables the GUIVariables object that interfaces variables with the GUI
     */
    public void initVariablesBinding(GUIVariables variables) {
        myVariables = FXCollections.observableHashMap();
        myVariables.addListener((MapChangeListener<String, Double>) (change) ->
                variables.setMap(change.getMap()));
    }

    /**
     * Getter for set containing error messages
     *
     * @return the set containing error messages
     */
    public static HashSet<String> getErrors() {
        return myErrors;
    }

    /**
     * Removes empty commands from an inputted list of commands
     *
     * @param array is the array to sanitized
     * @return a sanitized array with empty commands removed
     */
    private String[] sanitize(String[] array) {
        ArrayList<String> toSanitize = new ArrayList<String>();
        for (String command : array) {
            if (!command.equals("")) toSanitize.add(command);
        }
        String[] sanitizedList = new String[toSanitize.size()];
        toSanitize.toArray(sanitizedList);
        return sanitizedList;
    }

    /**
     * Initializes a ParseTreeBuilder by creating it, specifying its language,
     * binding its Turtle properties, setting its variable maps, and passing in
     * a set in which errors are placed
     *
     * @return the newly initialized ParseTreeBuilder
     */
    private ParseTreeBuilder initBuilder() {
        ParseTreeBuilder newBuilder = new ParseTreeBuilder();
        newBuilder.setLanguage(myLanguageBinding);
        newBuilder.setTurtleProperties(myTurtleProperties);
        newBuilder.setMappings(new Mappings(myVariables, myMethods, myMethodVariables));
        newBuilder.setErrorSet(myErrors);
        return newBuilder;
    }

    /**
     * Executes the cumulative action associated with a Logo command issued
     * from the GUI
     *
     * @param command a string containing the commands issued from the editor
     */
    public double getAction(String command) {
        myErrors = new HashSet<String>();
        String[] commands = sanitize(command.trim().split("\\p{Space}"));
        ParseTreeBuilder builder = initBuilder();
        ParseTreeNode parseTree = builder.buildNewParseTree(commands);
        if (builder.getErrors().size() != 0) return 0.0;
        double result = parseTree.getCommandObj().executeCommand(parseTree);
        myMethodVariables.clear(); // clear temporary method variables
        return result;
    }
}