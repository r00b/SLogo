package BackEndExternalAPI;

import BackEndInternalAPI.ParseTreeExecutor;
import BackEndInternalAPI.ParseTreeNode;
import BackEndInternalAPI.ParseTreeBuilder;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class interprets a Logo commandType and executes the action associated with it
 *         using the specified arguments. This is done by recursively creating a parse tree
 *         and executing down the nodes of the parse tree, checking for errors along the way.
 */
public class CommandParser {

    protected static HashMap<String, Double> variables = new HashMap<String, Double>();

    /**
     * Executes the cumulative action associated with a Logo commandType issued
     * from the editor
     *
     * @param command a string containing the commandType issued from the editor
     */
    public static void getAction(String command) {
        ResourceBundle settings = ResourceBundle.getBundle("resources/internal/Settings");
        String[] commands = command.split(settings.getString("Delimiter"));
        ParseTreeBuilder builder = new ParseTreeBuilder();
        ParseTreeExecutor executor = new ParseTreeExecutor();
//      executor.executeTree(builder.initParseTree(commands)); // TODO LEAVE COMMENTED WHEN DEBUGGING


        ParseTreeNode root = builder.initParseTree(commands); // TODO DEBUGGING
        executor.executeTree(root); // TODO DEBUGGING
        System.out.println("--------- PRINTING TREE ----------"); // TODO DEBUGGING
        printTree(root);
        System.out.println("----------------------------------");
    }

    public static void printTree(ParseTreeNode r) { // TODO DEBUGGING
        System.out.println("VALUE: " + r.getValue());
        System.out.println("COMMAND: " + r.getCommandType());
        if (r.hasNoChildren()) {
            return;
        }
        for (int i = 0; i < r.getNumChildren(); i++) {
            System.out.println("CHILD " + i);
            printTree(r.getChild(i));
        }
    }

    public static void main(String[] args) { // TODO DEBUGGING
//        getAction("make :shitter 92");
//        getAction("product :shitter 2");
        getAction("fd 90");
        getAction("sin 90");
        getAction("or 1.0 0.0");
        getAction("equal? 0 0.0");
    }
}
