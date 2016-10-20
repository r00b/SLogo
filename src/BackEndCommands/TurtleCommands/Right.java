package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

/**
 * Executes the right command
 * @author ezra
 *
 */
public class Right extends TurtleCommand {
	private static final int ARGS = 1;

	/**
	 * Updates the rotation of image clockwise and updates the rotation property
	 * Returns the degrees rotated
	 */
	@Override
	public double executeCommand(List<Double> args) {
		properties.getRotateProperty().set(properties.getRotateProperty().get() % 360 + args.get(0));
		return args.get(0);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
