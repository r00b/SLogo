package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

public class Right extends TurtleCommand {
	private static final int ARGS = 1;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview rotation
		properties.getRotateProperty().set(properties.getRotateProperty().get() % 360 + args.get(0));
		return args.get(0);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
