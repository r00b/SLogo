package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

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
	public double executeCommand(List<Double> args) {
		double distance = calculateTotalDistance(args.get(0), properties.getXProperty().get(),
													args.get(1), properties.getYProperty());
		properties.getXProperty().set(args.get(0));
		properties.setYProperty(args.get(1));
		properties.getNewLineProperty().set(true);
		return distance;
	}
	@Override
	public int numArguments() {
		return ARGS;
	}
}