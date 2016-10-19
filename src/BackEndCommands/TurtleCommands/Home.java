package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ObservableProperties;

public class Home implements Command {
	private static final int ARGS = 0;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview rotation
		double answer = Math.sqrt(Math.pow(ObservableProperties.xProperty.get(), 2) + 
							Math.pow(ObservableProperties.yProperty.get(), 2));
		ObservableProperties.xProperty.set(args.get(0));
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
