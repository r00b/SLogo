package BackEndInternalAPI;

import BackEndCommands.Constant;
import BackEndCommands.ControlCommand;
import BackEndCommands.ControlOperations.MakeVariable;
import BackEndCommands.ControlOperations.Variable;
import BackEndCommands.TurtleCommand;
import BackEndExternalAPI.CommandParser;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class executes a Logo commandType parse tree via the
 *         executeTree() method.
 */
public class ParseTreeExecutor extends CommandParser {

//    private static String COMMANDTYPES_PATH = "resources/internal/CommandTypes";
//    private static ResourceBundle myCommandTypes = ResourceBundle.getBundle(COMMANDTYPES_PATH);
////    private static ObservableProperties myProperties;
//
////    public static void setProperties(ObservableProperties properties) {
////        myProperties = properties;
////    }
//
//    /**
//     * Get a value associated with a variable
//     *
//     * @param variable is a String representing the variable name
//     * @return a double representing the specified variable
//     */
//    public double getVariable(String variable) {
//        return myVariables.get(variable);
//    }
//
//    /**
//     * Bind a variable to a value
//     *
//     * @param variable is a String representing the variable name
//     * @param value    is a double representing the value to bind to the variable
//     */
//    public void setVariable(String variable, double value) {
//        myVariables.put(variable, value);
//    }
//
//    /**
//     * Set the value of a node holding a variable to the value of that variable
//     *
//     * @param currNode is the node holding the variable
//     * @return the value that the node was set to
//     */
//    private double accessVariable(ParseTreeNode currNode) { // TODO REFACTOR
//        double variableVal;
//        if (myVariables.get(currNode.getCommand()) == null && myMethodVariables.get(currNode.getCommand()) == null) {
//            currNode.setValue(0.0); // set to 0 if no variable
//            return 0.0;
//        } else if (myVariables.get(currNode.getCommand()) == null) { // check if regular variable
//            variableVal = myMethodVariables.get(currNode.getCommand());
//            currNode.setValue(variableVal);
//        } else {
//            variableVal = myVariables.get(currNode.getCommand()); // check if function-defined variable
//            currNode.setValue(variableVal);
//        }
//        return variableVal;
//    }
//
//    /**
//     * Set the value of a variable
//     *
//     * @param currNode is the node containing the value to which the variable is bound
//     * @return the value of the newly bound variable
//     */
//    private double makeVariable(ParseTreeNode currNode) {
//        Double variableValue = executeTree(currNode.getChild(1)); // get the final value
//        String variableName = currNode.getChild(0).getCommand();
//        myVariables.put(variableName, variableValue);
//        currNode.setValue(variableValue);
//        return variableValue;
//    }
//
//    /**
//     * Execute a control-type command
//     *
//     * @param currNode  is the node containing the control command to execute
//     * @param arguments is a list of arguments for the control command
//     * @return the value that results from the command
//     */
//    private double executeControlCommand(ParseTreeNode currNode, ArrayList<Double> arguments) {
//        // add list of subtrees to the node that will be executed
//        ((ControlCommand) currNode.getCommandObj()).setExecutables(currNode.getChildren());
//        arguments.add(executeTree(currNode.getChild(0))); // set condition for if statements
//        double finalValue = currNode.getCommandObj().executeCommand(arguments); // result of the control block
//        currNode.setValue(finalValue);
//        return finalValue;
//    }
//
//    private void addTurtleProperties(ParseTreeNode currNode) {
//        ((TurtleCommand) currNode.getCommandObj()).setProperties(myProperties);
//    }
//
//    /**
//     * Recursively executes Logo commands by traversing a parse tree and
//     * executing each action associated with each node using the child
//     * nodes as arguments.
//     *
//     * @param currNode is the tree node with the action to be executed
//     * @return the result of the action as a Double
//     */
//    public double executeTree(ParseTreeNode currNode) {
//        ArrayList<Double> arguments = new ArrayList<Double>(); // arguments used to execute the node
//        if (currNode.getCommandObj().getClass() == Variable.class) { // trying to access a variable
//            return accessVariable(currNode);
//        }
//        if (currNode.getCommandObj().getClass() == MakeVariable.class) { // trying to make a variable
//            return makeVariable(currNode);
//        }
//        if (myCommandTypes.getString(currNode.getCommandType()).equals("Turtle")) {
//            addTurtleProperties(currNode);
//        }
//        if (currNode.hasNoChildren()) { // base case, execute the command associated with the tree
//            if (currNode.getCommandObj().numArguments() != 0 || currNode.getCommandObj().getClass() == Constant.class) {
//                arguments.add(currNode.getValue());
//            }
//            double finalValue = currNode.getCommandObj().executeCommand(arguments);
//            currNode.setValue(finalValue);
//            return finalValue;
//        }
//        if (myCommandTypes.getString(currNode.getCommandType()).equals("Control")) { // control command
//            return executeControlCommand(currNode, arguments);
//        }
//
//        // execute all child nodes and add to argument list for parent node
////        arguments.addAll(currNode.getChildren().stream()
////                .map(ParseTreeExecutor::executeTree)
////                .collect(Collectors.toList()));
//
//        for (ParseTreeNode child : currNode.children) { //TODO LOL
//            arguments.add(executeTree(child));
//        }
//
//        double result = currNode.getCommandObj().executeCommand(arguments); // execute parent node to get overall result
//        currNode.setValue(result);
//        return result;
//    }
}
