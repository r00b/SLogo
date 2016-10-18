package BackEndInternalAPI;

import java.util.ArrayList;

public class ParserTreeNode {
	public String value;
	int numChildren;
	Command cmdObj;
	public ArrayList<ParserTreeNode> children;
	
	
	ParserTreeNode(String val, Command commandObj) {
		value = val;
		children = new ArrayList<ParserTreeNode>();
		cmdObj = commandObj;
		numChildren = cmdObj.numArguments();
	}
	
	
}
