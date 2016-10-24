package BackEndInternalAPI;

import BackEndCommands.Constant;
import BackEndCommands.ListStart;
import BackEndCommands.NoType;

import static BackEndExternalAPI.CommandParser.myMethodVariables;
import static BackEndExternalAPI.CommandParser.myMethods;

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
     *
     * @return a ParseTreeNode holding the defined method
     */
    private ParseTreeNode buildMethodTree(LogoMethod method) {
        myCommandIndex++;
        for (int i = 0; i < method.numArguments(); i ++) {
            myMethodVariables.put(method.getArgument(i),Double.parseDouble(myCommands[myCommandIndex]));
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
        if (isNoType(newChild) && myMethods.get(newChild.getCommand()) != null) { // calling a method
            return buildMethodTree(myMethods.get(currCommand));
        }
        if (newChild.getCommandObj().getClass() == ListStart.class) { // building a list
            return buildList(newChild);
        }
        if (isConstant(newChild)) {
            newChild.setValue(Double.parseDouble(currCommand));
        }
        if (isNoType(newChild)) { // part of a TO call
            newChild.setValue(0.0);
        }
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
