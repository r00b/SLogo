package BackEndInternalAPI;

import BackEndCommands.ControlCommand;
import BackEndCommands.ControlOperations.If;
import BackEndCommands.ControlOperations.MakeVariable;
import BackEndCommands.ControlOperations.Variable;
import BackEndExternalAPI.CommandParser;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class executes a Logo commandType parse tree via the
 *         executeTree() method.
 */
public class ParseTreeExecutor extends CommandParser {

    public static double getVariable(String variable) {
        return variables.get(variable);
    }

    public static void setVariable(String variable, double value) {
        variables.put(variable,value);
    }

    /**
     * Recursively executes Logo commands by traversing a parse tree and
     * executing each action associated with each node using the child
     * nodes as arguments.
     *
     * @param currNode is the tree node with the action to be executed
     * @return the result of the action as a Double
     */
    public static double executeTree(ParseTreeNode currNode) {

        ResourceBundle commandTypes = ResourceBundle.getBundle("resources/internal/CommandTypes");

        ArrayList<Double> arguments = new ArrayList<Double>();


        if (currNode.getCommandObj().getClass() == Variable.class) { // trying to access a variable
            Double variableVal = variables.get(currNode.getCommand());
            if (variableVal == null) {
                currNode.setValue(0.0);
            } else {
                currNode.setValue(variableVal);
            }
            return variableVal;
        }

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
        } else if (commandTypes.getString(currNode.getCommandType()).equals("Control")) {

            ArrayList<ParseTreeNode> executables = new ArrayList<ParseTreeNode>();

            for (int i = 0; i < currNode.getNumChildren(); i++) {
                executables.add(currNode.getChild(i));
            }
            ((ControlCommand) currNode.getCommandObj()).setExecutables(executables);
            double condition = executeTree(currNode.getChild(0));
            arguments.add(condition);
            double finalVal = currNode.getCommandObj().executeCommand(arguments);
            currNode.setValue(finalVal);
            return finalVal;

        } else {// executing a normal command

            for (ParseTreeNode child : currNode.children) {
                arguments.add(executeTree(child));

            }
        }
        Double result = currNode.getCommandObj().executeCommand(arguments); // we have the result
        currNode.setValue(result); // update value of node
        return result;
    }
}
