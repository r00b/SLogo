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
		double value1 = arg1.executeCommand(arg1);
		double value2 = arg2.executeCommand(arg2);
		double distance = properties.calculateTotalDistance(value1, value2);
		properties.setXProperty(value1);
		properties.setYProperty(value2);
		properties.setNewLineProperty(true);
		return distance;
	}
	
	@Override
	public int numArguments() {
		return ARGS;
	}
}