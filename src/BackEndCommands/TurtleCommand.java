package BackEndCommands;

import java.util.List;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ObservableProperties;

public abstract class TurtleCommand implements Command {
	protected ObservableProperties properties;
	@Override
	public double executeCommand(List<Double> args) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numArguments() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setProperties(ObservableProperties prop) {
		properties = prop;
	}
	
	protected double calculateTotalDistance(double x2, double x1, double y2, double y1) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	protected double calculateXDistance(double hyp) {
		double angle = properties.getRotateProperty().get();
		double answer = Math.cos(angle) * hyp;
		if ((angle > 270) || (angle>90 && angle < 180)) {
			answer = answer  * -1;
		}
		return answer;
	}

	protected double calculateYDistance(double hyp) {
		double angle = properties.getRotateProperty().get();
		double answer = Math.sin(angle) * hyp;
		if ((angle > 270) || (angle>90 && angle < 180)) {
			answer = answer  * -1;
		}
		return answer;
	}
	
}
