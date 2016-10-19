package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ObservableProperties;

public class YCor implements Command {
	private static final int ARGS = 0;
	@Override
	public double executeCommand(List<Double> args) {
		return ObservableProperties.yProperty.get();
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}