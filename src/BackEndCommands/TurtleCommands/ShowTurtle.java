package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;


public class ShowTurtle extends TurtleCommand {
	private static final int ARGS = 0;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview visible property
		properties.getImageVisibleProperty().set(false);
		return 1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
