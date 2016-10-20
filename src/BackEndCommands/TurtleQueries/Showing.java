package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndCommands.TurtleCommand;


public class Showing extends TurtleCommand {
	private static final int ARGS = 0;
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (properties.getImageVisibleProperty().get()) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}