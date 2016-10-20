package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;


public class PenDown extends TurtleCommand {
	private static final int ARGS = 0;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to set boolean for pen to false
		properties.getPathVisibleProperty().set(false);
		return 0;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
