package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the atan command in Logo.
 */
public class ArcTangent implements Command {

    private static final int ARGS = 1;

    @Override
    public double executeCommand(List<ParseTreeNode> args) {
        //Still need to account for zero argument
        ParseTreeNode arg1 = args.get(0);
        return Math.atan(arg1.executeCommand(arg1.getChildren()));
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}