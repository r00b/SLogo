package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.LogoMethod;
import BackEndInternalAPI.ParseTreeExecutor;
import BackEndInternalAPI.ParseTreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static BackEndInternalAPI.ParseTreeExecutor.myMethods;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents a dotimes statement in Logo.
 */
public class To extends ControlCommand {

    private static final int ARGS = 3;

    @Override
    public double executeCommand(List<Double> args) {
        LogoMethod newMethod = new LogoMethod();

        String variableName = executables.get(0).getCommand();



        ParseTreeNode curr = executables.get(1);
//

        for (ParseTreeNode p : curr.getChildren()) {
            newMethod.addArgument(p.getCommand());
        }


        newMethod.setMethod(executables.get(2));

        myMethods.put(variableName,newMethod);
        return 1;
    }

    @Override
    public int numArguments() {
        return ARGS;
    }

}
