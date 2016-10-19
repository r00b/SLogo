package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ObservableProperties;

public class Heading implements Command {
	private static final int ARGS = 0;
	@Override
	public double executeCommand(List<Double> args) {
		return ObservableProperties.rotateProperty.get();
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}