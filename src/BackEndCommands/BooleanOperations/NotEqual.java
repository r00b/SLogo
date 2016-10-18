package BackEndCommands.BooleanOperations;

import java.util.List;

import BackEndInternalAPI.Command;

public class NotEqual implements Command{
	private static final int ARGS = 2;
	
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (!(args.get(0).equals(args.get(1)))) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}

}