package BackEndInternalAPI;

import BackEndCommands.*;
import BackEndCommands.ControlOperations.To;
//import org.apache.velocity.runtime.directive.Parse;

import java.util.*;


/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class builds a parse tree of a specified Logo commandType. This is done
 *         by reducing the Logo commandType down into its subparts and then recursively
 *         building a tree where each tree node is a irreducible Logo commandType.
 */
public class ParseTreeBuilder {

    private static final String ERRORS_PATH = "resources/internal/Errors";
    private static CommandTypeDetector myDetector;
    private static String[] myCommands;
    private static int myCommandIndex;
    private static ObservableProperties myProperties;
    private static Mappings myMappings;
    private static Set<String> myErrors;
    private static ResourceBundle myThrowables;
    private static boolean definingMethod;


    public ParseTreeBuilder(Map<String, Double> variables, Map<String, Double> methodVariables, Map<String, LogoMethod> methods, ObservableProperties properties) {
        myMappings = new Mappings(variables, methodVariables, methods);
        myProperties = properties;
        myErrors = new HashSet<String>();
        myThrowables = ResourceBundle.getBundle(ERRORS_PATH);
        definingMethod = false;
    }

    public static void setProperties(ObservableProperties properties) {
        myProperties = properties;
    }

    public static Set<String> getErrors() {
        return myErrors;
    }

    private boolean throwError(String exception) {
        if (myCommandIndex > myCommands.length - 1) {
            myErrors.add(exception);
            return true;
        }
        return false;
    }

    /**
     * Create a new ParseTreeNode and initialize the command, command type,
     * and command object for the node
     *
     * @param currCommand is a String representing the command with which to make the node
     * @return the newly initialized ParseTreeNode
     */
    private ParseTreeNode initParseTreeNode(String currCommand) {
        ParseTreeNode newNode = new ParseTreeNode();
        newNode.setRawCommand(currCommand);
        newNode.setCommandObj(myDetector.getCommandObj(currCommand)); // sets the associated Command
        newNode.getCommandObj().setProperties(myProperties);
        newNode.getCommandObj().setProperties(myMappings);
        return newNode;
    }

    /**
     * Builds the subtrees for commands within a list
     *
     * @param listNode is the node containing the beginning of the list
     * @return listNode holding all of its subtrees as children
     */
    private ParseTreeNode buildList(ParseTreeNode listNode) {
        while (!myCommands[myCommandIndex + 1].equals("]")) {
            myCommandIndex++;
            listNode.addChild(buildParseTree()); // create subtrees for each element in the list
            if (myCommandIndex >= myCommands.length - 1) {
                myErrors.add(myThrowables.getString("ListError"));
                return null;
            }

        }
        myCommandIndex++; // increment so we can execute anything after the list
        return listNode;
    }

    /**
     * Calls a user-set Logo method by binding inputted arguments to variables
     * and returning a ParseTreeNode containing the function to call
     *
     * @param method is the LogoMethod containing information about the
     *               previously defined Logo function
     * @return a ParseTreeNode holding the defined method
     */
    private ParseTreeNode buildMethodTree(LogoMethod method) {
        myCommandIndex++;
        for (int i = 0; i < method.numArguments(); i++) {
            if (throwError(myThrowables.getString("ArgumentError"))) {
                return null;
            }
            myMappings.getMyMethodVariables().put(method.getArgument(i), Double.parseDouble(myCommands[myCommandIndex]));
            myCommandIndex++;
        }
        return method.getMethod();
    }

    /**
     * Determine if the command object type of a node is NoType
     * (i.e. not any conventional Logo command)
     *
     * @param node is the node with the command object to test
     * @return true if the node represents a NoType, false otherwise
     */
    private boolean isNoType(ParseTreeNode node) {
        return node.getCommandObj().getClass() == NoType.class;
    }

    private ParseTreeNode checkForMethod(ParseTreeNode node) {
        if (definingMethod) {
            return node;
        }
        if (myMappings.getMyMethods().get(node.getRawCommand()) != null) { // calling a method
            return buildMethodTree(myMappings.getMyMethods().get(node.getRawCommand()));
        }
        myErrors.add(myThrowables.getString("CommandError")); // not calling or defining method so it must be error
        return null;
    }

    /**
     * Recursively builds a parse tree by iterating through a String array
     * of issued commands
     *
     * @return a ParseTreeNode corresponding to the root of a parse tree
     * initialized on the given commandType
     */
    private ParseTreeNode buildParseTree() {
        if (throwError(myThrowables.getString("ArgumentError"))) {
            return null;
        }
        String currCommand = myCommands[myCommandIndex];
        ParseTreeNode newChild = initParseTreeNode(currCommand);

        if (newChild.getCommandObj().getClass() == To.class) {
            definingMethod = true;
        }

        if (isNoType(newChild)) {
            return checkForMethod(newChild);
        }

        if (newChild.getCommandObj().getClass() == ListStart.class) { // building a list
            return buildList(newChild);
        }

        if (newChild.getNumChildren() == 0) { // base case, no more commands to add to the tree
            return newChild;
        }
        for (int i = 0; i < newChild.getCommandObj().numArguments(); i++) { // create all children
            myCommandIndex++;
            newChild.addChild(buildParseTree());
        }
        definingMethod = false;
        return newChild;
    }

    /**
     * Initializes the recursive function buildParseTree which returns the
     * root of the parse tree representing the inputted command
     *
     * @param commands is a String array containing the commands issued from the GUI
     * @return the root of the newly built parse tree
     */
    public ParseTreeNode initParseTree(String[] commands) {
        myDetector = new CommandTypeDetector();
        myCommands = commands;
        myCommandIndex = 0; // index of current command
        return buildParseTree();
    }
}
