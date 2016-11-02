package BackEndCommands.TurtleCommands;


import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the SetXY Command
 * @author ezra
 *
 */
public class SetXY extends TurtleCommand{
	private static final int ARGS = 2;

	/**
	 * Updates the x, y property of image and makes front end draw new line
	 * Returns the distance traveled
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		ParseTreeNode arg2 = node.getChild(1);
		return properties.setXY(arg1, arg2);
	}
	
	@Override
	public int numArguments() {
		return ARGS;
	}
}