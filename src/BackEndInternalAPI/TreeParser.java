package BackEndInternalAPI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;

import BackEndCommands.Constant;

/**
 * @author Robert H. Steilberg II
 */
public class TreeParser {

    /**
     * Determines which command is associated with the cmd passed in from the
     * GUI
     *
     * @param cmd
     * @return
     */
    private Command getCommandObj(String cmd) {
        ResourceBundle resources = ResourceBundle.getBundle("resources/internal/ClassLocations");

        CommandDetector detector = new CommandDetector();
        // TODO get current language from GUI
        String language = "English";
        detector.addPatterns("resources/languages/" + language);
        detector.addPatterns("resources/languages/Syntax");
        String commandName = detector.getSymbol(cmd);
        System.out.println(commandName);
        try {
            Class<?> cmdObj = Class.forName(resources.getString(commandName));
            try {
                Constructor<?> commandObjCtor = cmdObj.getDeclaredConstructor();
                Object o = commandObjCtor.newInstance();
                // Method numArgumentsMethod =
                // o.getClass().getMethod("numArguments", new Class[] {});
                // int numArguments = (Integer) numArgumentsMethod.invoke(o, new
                // Object[] {});
                return (Command) o;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            e.printStackTrace();
        }
        // Error has occurred, QUESTION do we throw here?
        return null;
    }

    private ParserTreeNode buildParserTree(Iterator<String> cmdIter) {
        String currCmd = cmdIter.next();

        Command cmdObj = getCommandObj(currCmd);
        ParserTreeNode newChild;
        if (cmdObj.getClass() == Constant.class) { // this is a constant
            newChild = new ParserTreeNode(Double.parseDouble(currCmd), cmdObj);
        } else {
//            System.out.println("SDASDASDASD" + currCmd);
            newChild = new ParserTreeNode(null, cmdObj);
        }
        newChild.setCommand(currCmd);
        // ParserTreeNode newChild = new
        // ParserTreeNode(currCmd,getCommandObj(currCmd));
        if (newChild.numChildren == 0) {
            return newChild;
        }
        for (int i = 0; i < newChild.cmdObj.numArguments(); i++) {
            newChild.children.add(buildParserTree(cmdIter));
        }
        return newChild;
    }

    public ParserTreeNode makeParserTree(String[] commands) {
        Iterator<String> cmdIter = Arrays.asList(commands).iterator();
        ParserTreeNode parserTree = buildParserTree(cmdIter);
        return parserTree;
    }

}
