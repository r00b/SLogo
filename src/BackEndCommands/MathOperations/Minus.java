package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

public class Minus implements Command {
	private static final int ARGS = 1;
	
	@Override
	public double executeCommand(List<Double> args) {
		return args.get(0) * -1;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}