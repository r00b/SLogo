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
        // 			sum 2 3
        // 			sum
        //		/  \
        //	   2    3
        // start at head; if head is a function, get its children, and execute the function with the children, set value of curr to be resulting value
        // if a child is a function, recursively execute that until it is equivalent to something, then pop back up
        // CHECK IS IF THE VALUE IS NULL

//               make
//              /   \
//          :name   double

        ArrayList<Double> arguments = new ArrayList<Double>();


        if (currNode.getCommandObj().getClass() == Variable.class) { // we're trying to access a variable
            currNode.setValue(variables.get(currNode.getCommandType()));
        }

        if (currNode.getNumChildren() == 0) {
            // i want to execute the commandType objects SUCH THAT the value WILL BE RETURNED
            // for a constant, value = the constant we want to return
            // BUT if its a commandType with no args, value = null; the value we want is located in executeCommand
            // SO we want a way to call execute commandType that will return the constant when we need it to


            arguments.add(currNode.getValue()); // for a variable, value needs to be the string
            double value = currNode.getCommandObj().executeCommand(arguments);
            currNode.setValue(value);
            return value;
        }
        // otherwise, we must have a function, get children values, execute
        // get args, put into arraylist


        if (currNode.getCommandObj().getClass() == MakeVariable.class) { // we're dealing with variables
            Double value = executeTree(currNode.getChild(1));  // get the final value
            String name = currNode.getChild(0).getCommandType();
            variables.put(name, value);
            currNode.setValue(value);
            return value;
        } else {
            for (int i = 0; i < currNode.getNumChildren(); i++) {
                arguments.add(executeTree(currNode.getChild(i)));
            }
        }

        Double result = currNode.getCommandObj().executeCommand(arguments); // we have the result
        currNode.setValue(result); // update value of node
        return result;
    }
}
