package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

public class Difference implements Command {
	public static final int ARGS = 2;
	@Override
	public double executeCommand(List<Double> args) {
		return args.get(0) - args.get(1);
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}
