package BackEndCommands.MathOperations;

import java.util.List;

import BackEndInternalAPI.Command;

public class Pi implements Command {
	private static final int ARGS = 0;
	
	@Override
	public double executeCommand(List<Double> args) {
		return Math.PI;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}