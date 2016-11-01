package BackEndCommands.DisplayCommands;

import BackEndCommands.DisplayCommand;
import BackEndInternalAPI.ParseTreeNode;

public class SetPenSize extends DisplayCommand{
	private static final int ARGS = 1;

	/**
	 * Sets the pen size of the display
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		double value = arg1.executeCommand(arg1);
		properties.setPenSize(value);
		return value;	
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
