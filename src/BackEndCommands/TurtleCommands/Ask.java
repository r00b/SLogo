package BackEndCommands.TurtleCommands;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

public class Ask extends TurtleCommand{
	private static final int ARGS = 2;

	/**
	 * 
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		return properties.performAsk(node);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
