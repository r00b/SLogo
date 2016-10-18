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
	boolean constant;
	
	
	ParserTreeNode(String val, Command commandObj) {
		value = val;
		children = new ArrayList<ParserTreeNode>();
		cmdObj = commandObj;
		numChildren = cmdObj.numArguments();
	}
	
	public boolean isConstant() {
		return constant;
	}
	
	public void setConstant(boolean makeConstant) {
		constant = makeConstant;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String newValue) {
		value = newValue;
	}
	
	public Command getCmdObj() {
		return cmdObj;
	}
	
}
