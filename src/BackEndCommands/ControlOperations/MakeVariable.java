package BackEndCommands.ControlOperations;

import BackEndExternalAPI.CommandParser;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents the make command in Logo.
 */
public class MakeVariable extends CommandParser implements Command {
    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<ParseTreeNode> args) {
        return 0;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
