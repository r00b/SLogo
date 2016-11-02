package BackEndExternalAPI;

import BackEndInternalAPI.*;
import GUIController.GUIVariables;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;

import java.util.*;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class interprets a Logo command and executes the action(s) associated with it
 *         using the specified arguments. This is done by recursively creating a parse tree
 *         and executing down the nodes of the parse tree, checking for errors along the way.
 *         <p>
 *         Dependencies: ParseTreeBuilder, ObservableProperties, CommandSanitizer
 */
public class CommandParser {

    private static final String ERRORS_PATH = "resources/internal/ErrorMessages";
    private static ObservableComposite myTurtleProperties;
    private static DisplayProperties myDisplayProperties;
    private static ObservableMap<String, Double> myVariables;
    private static HashMap<String, Double> myMethodVariables; // temporary map for method variables
    private static HashMap<String, LogoMethod> myMethods;
    private static HashMap<String, Integer> myMethodVariableDeclarations;
    private static SimpleStringProperty myLanguageBinding;
    private static HashSet<String> myErrors; // errors thrown during execution
    private static ResourceBundle myThrowables; // error messages


    public CommandParser(SimpleStringProperty languageBinding, ObservableComposite turtleProperties, DisplayProperties displayProperties, GUIVariables variables) {
        myMethodVariables = new HashMap<String, Double>();
        myMethods = new HashMap<String, LogoMethod>();
        myMethodVariableDeclarations = new HashMap<String, Integer>();
        myThrowables = ResourceBundle.getBundle(ERRORS_PATH);
        myLanguageBinding = languageBinding;
        myTurtleProperties = turtleProperties;
        myDisplayProperties = displayProperties;
        myVariables = FXCollections.observableHashMap();
        myVariables.addListener((MapChangeListener<String, Double>) (change) ->
                variables.setMap(change.getMap()));
    }

    /**
     * Gets the set containing error messages generated during previous execution
     *
     * @return the set containing error messages
     */
    public static HashSet<String> getErrors() {
        return myErrors;
    }

    /**
     * Initializes a ParseTreeBuilder by creating it, specifying its language,
     * binding its Turtle properties, setting its variable maps, and passing in
     * a set into which errors are placed
     *
     * @return the newly initialized ParseTreeBuilder
     */
    private ParseTreeBuilder initBuilder() {
        ParseTreeBuilder newBuilder = new ParseTreeBuilder();
        newBuilder.setLanguage(myLanguageBinding);
        newBuilder.setProperties(myTurtleProperties, myDisplayProperties);
        newBuilder.setMappings(new Mappings(myVariables, myMethods, myMethodVariables, myMethodVariableDeclarations));
        newBuilder.setErrorSet(myErrors);
        return newBuilder;
    }

    /**
     * Builds a parse tree with sanitized commands and attempts to execute it;
     * pushes errors back to the GUI if execution was not successful
     *
     * @param commands is a String array representing the sanitized commands
     *                 that will be built into a parse tree and executed
     * @return the result of execution as a double, is successful
     */
    private double buildAndExecuteTree(String[] commands) {
        ParseTreeBuilder builder = initBuilder();
        ParseTreeNode parseTree = builder.buildNewParseTree(commands);
        myErrors.addAll(builder.getErrors());
        if (myErrors.isEmpty()) {
            try { // execute the tree
                return parseTree.getCommandObj().executeCommand(parseTree);
            } catch (IndexOutOfBoundsException e) { // not enough args given for a command
                myErrors.add(myThrowables.getString("ArgumentError"));
            }
            myMethodVariables.clear(); // remove temporary method variables
        }
        return 0.0; // default return value
    }

    /**
     * Executes the cumulative actions associated with given Logo commands
     *
     * @param commands a String array containing the commands issued from the editor
     * @return an ArrayList of doubles containing the results from executing the commands
     */
    public double executeCommands(String[] commands) {
        myErrors = new HashSet<String>(); // clean out old errors
        commands = new CommandSanitizer().sanitize(commands);
        return buildAndExecuteTree(commands);
    }
}