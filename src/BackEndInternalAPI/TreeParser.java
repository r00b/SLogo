package BackEndInternalAPI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
		detector.addPatterns("resources/languages/English"); // TODO get current
																// language from
																// GUI here
		detector.addPatterns("resources/languages/Syntax");
		String commandName = detector.getSymbol(cmd);
		System.out.println(commandName);
		try {
			Class<?> cmdObj = Class.forName("BackEndCommands." + commandName);
			try {
				Constructor<?> commandObjCtor = cmdObj.getDeclaredConstructor();
				Object o = commandObjCtor.newInstance();
				Method numArgumentsMethod = o.getClass().getMethod("numArguments", new Class[] {});
				int numArguments = (Integer) numArgumentsMethod.invoke(o, new Object[] {});
				System.out.println(numArguments);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("class not found");
			e.printStackTrace();
		}

		return new Sum();

	}

	public static void main(String[] args) {
		getCommandObj("sum");
	}

	private ParserTreeNode buildParserTree(Iterator<String> cmdIter) {
		String currCmd = cmdIter.next();
		ParserTreeNode newChild = new ParserTreeNode(currCmd, getCommandObj(currCmd));
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
