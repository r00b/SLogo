package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

public class SetXY extends TurtleCommand{
	private static final int ARGS = 2;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview visible property
		double distance = calculateTotalDistance(args.get(0), properties.getXProperty().get(),
													args.get(1), properties.getYProperty().get());
		properties.getXProperty().set(args.get(0));
		properties.getYProperty().set(args.get(1));
		return distance;
	}
	@Override
	public int numArguments() {
		return ARGS;
	}
}