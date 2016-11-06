package BackEndInterpreter;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         This class encapsulates functionality for building a group in Logo.
 *         Dependencies: ParseTreeNode
 */
public class GroupBuilder extends ParseTreeBuilder {

    private static final String ERRORS_PATH = "resources/internal/ErrorMessages";
    private static String[] myCommands;

    GroupBuilder(String[] commands) {
        myCommands = commands;
    }

    /**
     * Builds the subtrees for commands within a group
     *
     * @param groupNode is the node representing the beginning of the group
     * @return groupNode holding all of its command subtrees as children
     */
    public ParseTreeNode buildGroup(ParseTreeNode groupNode) {
        ResourceBundle throwables = ResourceBundle.getBundle(ERRORS_PATH);
        incrMyCommandIndex(); // start at command
        String command = myCommands[getMyCommandIndex()];
        while (!myCommands[getMyCommandIndex() + 1].equals(")")) {
            ParseTreeNode commandNode = initParseTreeNode(command);
            int numArguments = commandNode.getCommandObj().numArguments();
            for (int i = 0; i < numArguments; i++) { // get all the args for the command
                incrMyCommandIndex();
                try {
                    commandNode.addChild(buildParseTree());
                } catch (ArrayIndexOutOfBoundsException e) {
                    // not enough arguments given for every call in the group
                    getErrorSet().add(throwables.getString("ArgumentError"));
                    return null;
                }
            }
            groupNode.addChild(commandNode); // create subtrees for each element in the list
        }
        incrMyCommandIndex(); // ensure that anything after the group is added to the tree
        return groupNode;
    }
}