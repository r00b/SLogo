package BackEndInterpreter;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class encapsulates functionality for calling a method in Logo.
 *         Dependencies: CommandTypeDetector, Mappings, ParseTreeNode
 */
public class MethodCaller extends ParseTreeBuilder {

    private static final String ERRORS_PATH = "resources/internal/ErrorMessages";
    private static String[] myCommands;
    private static String myMethodBeingDefined;

    MethodCaller(String[] commands) {
        myCommands = commands;
        myMethodBeingDefined = "";
    }

    /**
     * Sets the current method being defined
     *
     * @param methodName is the name of the method currently being defined
     */
    public void setMethodBeingDefined(String methodName) {
        myMethodBeingDefined = methodName;
    }

    /**
     * Resets the method currently being defined to nothing (i.e. "")
     */
    public void clearMethodBeingDefined() {
        myMethodBeingDefined = "";
    }

    /**
     * Determines if the current node is calling a method and builds a tree
     * representing the method if so
     *
     * @param node is the node representing the current command
     * @return a ParseTreeNode representing the called method, if applicable
     */
    public ParseTreeNode callMethod(ParseTreeNode node, CommandTypeDetector detector, Mappings mappings) {
        ResourceBundle throwables = ResourceBundle.getBundle(ERRORS_PATH);
        if (node.getRawCommand().equals(myMethodBeingDefined)) {
            // map method call to number of arguments taken
            int varIndex = getMyCommandIndex() + 2;
            int varCount = 0;
            // get all the variable definitions for the method
            while (detector.getCommandType(myCommands[varIndex]).equals("Variable")) {
                varCount++;
                varIndex++;
            }
            // store all the variable definitions for the method
            mappings.getMyMethodDeclarations().put(node.getRawCommand(), varCount);
            setMappings(mappings);
            return node;
        } else if (mappings.getMyMethodDeclarations().get(node.getRawCommand()) != null) {
            // calling previously defined method within a new method definition
            int numVariables = mappings.getMyMethodDeclarations().get(node.getRawCommand());
            while (numVariables != 0) { // get all variables necessary for the method
                incrMyCommandIndex();
                node.addChild(buildParseTree());
                numVariables--;
            }
            return node;
        } else { // building something that is not a Logo command or method call
            getErrorSet().add(throwables.getString("CommandError"));
            return null;
        }
    }
}