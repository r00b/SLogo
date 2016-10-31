package BackEndInternalAPI;

import java.util.ArrayList;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class holds information about a user-set Logo function. This object
 *         can be used to call a Logo method after it has been defined.
 */
public class LogoMethod {

    private ParseTreeNode method;
    private ArrayList<String> arguments;

    public LogoMethod() {
        arguments = new ArrayList<String>();
    }

    /**
     * Specify String argument variables
     *
     * @param argument is the name of the variable to include in the method
     *                 definition
     */
    public void addArgument(String argument) {
        arguments.add(argument);
    }

    /**
     * Gets a specified argument variable
     *
     * @param index specifies the list index of the argument to get
     * @return a String representing the name of the variable
     */
    public String getArgument(int index) {
        return arguments.get(index);
    }

    /**
     * Get the number of arguments for a defined method
     *
     * @return the number of arguments
     */
    public int numArguments() {
        return arguments.size();
    }

    /**
     * Store a Logo method by storing the root of a parse tree
     * defining the method
     *
     * @param newMethod is the root of the parse tree defining the method
     */
    public void setMethod(ParseTreeNode newMethod) {
        method = newMethod;
    }

    /**
     * Gets a store Logo method
     *
     * @return a ParseTreeNode root that represents the stored method
     */
    public ParseTreeNode getMethod() {
        return method;
    }
}