package BackEndCommands.BooleanOperations;

import java.util.List;

import BackEndInternalAPI.Command;

public class And implements Command{
	private static final int ARGS = 2;
	
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (args.get(0) != 0 && args.get(0) != 0) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}