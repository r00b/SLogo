package BackEndInternalAPI;

import java.util.List;

public interface Command {
//    public Command(String userInput);

    public double executeCommand(List<Double> args);
    public int numArguments();
}