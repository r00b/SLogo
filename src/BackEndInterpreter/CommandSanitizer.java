package BackEndInterpreter;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class sanitizes an inputted Logo command so that it will run
 *         even if the command is defined over multiple lines or if the command
 *         contains empty lines.
 */
public class CommandSanitizer {

    private static final String SETTINGS_PATH = "resources/internal/Settings";

    /**
     * Sanitizes a Logo command so that it will run even if it is defined over several
     * lines or if it contains empty lines
     *
     * @param commands is a String array of the Logo commands
     * @return a String array of sanitized Logo commands
     */
    public String[] sanitize(String[] commands) {
        ResourceBundle settings = ResourceBundle.getBundle(SETTINGS_PATH);
        ArrayList<String> sanitizedCommands = new ArrayList<String>();
        sanitizedCommands.add("["); // put all commands in a list on one "line"
        for (String command : commands) {
            if (!command.equals(" ")) {
                if (!(command.trim().charAt(0) == '#')) { // entire line is comment
                    String[] splitCommands = command.trim().split(settings.getString("Delimiter"));
                    for (String splitCommand : splitCommands) {
                        if (splitCommand.equals("#")) break; // inline comment
                        if (!splitCommand.equals("")) { // don't add empty lines
                            sanitizedCommands.add(splitCommand);
                        }
                    }
                }
            }
        }
        sanitizedCommands.add("]");
        String[] result = new String[sanitizedCommands.size()];
        return sanitizedCommands.toArray(result);
    }
}