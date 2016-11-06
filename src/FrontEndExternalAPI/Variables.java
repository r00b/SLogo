package FrontEndExternalAPI;

import BackEndInterface.CommandParser;
import javafx.collections.ObservableMap;

/**
 * @author Delia
 */
public interface Variables {

    /**
     *
     * @param variableSetter
     */
    void setVariableSetter(CommandParser variableSetter);

    /**
     *
     * @param name
     * @param value
     */
    void addVariable(String name, double value);

    /**
     *
     * @param map
     */
    void setMap(ObservableMap<? extends String, ? extends Double> map);
}