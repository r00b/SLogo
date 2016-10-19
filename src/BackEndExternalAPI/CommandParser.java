package BackEndExternalAPI;

import java.util.ArrayList;
import java.util.HashMap;

import BackEndCommands.Constant;
import BackEndCommands.ControlOperations.MakeVariable;
import BackEndCommands.ControlOperations.Variable;
import BackEndInternalAPI.ParserTreeNode;
import BackEndInternalAPI.TreeParser;

/**
 * 
 * @author Robert H. Steilberg II
 *
 */
public class CommandParser {
	
	private static HashMap<String,Double> variables = new HashMap<String,Double>();

	private static double executeTree(ParserTreeNode currNode) {
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

		
		if (currNode.numChildren == 0) {
			// i want to execute the command objects SUCH THAT the value WILL BE RETURNED
			// for a constant, value = the constant we want to return
			// BUT if its a command with no args, value = null; the value we want is located in executeCommand
			// SO we want a way to call execute command that will return the constant when we need it to		

//			if (currNode.getCmdObj().getClass() == Variable.class) {
//			}
			
			arguments.add(currNode.getValue()); // for a variable, value needs to be the string
			double value = currNode.getCmdObj().executeCommand(arguments);
			currNode.setValue(value);
			return value;
		}
		// otherwise, we must have a function, get children values, execute
		// get args, put into arraylist
		

        if (currNode.getCmdObj().getClass() == MakeVariable.class) { // we're dealing with variables
            Double value = executeTree(currNode.children.get(1));  // get the final value
            String name = currNode.children.get(0).getCommand();
            variables.put(name,value);
            currNode.setValue(value);
            return value;
        } else {
            for (int i = 0; i < currNode.numChildren; i++) {
                arguments.add(executeTree(currNode.children.get(i)));
            }
        }

		Double result = currNode.getCmdObj().executeCommand(arguments); // we have the result
		currNode.setValue(result); // update value of node
		return result;
	}
	
	
	/**
	 * 
	 * @param command
	 *            a string containing the command issued from the editor
	 */
	public static void getAction(String command) {
		String[] commands = command.split("\\p{Space}"); // replace with delimiter
		TreeParser parser = new TreeParser();
		ParserTreeNode root = parser.makeParserTree(commands);
		// root is now the parser tree, execute

		executeTree(root);
        System.out.println("--------- PRINTING TREE ----------");
		printTree(root);
        System.out.println("----------------------------------");

    }

	public static void printTree(ParserTreeNode r) {
		System.out.println("VALUE: " + r.value);
        System.out.println("COMMAND: " + r.command);
		if (r.children.isEmpty()) {
			return;
		}
		for (int i = 0; i < r.numChildren; i++) {
			System.out.println("CHILD " + i);
			printTree(r.children.get(i));
		}
	}

	public static void main(String[] args) {
		getAction("make :shit sum 4 minus 2");
        System.out.println(variables.get(":shit"));
	}
}
