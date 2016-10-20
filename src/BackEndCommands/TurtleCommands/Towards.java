package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;

public class Towards extends TurtleCommand{
	private static final int ARGS = 2;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview visible property
		double degrees = calculateDegrees(args.get(0), args.get(1));
		properties.getRotateProperty().set(degrees);
		return Math.abs(degrees - properties.getRotateProperty().get());
	}
	@Override
	public int numArguments() {
		return ARGS;
	}

	private double calculateDegrees(double x, double y) {
		double answer;
		double angle = Math.atan(x / y);
		if (x > 0 && y < 0) {
			answer = 180 - angle;
		}
		else if (x < 0 && y < 0) {
			answer = 180 + angle;
		}
		else if (x < 0 && y > 0) {
			answer = 360 - angle;
		}
		else {
			answer = angle;
		} 
		return answer;
	}
}
