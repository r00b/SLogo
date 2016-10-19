package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ObservableProperties;


public class Left implements Command {
	private static final int ARGS = 1;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview rotation
		ObservableProperties.rotateProperty.set(ObservableProperties.rotateProperty.get()-args.get(0));
		return args.get(0);

	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
