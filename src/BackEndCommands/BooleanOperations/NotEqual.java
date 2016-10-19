package BackEndCommands.BooleanOperations;

import java.util.List;

import BackEndInternalAPI.Command;

public class NotEqual implements Command{
	private static final int ARGS = 2;
	
	@Override
	public double executeCommand(List<Double> args) {
		if (!(args.get(0).equals(args.get(1)))) {
			return 1;
		}
		return 0;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}