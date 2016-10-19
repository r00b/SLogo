package BackEndInternalAPI;

import java.util.List;

public interface Command {
	
    public double executeCommand(List<Double> args);
    
    public int numArguments();
}