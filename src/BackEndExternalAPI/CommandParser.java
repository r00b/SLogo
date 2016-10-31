package BackEndExternalAPI;

import BackEndInternalAPI.*;
import GUIController.GUIConsole;
import GUIController.GUIVariables;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class interprets a Logo command and executes the action(s) associated with it
 *         using the specified arguments. This is done by recursively creating a parse tree
 *         and executing down the nodes of the parse tree, checking for errors along the way.
 *
 *         Dependencies: ParseTreeBuilder, ObservableProperties
 */
public class CommandParser {

    private static ObservableProperties myProperties;
    private static ObservableMap<String,Double> myVariables;
    private static HashMap<String, Double> myMethodVariables; // temporary map for method variables
    private static HashMap<String, LogoMethod> myMethods;
    private static GUIConsole myConsole;
    private static SimpleStringProperty myLanguageBinding;
//    private static ObservableSet<String> myErrors; // will hold any found errors

    public CommandParser(ObservableProperties properties, GUIVariables variables, GUIConsole console) {
        myProperties = properties;
        myVariables = FXCollections.observableHashMap();
        myMethodVariables = new HashMap<String, Double>();
        myMethods = new HashMap<String, LogoMethod>();

        myVariables.addListener((MapChangeListener<String, Double>) (change) ->
                variables.setMap(change.getMap()));

        myConsole = console;
    }

    public void setLanguageBinding(SimpleStringProperty languageBinding) {
        myLanguageBinding = languageBinding;
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
     * Executes the cumulative action associated with a Logo command issued
     * from the GUI
     *
     * @param command a string containing the commands issued from the editor
     */
    public double getAction(String command) {
        String[] commands = sanitize(command.trim().split("\\p{Space}"));
        ParseTreeBuilder builder = new ParseTreeBuilder(myProperties, myVariables, myMethods, myMethodVariables, myConsole, myLanguageBinding);
        ParseTreeNode parseTree = builder.buildNewParseTree(commands);
//        myErrors = builder.getErrors();
//        if (myErrors.size() != 0) return 0.0; TODO FIX
        double result = parseTree.getCommandObj().executeCommand(parseTree);
        myMethodVariables.clear(); // clear temporary method variables
        return result;
    }
}