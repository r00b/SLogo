package BackEndCommands.DisplayCommands;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

public class SetBackground extends TurtleCommand{
	private static final int ARGS = 1;

	/**
	 * Sets the background color of the display
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		double value = arg1.executeCommand(arg1);
		//properties.setBackground(value);
		return value;	
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
