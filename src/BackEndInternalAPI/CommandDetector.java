package BackEndInternalAPI;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * 
 * @author Robert H. Steilberg II
 *
 */
public class CommandDetector {
	
	private List<Entry<String, Pattern>> mySymbols;
	
	CommandDetector() {
		mySymbols = new ArrayList<>();
	}
	
	/**
	 * Add specified resources file to the recognized types
	 * 
	 * @param fileName
	 */
	public void addPatterns (String fileName) {
		ResourceBundle resources = ResourceBundle.getBundle(fileName);
		Enumeration<String> propIter = resources.getKeys();
		while (propIter.hasMoreElements()) {
			String key = propIter.nextElement();
			String regex = resources.getString(key);
			mySymbols.add(new SimpleEntry<>(key,Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}
	
	public String getSymbol(String cmd) {
		for (Entry<String, Pattern> mapping : mySymbols) {
			if (isMatch(cmd, mapping.getValue())) {
				return mapping.getKey();
			}
		}
		return "ERROR";
	}
	
	private boolean isMatch(String cmd, Pattern regex) {
		return regex.matcher(cmd).matches();
	}
	
	
	
}
