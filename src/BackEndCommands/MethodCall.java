package BackEndCommands;

import BackEndInternalAPI.Command;
import BackEndInternalAPI.LogoMethod;
import BackEndInternalAPI.ParseTreeNode;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents non-conventional commands in Logo
 *         (i.e. method calls for user-defined functions).
 */
public class MethodCall extends ControlCommand {

    private static final int ARGS = 0;

    @Override
    public double executeCommand(ParseTreeNode node) {
        String methodName = node.getRawCommand();
        LogoMethod method = getMethods().get(methodName);

        // to lol [ :a :b ] [ sum :a :b ]
        // lol 4 5

        for (int i = 0; i < method.numArguments(); i++) { // bind arguments to method variables
//            if (argumentError()) {
//                return null;
//            }
            String variable = method.getArgument(i); // :a
            double value = node.getChild(i).executeCommand(node.getChild(i)); // 4


//            if (myMappings.getMyMethodVariables().get(valueStr) != null) {
//                value = myMappings.getMyMethodVariables().get(valueStr);
//            } else if (myMappings.getMyVariables().get(valueStr) != null) {
//                value = myMappings.getMyVariables().get(valueStr);
//            } else {
//                try {
//                    value = Double.parseDouble(myCommands[myCommandIndex]);
//                } catch (NumberFormatException n) {
//                    value = 0.0; // trying to access non-initialized variable
//                }
//            }


            getMethodVariables().put(variable, value); // method variables placed in temporary map
        }

        double result = method.getMethod().executeCommand(method.getMethod());
        return result;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}
