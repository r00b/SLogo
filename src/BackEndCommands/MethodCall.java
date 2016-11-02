package BackEndCommands;

import BackEndInternalAPI.LogoMethod;
import BackEndInternalAPI.ParseTreeNode;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents method calls for user-defined
 *         functions and invalid commands.
 */
public class MethodCall extends ControlCommand {

    private static final int ARGS = 0;

    @Override
    public double executeCommand(ParseTreeNode node) {
        String methodName = node.getRawCommand();
        LogoMethod method = getMethods().get(methodName);
        for (int i = 0; i < method.numArguments(); i++) { // bind arguments to method variables
            String variable = method.getArgument(i); // :a
            double value = node.getChild(i).executeCommand(node.getChild(i)); // 4
            getMethodVariables().put(variable, value); // method variables placed in temporary map
        }
        return method.getMethod().executeCommand(method.getMethod());
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}