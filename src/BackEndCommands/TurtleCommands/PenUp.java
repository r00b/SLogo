package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

public class PenUp extends TurtleCommand {
	private static final int ARGS = 0;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to make pen boolean true
		properties.getPathVisibleProperty().set(true);
		return 1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
