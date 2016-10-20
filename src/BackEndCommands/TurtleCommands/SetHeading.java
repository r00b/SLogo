package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

/**
 * Executes the SetHeading Command
 * @author ezra
 *
 */
public class SetHeading extends TurtleCommand {
	private static final int ARGS = 1;

	/**
	 * Turns turtle to absolute heading
	 * Returns degrees moved. Note that returns the amount of degrees moved clockwise to get to new point
	 */
	@Override
	public double executeCommand(List<Double> args) {
		//Need to get actual rotation so use % 
		double set_degrees = args.get(0) % 360;
		double current_degrees = properties.getRotateProperty().get();
		properties.getRotateProperty().set(args.get(0));
		return Math.abs(set_degrees - current_degrees);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
