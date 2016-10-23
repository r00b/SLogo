package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

/**
 * Executes the ClearScreen command
 * @author ezra
 *
 */
public class ClearScreen extends TurtleCommand {
	private static final int ARGS = 0;

	/**
	 * Updates x, y property to the origin. Also sets clear property to true to tell front end to erase 
	 * all lines created.
	 * Returns the distance traveled to the origin
	 */
	@Override
	public double executeCommand(List<Double> args) {
		double answer = calculateTotalDistance(properties.getXProperty().get(), 0,
												properties.getYProperty(), 0);
		properties.setXProperty(0);
		properties.setYProperty(0);
		properties.getClearScreenProperty().set(true);
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
