package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;


/**
 * Executes the left command
 * @author ezra
 *
 */
public class Left extends TurtleCommand {
	private static final int ARGS = 1;

	/**
	 * Updates the rotate property of the imageView counterclockwise by the parameter
	 * Returns the degrees rotated
	 */
	@Override
	public double executeCommand(List<Double> args) {
		properties.getRotateProperty().set(properties.getRotateProperty().get() % 360 - args.get(0));
		return args.get(0);

	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
