package BackEndCommands;

import java.util.List;

import BackEndInternalAPI.Command;

public class Constant implements Command {
	private static final int ARGS = 0;
	
	@Override
	public double executeCommand(List<Double> args) {
		return args.get(0);
	}
	
	@Override
	public int numArguments() {
		return ARGS;
	}
}
