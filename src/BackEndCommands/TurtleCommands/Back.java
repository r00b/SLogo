package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

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
	public double executeCommand(List<Double> args) {
		//Need to update line and image position
		double xDistance = calculateXDistance(args.get(0));
		double yDistance = calculateYDistance(args.get(0));
		properties.getXProperty().set(properties.getXProperty().get() - xDistance);
		properties.getYProperty().set(properties.getXProperty().get() - yDistance);
		properties.getNewLineProperty().set(true);
		return args.get(0);	
		}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
