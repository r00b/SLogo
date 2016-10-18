package BackEndInternalAPI;

import java.util.ArrayList;

/**
 * 
 * @author Robert H. Steilberg II
 *
 */
public class ParserTreeNode {
	public String value;
	public int numChildren;
	Command cmdObj;
	public ArrayList<ParserTreeNode> children;
	
	
	ParserTreeNode(String val, Command commandObj) {
		value = val;
		children = new ArrayList<ParserTreeNode>();
		cmdObj = commandObj;
		numChildren = cmdObj.numArguments();
	}
	
	
}
