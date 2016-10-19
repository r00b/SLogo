package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndInternalAPI.Command;

public class PenDown implements Command {
	private static final int ARGS = 0;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to set boolean for pen to false
		return 0;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
