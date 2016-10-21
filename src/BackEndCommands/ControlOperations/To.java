package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeExecutor;
import BackEndInternalAPI.ParseTreeNode;

import java.util.HashMap;
import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a dotimes statement in Logo.
 */
public class To extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<Double> args) {
        HashMap<String, ParseTreeNode> commandVars = new HashMap<String, ParseTreeNode>();


        for (int i = 0; i < executables.get(1).getNumChildren(); i ++) {
            String currVar = executables.get(1).getChild(i).getCommand();
        }

        ParseTreeExecutor executor = new ParseTreeExecutor();

        String variable = executables.get(0).getChild(0).getCommand();
        double start = executor.executeTree(executables.get(0).getChild(1));
        double end = executor.executeTree(executables.get(0).getChild(2));
        double increment = args.get(0);

        double result = 0;

        for (double i = start; i < end; i += increment) {
            executor.setVariable(variable,i);
            result = executor.executeTree(executables.get(1));
            System.out.println(result);
        }
        return result;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
