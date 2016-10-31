package BackEndCommands.TurtleCommands;


import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the forward command
 * @author ezra
 *
 */
public class Forward extends TurtleCommand {
	private static final int ARGS = 1;

	/**
	 * Calculates the distance moved and changes x and y property. Also lets front end now needs a new line
	 * Returns the distance moved
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		ParseTreeNode arg1 = node.getChild(0);
		double value1 = arg1.executeCommand(arg1);
		properties.calculateXDistance(value1);
		double yDistance = calculateYDistance(value1);
		//properties.setXProperty((properties.getXProperty().get() + xDistance));
		properties.setYProperty(properties.getYProperty() + yDistance);
		properties.setNewLineProperty(true);
		return value1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
