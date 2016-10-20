package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndCommands.TurtleCommand;


public class Heading extends TurtleCommand {
	private static final int ARGS = 0;
	@Override
	public double executeCommand(List<Double> args) {
		return properties.getRotateProperty().get();
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}