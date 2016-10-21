package BackEndInternalAPI;

import BackEndCommands.ControlOperations.MakeVariable;
import BackEndCommands.ControlOperations.Variable;
import BackEndExternalAPI.CommandParser;

import java.util.ArrayList;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class executes a Logo commandType parse tree via the
 *         executeTree() method.
 */
public class ParseTreeExecutor extends CommandParser {

    /**
     * Recursively executes Logo commands by traversing a parse tree and
     * executing each action associated with each node using the child
     * nodes as arguments.
     *
     * @param currNode is the tree node with the action to be executed
     * @return the result of the action as a Double
     */
    public static double executeTree(ParseTreeNode currNode) {
        ArrayList<Double> arguments = new ArrayList<Double>();
        if (currNode.getNumChildren() == 0) { // base case, execute the command
            arguments.add(currNode.getValue());
            double value = currNode.getCommandObj().executeCommand(arguments);
            currNode.setValue(value);
            return value;
        }




        if (currNode.getCommandObj().getClass() == MakeVariable.class) { // making a variable
            Double value = executeTree(currNode.getChild(1));  // get the final value
            String name = currNode.getChild(0).getCommand();
            variables.put(name, value);
            currNode.setValue(value);
            return value;
        } else { // executing a normal command
            for (int i = 0; i < currNode.getNumChildren(); i++) {
                arguments.add(executeTree(currNode.getChild(i)));
            }
        }

        Double result = currNode.getCommandObj().executeCommand(arguments); // we have the result
        currNode.setValue(result); // update value of node
        return result;
    }
}
