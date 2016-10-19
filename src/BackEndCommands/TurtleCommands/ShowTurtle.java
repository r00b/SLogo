package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndInternalAPI.Command;

public class ShowTurtle implements Command {
	private static final int ARGS = 0;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview visible property
		return 1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
