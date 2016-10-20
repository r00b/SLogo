package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;


public class SetHeading extends TurtleCommand {
	private static final int ARGS = 1;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview rotation
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
