package BackEndCommands.BooleanOperations;

import BackEndInternalAPI.Command;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the not command in Logo.
 */
public class Not implements Command {

    private static final int ARGS = 1;

    @Override
    public double executeCommand(List<Double> args) {
        double answer = 0;
        if (args.get(0) == 0) {
            answer++;
        }
        return answer;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}