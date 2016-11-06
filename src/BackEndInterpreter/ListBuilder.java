package BackEndInterpreter;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         This class encapsulates functionality for building a list in Logo.
 *         Dependencies: ParseTreeNode
 */
public class ListBuilder extends ParseTreeBuilder {

    private static final String ERRORS_PATH = "resources/internal/ErrorMessages";
    private static String[] myCommands;

    ListBuilder(String[] commands) {
        myCommands = commands;
    }

    /**
     * Builds the subtrees for commands within a list
     *
     * @param listNode is the node representing the beginning of the list
     * @return listNode holding all of its command subtrees as children
     */
    public ParseTreeNode buildList(ParseTreeNode listNode) {
        ResourceBundle throwables = ResourceBundle.getBundle(ERRORS_PATH);
        while (!myCommands[getMyCommandIndex() + 1].equals("]")) {
            incrMyCommandIndex();
            listNode.addChild(buildParseTree()); // create subtrees for each element in the list
            if (getMyCommandIndex() >= myCommands.length - 1) { // if expecting more arguments
                getErrorSet().add(throwables.getString("ArgumentError"));
                return null;
            }
        }
        incrMyCommandIndex(); // ensure that anything after the list is added to the tree
        return listNode;
    }
}
