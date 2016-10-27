package BackEndCommands.MathOperations;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;
import org.apache.velocity.runtime.directive.Parse;

import java.util.List;

/**
 * @author Ezra Lieblich
 *         <p>
 *         This command instance represents the atan command in Logo.
 */
public class ArcTangent implements Command {

    private static final int ARGS = 1;

    @Override
    public double executeCommand(ParseTreeNode node) {
        //TODO Still need to account for zero argument
        ParseTreeNode arg1 = node.getChild(0);
        double value1 = arg1.executeCommand(arg1);
        return Math.atan(value1);
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}