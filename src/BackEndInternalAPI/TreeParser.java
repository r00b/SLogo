package BackEndInternalAPI;

import java.util.Arrays;
import java.util.Iterator;

import BackEndCommands.Sum;

/**
 * 
 * @author Robert H. Steilberg II
 *
 */
public class TreeParser {


	private static Command getCommandObj(String cmd) {
        CommandDetector detector = new CommandDetector();
        detector.addPatterns("resources/languages/English"); //TODO get current language from GUI here
        detector.addPatterns("resources/languages/Syntax");
        System.out.println(String.format("%s : %s", cmd, detector.getSymbol(cmd)));

        return new Sum();
		
	}
	
	public static void main(String[] args) {
		getCommandObj("forward");
	}
	
	private ParserTreeNode buildParserTree(Iterator<String> cmdIter) {
		String currCmd = cmdIter.next();
		ParserTreeNode newChild = new ParserTreeNode(currCmd,getCommandObj(currCmd));
		for (int i = 0; i < 1; i++) {
			if (cmdIter.hasNext()) { // base case
				newChild.children.add(buildParserTree(cmdIter));
			}
		}
		return newChild;
	}

	public ParserTreeNode makeParserTree(String[] commands) {
		Iterator<String> cmdIter = Arrays.asList(commands).iterator();
		ParserTreeNode parserTree = buildParserTree(cmdIter);
		return parserTree;
	}
}
