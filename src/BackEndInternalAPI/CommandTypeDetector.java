package BackEndInternalAPI;

import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class returns an instance of a Command object based off of a
 *         corresponding Logo commandType. This is done by using regular expressions
 *         to determine the type of Logo commandType specified by the caller via an
 *         inputted String.
 */
public class CommandTypeDetector {

    private List<Entry<String, Pattern>> mySymbols;

    public CommandTypeDetector() {
        mySymbols = new ArrayList<>();
        String language = "English"; // TODO get current language from GUI
        addPatterns("resources/languages/" + language);
        addPatterns("resources/languages/Syntax"); // Logo syntax
    }

    /**
     * Determines which commandType instance is associated with a
     * commandType passed in from the GUI
     *
     * @param command is the String to find a commandType instance for
     * @return an commandType instance equivalent to the type of commandType given
     */
    public Command getCommandObj(String command) {
        ResourceBundle resources = ResourceBundle.getBundle("resources/internal/ClassLocations");
        String commandType = getCommandType(command);
        try {
            Class<?> cmdObj = Class.forName(resources.getString(commandType)); // get commandType class
            try {
                Constructor<?> commandObjCtor = cmdObj.getDeclaredConstructor(); // create an instance of the class
                Object commandObject = commandObjCtor.newInstance();
                return (Command) commandObject;
            } catch (Exception e) {
                e.printStackTrace(); // TODO ERROR INSTANCE COULD NOT BE MADE
            }
        } catch (ClassNotFoundException e) {
            System.out.println("class not found"); // TODO DEBUGGING
            e.printStackTrace(); // TODO ERROR CLASS COULD NOT BE FOUND
        }
        return null; // TODO WHAT DO WE RETURN WHEN THERE'S AN ERROR
    }


    /**
     * Add specified resource files to the recognized types
     *
     * @param fileName is the path of the properties file to add
     */
    private void addPatterns(String fileName) {
        ResourceBundle resources = ResourceBundle.getBundle(fileName);
        Enumeration<String> propIter = resources.getKeys();
        while (propIter.hasMoreElements()) {
            String key = propIter.nextElement();
            String regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Determine a String's Logo commandType type
     *
     * @param command is the String to test
     * @return the String's Logo commandType type, or "ERROR"
     * if no match is found
     */
    public String getCommandType(String command) {
        for (Entry<String, Pattern> mapping : mySymbols) {
            if (isMatch(command, mapping.getValue())) {
                return mapping.getKey();
            }
        }
        return "NoType"; // TODO illegal commandType exception
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
