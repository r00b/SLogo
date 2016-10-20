package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndCommands.TurtleCommand;


public class PenDown extends TurtleCommand {
	private static final int ARGS = 0;
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (properties.getPathVisibleProperty().get()) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}