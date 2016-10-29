package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;


/**
 * Executes the Home command
 * @author ezra
 *
 */
public class Home extends TurtleCommand {
	private static final int ARGS = 0;

	/**
	 * updates x, y property to the origin and tells front end to create new line
	 * Returns the distance traveled to origin
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		double answer = calculateTotalDistance(properties.getXProperty(), 0,
				properties.getYProperty(), 0);
		properties.setXProperty(0);
		properties.setYProperty(0);
		properties.setNewLineProperty(true);
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
