package BackEndInternalAPI;

import BackEndCommands.*;
import BackEndCommands.ControlOperations.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class builds a parse tree of a specified Logo commandType. This is done
 *         by reducing the Logo commandType down into its subparts and then recursively
 *         building a tree where each tree node is a irreducible Logo commandType.
 */
public class ParseTreeBuilder {

    CommandTypeDetector myDetector;
    private String[] myCommands;
    private int myCommandIndex;

    private static String COMMANDTYPES_PATH = "resources/internal/CommandTypes";
    private static ResourceBundle myCommandTypes = ResourceBundle.getBundle(COMMANDTYPES_PATH);
    private static ObservableProperties myProperties;


//    private Map<String, Double> myVariables;
//    private Map<String, LogoMethod> myMethods;
//    private Map<String, Double> myMethodVariables;

    private Mappings myMappings;


    public ParseTreeBuilder(Map<String, Double> variables, Map<String, Double> methodVariables, Map<String, LogoMethod> methods) {
        myMappings = new Mappings(variables,methodVariables,methods);

    }

    public static void setProperties(ObservableProperties properties) {
        myProperties = properties;
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
        newNode.setCommand(currCommand);
        newNode.setCommandType(myDetector.getCommandType(currCommand));
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
            myMappings.getMyMethodVariables().put(method.getArgument(i), Double.parseDouble(myCommands[myCommandIndex]));
            myCommandIndex++;
        }
        return method.getMethod();
    }

    /**
     * Determine if the command object type of a node is Constant
     * (i.e. represents a double)
     *
     * @param node is the node with the command object to test
     * @return true if the node represents a Constant, false otherwise
     */
    private boolean isConstant(ParseTreeNode node) {
        return node.getCommandObj().getClass() == Constant.class;
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

    private void addTurtleProperties(ParseTreeNode currNode) {
        ((TurtleCommand) currNode.getCommandObj()).setProperties(myProperties);
    }

    /**
     * Recursively builds a parse tree by iterating through a String array
     * of issued commands
     *
     * @return a ParseTreeNode corresponding to the root of a parse tree
     * initialized on the given commandType
     */
    private ParseTreeNode buildParseTree() {
        String currCommand = myCommands[myCommandIndex];
        ParseTreeNode newChild = initParseTreeNode(currCommand);

//        if (myCommandTypes.getString(newChild.getCommandType()).equals("Turtle")) {
//            addTurtleProperties(newChild);
//        }

//        if (myCommandTypes.getString(newChild.getCommandType()).equals("Control")) {
//            ((ControlCommand) newChild.getCommandObj()).setInfo(myVariables,myMethodVariables,myMethods);
//        }

        if (isNoType(newChild) && myMappings.getMyMethods().get(newChild.getCommand()) != null) { // calling a method
            return buildMethodTree(myMappings.getMyMethods().get(currCommand));
        }
        if (newChild.getCommandObj().getClass() == ListStart.class) { // building a list
            return buildList(newChild);
        }
//        if (isConstant(newChild) || newChild.getCommandObj().getClass() == Variable.class) {
//            ParseTreeNode constNode = new ParseTreeNode();
//            constNode.setValue(Double.parseDouble(currCommand));
//            newChild.addChild(constNode);
//            return newChild;
//        }
//        if (isNoType(newChild)) { // part of a TO call
//            newChild.setValue(0.0);
//        }
        if (newChild.getNumChildren() == 0) { // base case, no more commands to add to the tree
            return newChild;
        }
        for (int i = 0; i < newChild.getCommandObj().numArguments(); i++) { // create all children
            myCommandIndex++;
            newChild.addChild(buildParseTree());
        }
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
        myCommandIndex = 0; // indexes current command
        return buildParseTree();
    }
}
