package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ObservableProperties;

public class PenDown implements Command {
	private static final int ARGS = 0;
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (ObservableProperties.pathVisibleProperty.get()) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}