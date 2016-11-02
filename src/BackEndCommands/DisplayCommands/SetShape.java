package BackEndCommands.DisplayCommands;

import BackEndCommands.DisplayCommand;
import BackEndInternalAPI.ParseTreeNode;

public class SetShape extends DisplayCommand{
	private static final int ARGS = 1;

	/**
	 * Sets the image of the turtle
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		double value = arg1.executeCommand(arg1);
		properties.setImageIndex(value);
		return value;	
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
