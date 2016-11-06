package BackEndInterpreter;

import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * @author Robert Steilberg, Robert Duvall
 *         <p>
 *         This class serves as a detector for a certain instance of a Command object.
 *         A specified Logo command's type is detected via regular expressions and then
 *         called via reflection to create an instance of the respective Command object.
 */
public class CommandTypeDetector {

    private static final String LANGUAGES_PATH = "resources/languages/";
    private static final String SYNTAX_PATH = "resources/languages/Syntax";
    private static List<Entry<String, Pattern>> mySymbols;

    public CommandTypeDetector(String language) {
        mySymbols = new ArrayList<>();
        addResources(LANGUAGES_PATH + language);
        addResources(SYNTAX_PATH); // Logo syntax
    }

    /**
     * Determines which Command instance is associated with a
     * command passed in from the GUI
     *
     * @param command is the String to find a Command instance for
     * @return a Command instance respective for the specified command
     */
    public Command getCommandObj(String command) {
        ResourceBundle resources = ResourceBundle.getBundle("resources/internal/ClassLocations");
        String commandType = getCommandType(command);
        try {
            Class<?> cmdObj = Class.forName(resources.getString(commandType)); // get commandType class
            try {
                Constructor<?> commandObjCtor = cmdObj.getDeclaredConstructor();
                Object commandObject = commandObjCtor.newInstance(); // create an instance of the class
                return (Command) commandObject;
            } catch (Exception e) {
                e.printStackTrace(); // this error is properly caught by ParseTreeBuilder
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // this error is properly caught by ParseTreeBuilder
        }
        return null;
    }


    /**
     * Add specified resource files to the recognized types
     *
     * @param fileName is the path of the properties file to add
     */
    private void addResources(String fileName) {
        ResourceBundle resources = ResourceBundle.getBundle(fileName);
        Enumeration<String> propertiesIter = resources.getKeys();
        while (propertiesIter.hasMoreElements()) {
            String key = propertiesIter.nextElement();
            String regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Determine a String's Logo command type
     *
     * @param command is the String to test
     * @return the String's Logo command type, or "MethodCall"
     * if no match is found
     */
    public String getCommandType(String command) {
        for (Entry<String, Pattern> mapping : mySymbols) {
            if (isMatch(command, mapping.getValue())) {
                return mapping.getKey();
            }
        }
        return "MethodCall"; // either a method invocation or not a valid command
    }

    /**
     * Matches a String to a regular expression
     *
     * @param command is the String to be matched with the regular expression
     * @param regex   is the regular expression value to be matched against
     * @return true if commandType satisfies the regular expression, false otherwise
     */
    private boolean isMatch(String command, Pattern regex) {
        return regex.matcher(command).matches();
    }
}