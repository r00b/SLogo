package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

public class Sine implements Command {
	private static final int ARGS = 1;
	
	@Override
	public double executeCommand(List<Double> args) {
		return Math.sin(args.get(0)) ;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}