package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.LogoMethod;
import BackEndInternalAPI.ParseTreeNode;

//import static BackEndInternalAPI.ParseTreeExecutor.myMethods;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a method assignment statement in Logo.
 */
public class To extends ControlCommand {

    private static final int ARGS = 3;

    @Override
    public double executeCommand(ParseTreeNode node) {
    	ParseTreeNode arg1 = node.getChild(0);
		ParseTreeNode arg2 = node.getChild(1);
		ParseTreeNode arg3 = node.getChild(2);
        LogoMethod newMethod = new LogoMethod();
        String methodName = arg1.getRawCommand();
        for (ParseTreeNode p : arg2.getChildren()) {
            newMethod.addArgument(p.getRawCommand());
        }
        newMethod.setMethod(arg3);
        getMethods().put(methodName,newMethod);
        return 1;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
