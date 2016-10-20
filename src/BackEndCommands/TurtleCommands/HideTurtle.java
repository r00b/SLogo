package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;


public class HideTurtle extends TurtleCommand {
	private static final int ARGS = 0;
	//Note needs to be binded value passed from the controller
	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview visible property
		properties.getImageVisibleProperty().set(false);
		return 0;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
