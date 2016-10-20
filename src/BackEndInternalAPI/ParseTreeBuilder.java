package BackEndInternalAPI;

import BackEndCommands.Constant;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class builds a parse tree of a specified Logo commandType. This is done
 *         by reducing the Logo commandType down into its subparts and then recursively
 *         building a tree where each tree node is a irreducible Logo commandType.
 */
public class ParseTreeBuilder {

    /**
     * Recursively builds a parse tree using an iterator that points to the
     * current commandType to be added to the tree
     *
     * @param commandIterator is an iterator set to the commandType to be added
     *                        to the tree
     * @return a ParseTreeNode corresponding to the root of a parse tree
     * initialized on the given commandType
     */
    private ParseTreeNode buildParseTree(Iterator<String> commandIterator) {
        String currCommand = commandIterator.next();
        CommandTypeDetector detector = new CommandTypeDetector();
        Command commandObj = detector.getCommandObj(currCommand);
        ParseTreeNode newChild;


        if (commandObj.getClass() == Constant.class) { // this commandType is actually a constant
            newChild = new ParseTreeNode(Double.parseDouble(currCommand), commandObj);
        } else {
            newChild = new ParseTreeNode(null, commandObj); // un-executed function calls have a null value
        }
        newChild.setCommandType(currCommand); // TODO determine if this is actually necessary
        if (newChild.getNumChildren() == 0) { // no more commands to add to the tree
            return newChild;
        }
        for (int i = 0; i < newChild.getCommandObj().numArguments(); i++) {
            newChild.addChild(buildParseTree(commandIterator));
        }
        return newChild;
    }

    /**
     * Initializes an iterator for the String array of commands and calls the recursive
     * function buildParseTree which returns the root of the parse tree
     *
     * @param commands is a String array containing the commands issued from the GUI
     * @return the root of the newly built ParseTree
     */
    public ParseTreeNode initParseTree(String[] commands) {
        Iterator<String> commandIterator = Arrays.asList(commands).iterator();
        ParseTreeNode parseTreeRoot = buildParseTree(commandIterator);
        return parseTreeRoot;
    }
}
