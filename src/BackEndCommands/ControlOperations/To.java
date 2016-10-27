package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.LogoMethod;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

//import static BackEndInternalAPI.ParseTreeExecutor.myMethods;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a method assignment statement in Logo.
 */
public class To extends ControlCommand {

    private static final int ARGS = 3;

    @Override
    public double executeCommand(List<ParseTreeNode> args) {
    	ParseTreeNode arg1 = args.get(0);
		ParseTreeNode arg2 = args.get(1);
		ParseTreeNode arg3 = args.get(2);
        LogoMethod newMethod = new LogoMethod();
        String variableName = arg1.getCommand();
        for (ParseTreeNode p : arg2.getChildren()) {
            newMethod.addArgument(p.getCommand());
        }
        newMethod.setMethod(arg3);
//        myMethods.put(variableName,newMethod);
        return 1;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
