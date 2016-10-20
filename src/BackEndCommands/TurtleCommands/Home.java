package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

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
	public double executeCommand(List<Double> args) {
		double answer = calculateTotalDistance(properties.getXProperty().get(), 0,
												properties.getYProperty().get(), 0);
		properties.getXProperty().set(0);
		properties.getYProperty().set(0);
		properties.getNewLineProperty().set(true);
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
