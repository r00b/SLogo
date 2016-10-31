package BackEndExternalAPI;

import BackEndInternalAPI.*;
import GUIController.GUIVariables;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

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
//    private static HashMap<String, Double> myVariables;
    private static ObservableMap<String,Double> myVariables;
    private static HashMap<String, Double> myMethodVariables; // temporary map for method variables
    private static HashMap<String, LogoMethod> myMethods;
    private static Set<String> myErrors; // will hold any found errors

    public CommandParser(ObservableProperties properties, GUIVariables variables) {
        myProperties = properties;
//        myVariables = new HashMap<String, Double>();
        myMethodVariables = new HashMap<String, Double>();
        myMethods = new HashMap<String, LogoMethod>();


        myVariables = FXCollections.observableHashMap();
        myVariables.addListener(new MapChangeListener<String, Double>() {
            @Override
            public void onChanged(Change<? extends String, ? extends Double> change) {
                System.out.println("FUCK");
                variables.setMap(change.getMap());
            }
        });
//
//
//        myVariables.addListener(new ChangeListener<ObservableMap<String, Double>>() {
//            @Override
//            public void changed(ObservableValue<? extends ObservableMap<String, Double>> observable, ObservableMap<String, Double> oldValue, ObservableMap<String, Double> newValue) {
//                System.out.println("FUCK");
//                variables.setMap(newValue);
//            }
//        });



    }


//    // TODO CHANGE THIS SO THAT WE GET VARIABLES VIA OBSERVABLE PROPERTIES OR SOMETHING
//    public HashMap<String, Double> getVariables() {
//
//
//
//        return myVariables;
//    }

    /**
     * Getter for set containing error messages
     *
     * @return the set containing error messages
     */
    public Set<String> getErrors() {
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
     * Executes the cumulative action associated with a Logo command issued
     * from the GUI
     *
     * @param command a string containing the commands issued from the editor
     */
    public double getAction(String command) {
        String[] commands = sanitize(command.trim().split("\\p{Space}"));
        ParseTreeBuilder builder = new ParseTreeBuilder(myProperties, myVariables, myMethods, myMethodVariables);
        ParseTreeNode parseTree = builder.buildNewParseTree(commands);
        myErrors = builder.getErrors();
        if (myErrors.size() != 0) return 0.0;
        double result = parseTree.getCommandObj().executeCommand(parseTree);
        myMethodVariables.clear(); // clear temporary method variables
        return result;
    }
}