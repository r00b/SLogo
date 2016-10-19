package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ObservableProperties;

public class Showing implements Command {
	private static final int ARGS = 0;
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (ObservableProperties.imageVisibleProperty.get()) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}