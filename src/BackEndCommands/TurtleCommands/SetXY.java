package BackEndCommands.TurtleCommands;

import java.util.List;

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
	public double executeCommand(List<ParseTreeNode> args) {
		ParseTreeNode arg1 = args.get(0);
		ParseTreeNode arg2 = args.get(1);
		double value1 = arg1.executeCommand(arg1.getChildren());
		double value2 = arg2.executeCommand(arg2.getChildren());
		double distance = calculateTotalDistance(value1, properties.getXProperty().get(),
													value2, properties.getYProperty());
		properties.getXProperty().set(value1);
		properties.setYProperty(value2);
		properties.getNewLineProperty().set(true);
		return distance;
	}
	@Override
	public int numArguments() {
		return ARGS;
	}
}