package BackEndExternalAPI;

import BackEndInternalAPI.ParserTreeNode;
import BackEndInternalAPI.TreeParser;

/**
 * 
 * @author Robert H. Steilberg II
 *
 */
public class CommandParser {

	/**
	 * 
	 * @param command
	 *            a string containing the command issued from the editor
	 */
	public static void getAction(String command) {
		String[] commands = command.split(" ");
		TreeParser parser = new TreeParser();
		ParserTreeNode root = parser.makeParserTree(commands);
		// root is now the parser tree
		printTree(root);
	}
	
	public static void printTree(ParserTreeNode r) {
		System.out.println(r.value);
		if (r.children.isEmpty()) {
			return;
		}
		printTree(r.children.get(0));

	}

	public static void main(String[] args) {
		getAction("fd 3 rt 2");
	}
}
