package BackEndInternalAPI;

import java.util.ArrayList;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class stores information about a Logo commandType parse tree node.
 */
public class ParseTreeNode {

    private Double value;
    private String command;
    private String commandType;
    private Command commandObj;
    public ArrayList<ParseTreeNode> children;
    private int numChildren;

    /**
     * Initialize the parse tree node
     */
    ParseTreeNode() {
        children = new ArrayList<ParseTreeNode>();
    }

    public double executeCommand(ArrayList<Double> args) {
        return commandObj.executeCommand(args);
    }

    /**
     * Returns the value of the node
     *
     * @return the value as a Double
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of the node
     *
     * @param newValue is the new value
     */
    public void setValue(Double newValue) {
        value = newValue;
    }

    /**
     * Returns the command associated with the node
     *
     * @return the command as a String
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command associated with the node
     *
     * @param newCommand is the new command
     */
    public void setCommand(String newCommand) {
        command = newCommand;
    }

    /**
     * Returns the command type of the node
     *
     * @return the command type as a String
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Sets the command type of the node
     *
     * @param newCommandType is the new command type
     */
    public void setCommandType(String newCommandType) {
        commandType = newCommandType;
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
     * Sets the command object associated with the node
     *
     * @param newCommandObj is the new Command instance object
     */
    public void setCommandObj(Command newCommandObj) {
        commandObj = newCommandObj;
        numChildren = commandObj.numArguments();
    }

    /**
     * Adds a parser tree node to the list of children
     *
     * @param newChild is the new node to add
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
     * Determine if the node has any children
     *
     * @return true if there are no children, false otherwise
     */
    public boolean hasChild() {
        return !children.isEmpty();
    }

    /**
     * Determine if there are no children
     *
     * @return true if there are no children, false otherwise
     */
    public boolean hasNoChildren() {
        return children.isEmpty();
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
     * Returns the node's subtree
     *
     * @return the root of the subtree
     */
    public ArrayList<ParseTreeNode> getChildren() {
        return children;
    }
}
