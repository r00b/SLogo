package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndInternalAPI.Command;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;

public class HideTurtle implements Command {
	private static final int ARGS = 0;
	//Note needs to be binded value passed from the controller
	private ObservableBooleanValue bool;
	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview visible property
		bool = new SimpleBooleanProperty(false);
		return 0;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
