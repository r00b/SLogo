package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

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
	public double executeCommand(List<Double> args) {
		double xDistance = calculateXDistance(args.get(0));
		double yDistance = calculateYDistance(args.get(0));
		properties.getXProperty().set(properties.getXProperty().get() + xDistance);
		properties.getYProperty().set(properties.getXProperty().get() + yDistance);
		properties.getNewLineProperty().set(true);
		return args.get(0);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
