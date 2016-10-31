package BackEndInternalAPI;

import BackEndCommands.*;
import BackEndCommands.ControlOperations.To;
import GUIController.GUIConsole;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;

import java.util.*;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class builds a parse tree of a specified Logo commandType. This is done
 *         by reducing the Logo commandType down into its subparts and then recursively
 *         building a tree where each tree node is a irreducible Logo commandType.
 *         <p>
 *         Dependencies: Command classes, CommandTypeDetector, LogoMethod, Mappings,
 *         ObservableProperties, ParseTreeNode
 */
public class ParseTreeBuilder {

    private static final String ERRORS_PATH = "resources/internal/Errors";
    private static String[] myCommands;
    private static int myCommandIndex;
    private static CommandTypeDetector myDetector;
    private static ObservableProperties myProperties;
    private static Mappings myMappings;
    private static ObservableSet<String> myErrors;
    private static ResourceBundle myThrowables; // contains error messages
    private static boolean definingMethod; // only true for a TO command


    public ParseTreeBuilder(ObservableProperties properties, ObservableMap<String, Double> variables, Map<String, LogoMethod> methods, Map<String, Double> methodVariables, GUIConsole console, SimpleStringProperty languageBinding) {
        myDetector = new CommandTypeDetector(languageBinding.get());
        myMappings = new Mappings(variables, methodVariables, methods);
        myProperties = properties;
        myErrors = FXCollections.observableSet();
        myThrowables = ResourceBundle.getBundle(ERRORS_PATH);
        definingMethod = false;
        myErrors.addListener((SetChangeListener<String>) (change) -> change.getSet().forEach(console::addConsole));
    }

    /**
     * Sets observable properties used for communicating with the GUI
     *
     * @param properties
     */
    public static void setProperties(ObservableProperties properties) {
        myProperties = properties;
    }

    /**
     * Getter for set containing error messages
     *
     * @return the set containing error messages
     */
    public static ObservableSet<String> getErrors() {
        return myErrors;
    }

    /**
     * Calls a Logo method by binding inputted arguments to method variables
     * and returning a ParseTreeNode representing the actions defined by the method
     *
     * @param method is the LogoMethod object containing action and variable information
     *               about the defined Logo method
     * @return a ParseTreeNode representing the method, ready for execution
     */
    private ParseTreeNode buildMethodTree(LogoMethod method) {
        myCommandIndex++;
        for (int i = 0; i < method.numArguments(); i++) { // bind arguments to method variables
            if (argumentError()) {
                return null;
            }
            myMappings.getMyMethodVariables().put(method.getArgument(i), Double.parseDouble(myCommands[myCommandIndex])); // method variables placed in temporary map
            myCommandIndex++;
        }
        return method.getMethod();
    }

    /**
     * Determines if the current node is calling a method
     *
     * @param node is the node representing the current command
     * @return a ParseTreeNode representing the called method, if applicable
     */
    private ParseTreeNode checkIfCallingMethod(ParseTreeNode node) {
        if (definingMethod) { // node is part of a method definition
            return node;
        }
        if (myMappings.getMyMethods().get(node.getRawCommand()) != null) { // calling a method
            // get the method associated with the method name specified by the current command
            return buildMethodTree(myMappings.getMyMethods().get(node.getRawCommand()));
        }
        myErrors.add(myThrowables.getString("CommandError")); // not calling or defining method, error
        return null;
    }

    /**
     * Determine if the command object type of a node is unknown
     * (i.e. not any conventional Logo command). This means the node
     * represents either an incorrect command or a method call
     *
     * @param node is the node with the command object to test
     * @return true if the node represents an unknown command, false
     * otherwise
     */
    private boolean unknownCommand(ParseTreeNode node) {
        return node.getCommandObj().getClass() == Unknown.class;
    }

    /**
     * Builds the subtrees for commands within a list
     *
     * @param listNode is the node representing the beginning of the list
     * @return listNode holding all of its command subtrees as children
     */
    private ParseTreeNode buildList(ParseTreeNode listNode) {
        while (!myCommands[myCommandIndex + 1].equals("]")) {
            myCommandIndex++;
            listNode.addChild(buildParseTree()); // create subtrees for each element in the list
            if (myCommandIndex >= myCommands.length - 1) { // list end never given
                myErrors.add(myThrowables.getString("ListError"));
                return null;
            }

        }
        myCommandIndex++; // ensure that anything after the list is added to the tree
        return listNode;
    }

    /**
     * Determines if the current command spcecifies the beginning of a list
     *
     * @param node is the node representing the current command
     * @return true if the node represents the beginning of a list, false otherwise
     */
    private boolean listStart(ParseTreeNode node) {
        return node.getCommandObj().getClass() == ListStart.class;
    }

    /**
     * Determines if a TO command is being executed, i.e. a new method is being
     * created, and if so sets definingMethod to true so that the method's name
     * is not called, but rather defined
     *
     * @param node is the node representing the current command
     */
    private void checkifDefiningMethod(ParseTreeNode node) {
        if (node.getCommandObj().getClass() == To.class) {
            definingMethod = true;
        }
    }

    /**
     * Create a new ParseTreeNode and initialize the raw command and command object.
     * Observable properties and variable mappings are also initialized.
     *
     * @param currCommand is a String representing the single command with which
     *                    to make the node
     * @return the newly initialized ParseTreeNode
     */
    private ParseTreeNode initParseTreeNode(String currCommand) {
        ParseTreeNode newNode = new ParseTreeNode();
        newNode.setRawCommand(currCommand);
        newNode.setCommandObj(myDetector.getCommandObj(currCommand));
        newNode.getCommandObj().setProperties(myProperties); // observable properties
        newNode.getCommandObj().setProperties(myMappings); // information about variables
        return newNode;
    }

    /**
     * Determines if the specified command has a sufficient number
     * of arguments to execute
     *
     * @return true if the command has sufficient arguments, false otherwise
     */
    private boolean argumentError() {
        if (myCommandIndex > myCommands.length - 1) {
            myErrors.add(myThrowables.getString("ArgumentError"));
            return true;
        }
        return false;
    }

    /**
     * Recursively builds a parse tree representing a Logo command by
     * iterating through a String array of commands
     *
     * @return a ParseTreeNode corresponding to the root of a parse tree
     * representing the Logo command
     */
    private ParseTreeNode buildParseTree() {
        if (argumentError()) return null;
        String currCommand = myCommands[myCommandIndex];
        ParseTreeNode newChild = initParseTreeNode(currCommand);
        checkifDefiningMethod(newChild);
        if (listStart(newChild)) return buildList(newChild);
        if (unknownCommand(newChild)) return checkIfCallingMethod(newChild);
        if (newChild.getNumChildren() == 0) return newChild; // base case, no more commands to add to the tree
        for (int i = 0; i < newChild.getCommandObj().numArguments(); i++) { // create all children
            myCommandIndex++;
            newChild.addChild(buildParseTree());
        }
        definingMethod = false; // no longer on a TO command
        return newChild;
    }

    /**
     * Initializes the recursive function buildParseTree which returns the
     * root of the complete parse tree representing the inputted command
     *
     * @param commands is a sanitized String array containing the commands issued from the GUI
     * @return the root of the newly built parse tree
     */
    public ParseTreeNode buildNewParseTree(String[] commands) {
        myCommands = commands;
        myCommandIndex = 0; // will be index of current command
        return buildParseTree();
    }
}