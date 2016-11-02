package BackEndInternalAPI;

import BackEndCommands.*;
import BackEndCommands.ControlOperations.To;
import javafx.beans.property.SimpleStringProperty;

import java.util.*;
import java.util.ResourceBundle;

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

    private static final String ERRORS_PATH = "resources/internal/ErrorMessages";

    private static String[] myCommands;
    private static int myCommandIndex;
    private static CommandTypeDetector myDetector;
    private static ObservableComposite myTurtleProperties;
    private static DisplayProperties myDisplayProperties;
    private static Mappings myMappings;
    private static HashSet<String> myErrors; // errors thrown during execution
    private static ResourceBundle myThrowables; // error messages
    private static String myMethodBeingDefined;

    public ParseTreeBuilder() {
        myThrowables = ResourceBundle.getBundle(ERRORS_PATH);
        myMethodBeingDefined = "";
    }

    /**
     * Sets a bound String specifying the language in which commands will
     * be issued
     *
     * @param languageBinding is the SimpleStringProperty containing the
     *                        String specifying the language
     */
    public void setLanguage(SimpleStringProperty languageBinding) {
        myDetector = new CommandTypeDetector(languageBinding.get());
    }

    /**
     * Sets observable properties used for communicating with the GUI
     *
     * @param turtleProperties contains the observable properties associated
     *                         turtle manipulation
     * @param displayProperties contains the observable properties associated
     *                          with GUI display manipulation
     */
    public void setProperties(ObservableComposite turtleProperties, DisplayProperties displayProperties) {
        myTurtleProperties = turtleProperties;
        myDisplayProperties = displayProperties;
    }

    /**
     * Set variable and method mappings used for storing variables and
     * methods
     *
     * @param maps is the Mappings object containing the variables, methods,
     *             temporary method variables, and method declarations
     */
    public void setMappings(Mappings maps) {
        myMappings = maps;
    }

    /**
     * Sets the error set in which to place thrown errors
     *
     * @param errors is the set in which to place thrown errors
     */
    public void setErrorSet(HashSet<String> errors) {
        myErrors = errors;
    }

    /**
     * Get the set containing error messages
     *
     * @return the set containing error messages
     */
    public HashSet<String> getErrors() {
        return myErrors;
    }

    /**
     * Determines if the current node is calling a method and builds the method
     * if so
     *
     * @param node is the node representing the current command
     * @return a ParseTreeNode representing the called method, if applicable
     */
    private ParseTreeNode checkIfCallingMethod(ParseTreeNode node) {
        if (node.getRawCommand().equals(myMethodBeingDefined)) {
            // map method call to number of arguments taken
            int varIndex = myCommandIndex + 2;
            int varCount = 0;
            while (myDetector.getCommandType(myCommands[varIndex]).equals("Variable")) {
                varCount++;
                varIndex++;
            }
            myMappings.getMyMethodDeclarations().put(node.getRawCommand(), varCount);
            return node;
        } else if (myMappings.getMyMethodDeclarations().get(node.getRawCommand()) != null) {
            // calling previously defined method within a new method definition
            int numVariables = myMappings.getMyMethodDeclarations().get(node.getRawCommand());
            while (numVariables != 0) { // get all variables necessary
                myCommandIndex++;
                node.addChild(buildParseTree());
                numVariables--;
            }
            return node;
        } else { // building something that is not a Logo command or method call
            myErrors.add(myThrowables.getString("CommandError"));
            return null;
        }
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
    private boolean methodCall(ParseTreeNode node) {
        return node.getCommandObj().getClass() == MethodCall.class;
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
            if (myCommandIndex >= myCommands.length - 1) { // if expecting more arguments
                myErrors.add(myThrowables.getString("ArgumentError"));
                return null;
            }
        }
        myCommandIndex++; // ensure that anything after the list is added to the tree
        return listNode;
    }

    /**
     * Determines if the current command specifies the beginning of a list
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
            myMethodBeingDefined = myCommands[myCommandIndex + 1];
        }
    }

    /**
     * Create a new ParseTreeNode and initialize the raw command and command object;
     * observable properties and variable mappings are also initialized.
     *
     * @param currCommand is a String representing the single command with which
     *                    to make the node
     * @return the newly initialized ParseTreeNode
     */
    private ParseTreeNode initParseTreeNode(String currCommand) {
        ParseTreeNode newNode = new ParseTreeNode();
        newNode.setRawCommand(currCommand);
        newNode.setCommandObj(myDetector.getCommandObj(currCommand));
        newNode.getCommandObj().setProperties(myTurtleProperties); // observable turtle properties
        newNode.getCommandObj().setProperties(myMappings); // information about variables
        newNode.getCommandObj().setProperties(myDisplayProperties); //observable display properties
        return newNode;
    }

    /**
     * Recursively builds a parse tree representing a Logo command by
     * iterating through a String array of commands
     *
     * @return a ParseTreeNode corresponding to the root of a parse tree
     * representing the Logo command
     */
    private ParseTreeNode buildParseTree() {
        String currCommand = myCommands[myCommandIndex];
        ParseTreeNode newChild = initParseTreeNode(currCommand);
        checkifDefiningMethod(newChild);
        if (listStart(newChild)) return buildList(newChild);
        if (methodCall(newChild)) return checkIfCallingMethod(newChild);
        if (newChild.getNumChildren() == 0) return newChild; // base case, no more commands to add to the tree
        for (int i = 0; i < newChild.getCommandObj().numArguments(); i++) { // create all children
            myCommandIndex++;
            newChild.addChild(buildParseTree());
        }
        myMethodBeingDefined = "";
        return newChild;
    }

    /**
     * Initializes the recursive function buildParseTree which returns the
     * root of the complete parse tree representing the given command
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