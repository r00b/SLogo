package BackEndInternalAPI;

import java.util.ArrayList;

/**
 * 
 * @author Robert H. Steilberg II
 *
 */
public class ParserTreeNode {
//	public String value;
	public Double value;
	public int numChildren;
	Command cmdObj;
	public ArrayList<ParserTreeNode> children;
	boolean constant;
	
	
	ParserTreeNode(Double newValue, Command commandObj) {
		value = newValue;
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
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double newValue) {
		value = newValue;
	}
	
	public Command getCmdObj() {
		return cmdObj;
	}
	
}
