package BackEndInterpreter;

import java.util.ArrayList;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class stores information about a node in the parse tree. Specifically,
 *         this class stores the raw command given via the GUI, the respective Command
 *         object, children, and number of children associated with a node representing
 *         a Logo command.
 */
public class ParseTreeNode {

    private String rawCommand; // actual command typed by the user
    private Command commandObj;
    private ArrayList<ParseTreeNode> children;
    private int numChildren;


    /**
     * Initialize the parse tree node
     */
    public ParseTreeNode() {
        children = new ArrayList<ParseTreeNode>();
    }

    /**
     * Executes the command associated with the node
     *
     * @param node is the current node, it is passed through so that
     *             there is access to the node during command execution
     * @return a double representing the result of executing the command
     */
    public double executeCommand(ParseTreeNode node) {
        return commandObj.executeCommand(node);
    }

    /**
     * Returns the raw command string associated with the node
     *
     * @return the raw command as a String
     */
    public String getRawCommand() {
        return rawCommand;
    }

    /**
     * Sets the raw command string associated with the node
     *
     * @param newCommand is the new rawCommand
     */
    public void setRawCommand(String newCommand) {
        rawCommand = newCommand;
    }

    /**
     * Returns the command object associated with the node
     *
     * @return the command object as a Command instance
     */
    public Command getCommandObj() {
        return commandObj;
    }

    /**
     * Sets the command object associated with the node and gets
     * the number of children that the node will have
     *
     * @param newCommandObj is the new Command instance object
     */
    public void setCommandObj(Command newCommandObj) {
        commandObj = newCommandObj;
        numChildren = commandObj.numArguments();
    }

    /**
     * Adds a ParseTreeNode to the list of children
     *
     * @param newChild is the new node to become a child
     */
    public void addChild(ParseTreeNode newChild) {
        children.add(newChild);
    }

    /**
     * Get the child at the specified index
     *
     * @param index the index of the child node to get
     * @return the child node at the index
     */
    public ParseTreeNode getChild(int index) {
        return children.get(index);
    }

    /**
     * Get the number of children associated with this node
     *
     * @return the number of children
     */
    public int getNumChildren() {
        return numChildren;
    }

    /**
     * Returns the node's subtrees
     *
     * @return the a list of the node's subtrees
     */
    public ArrayList<ParseTreeNode> getChildren() {
        return children;
    }
}