package BackEndInternalAPI;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This is an interface for the Command object, which specifies
 *         the actions specific to a Logo commandType.
 */
public interface Command {


    /**
     * Executes a commandType that is respective to the type of
     * Command instance
     *
     * @param args is an ArrayList of Doubles containing the arguments
     *             for the computations
     * @return the result of executing the commandType
     */
    double executeCommand(ParseTreeNode node);

    /**
     * Specifies the number of arguments that a Command takes
     *
     * @return an int that specifies the number of arguments
     */
    int numArguments();
}