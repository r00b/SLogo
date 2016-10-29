package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the HideTurtle command
 * @author ezra
 *
 */
public class HideTurtle extends TurtleCommand {
	private static final int ARGS = 0;
	
	/**
	 *Sets image visible property to false
	 *Returns 0
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		//Need to update imageview visible property
		properties.setImageVisibleProperty(false);
		return 0;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
