package BackEndCommands.BooleanOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the less? command in Logo.
 */
public class LessThan implements Command {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<Double> args) {
        double answer = 0;
        if (args.get(0) < args.get(1)) {
            answer++;
        }
        return answer;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}