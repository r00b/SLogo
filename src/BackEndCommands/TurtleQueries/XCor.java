package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.ObservableProperties;

public class XCor extends TurtleCommand {
	private static final int ARGS = 0;
	@Override
	public double executeCommand(List<Double> args) {
		return properties.getXProperty().get();
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}