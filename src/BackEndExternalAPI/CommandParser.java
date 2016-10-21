package BackEndExternalAPI;

import BackEndCommands.Comment;
import BackEndInternalAPI.CommandTypeDetector;
import BackEndInternalAPI.ParseTreeExecutor;
import BackEndInternalAPI.ParseTreeNode;
import BackEndInternalAPI.ParseTreeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class interprets a Logo commandType and executes the action associated with it
 *         using the specified arguments. This is done by recursively creating a parse tree
 *         and executing down the nodes of the parse tree, checking for errors along the way.
 */
public class CommandParser { // TODO DE STATIC EVERYTHING

    protected static HashMap<String, Double> variables = new HashMap<String, Double>();

    protected static ResourceBundle commandTypes;

    /**
     * Determines if a specified command is indeed an executable Command
     * (i.e. not a comment or an empty line)
     *
     * @param command is the specified String command
     */
    private static boolean notCommand(String[] command) {
        if (command[0].equals("")) { // this is an empty line
            return true;
        }
        CommandTypeDetector detector = new CommandTypeDetector();
        if (detector.getCommandObj(command[0]).getClass() == Comment.class) { // this is a comment
            return true;
        }
        return false;
    }

    /**
     * Executes the cumulative action associated with a Logo commandType issued
     * from the editor
     *
     * @param command a string containing the commandType issued from the editor
     */
    public static double getAction(String command) {
        ResourceBundle settings = ResourceBundle.getBundle("resources/internal/Settings");

        String[] commands = command.trim().split(settings.getString("Delimiter"));
        if (notCommand(commands)) {
            return 0.0;
        }
        ParseTreeBuilder builder = new ParseTreeBuilder();
        ParseTreeExecutor executor = new ParseTreeExecutor();
//      executor.executeTree(builder.initParseTree(commands)); // TODO LEAVE COMMENTED WHEN DEBUGGING


        ParseTreeNode root = builder.initParseTree(commands); // TODO DEBUGGING
        double result = executor.executeTree(root); // TODO DEBUGGING
        System.out.println("--------- PRINTING TREE ----------"); // TODO DEBUGGING
        printTree(root);
        System.out.println("----------------------------------");
        return result;
        // return 0;
    }

    public static void printTree(ParseTreeNode r) { // TODO DEBUGGING
        //      r = r.getChild(1).getChild(0).getChild(1);
        System.out.println("VALUE: " + r.getValue()); // make sure workign
        System.out.println("COMMAND: " + r.getCommand());
        System.out.println("COMMAND TYPE: " + r.getCommandType());
//        if (r.hasNoChildren()) {
//            return;
//        }
//        for (int i = 0; i < r.getNumChildren(); i++) {
//            System.out.println("CHILD " + i);
//            printTree(r.getChild(i));
//        }
    }


    public static void main(String[] args) { // TODO DEBUGGING
//        getAction("make :shitter 92");
//        getAction("product :shitter 2");
//        double result = getAction("[ sum 2 3 ]");
        getAction("make :shitter 5");
        System.out.println(getAction("for [ :shitter 0 5 2 ] [ sum :shitter 0 ]"));

//        double result = getAction("[ sum 2 3 [ difference 6 5 ] minus 1 ]");
//        System.out.println(result);
//        System.out.println("G".equals("G"));


    }
}