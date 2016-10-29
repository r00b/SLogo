package BackEndCommands.TurtleCommands;


import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the back command
 * @author ezra
 *
 */
public class Back extends TurtleCommand {
	private static final int ARGS = 1;

	/**
	 * Performs the same update as forward command except it subtracts distance instead of adding it
	 * Returns the distance moved
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		double value1 = arg1.executeCommand(arg1);
		//Need to update line and image position
		double xDistance = calculateXDistance(value1);
		double yDistance = calculateYDistance(value1);
		properties.setXProperty(properties.getXProperty() - xDistance);
		properties.setYProperty(properties.getYProperty() - yDistance);
		properties.setNewLineProperty(true);
		return value1;	
		}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
