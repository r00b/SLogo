package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ObservableProperties;

public class SetHeading implements Command {
	private static final int ARGS = 1;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview rotation
		double degrees_moved = Math.max(args.get(0), ObservableProperties.rotateProperty.get()) -
								Math.min(args.get(0), ObservableProperties.rotateProperty.get());
		ObservableProperties.rotateProperty.set(args.get(0));
		return degrees_moved;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
