package FrontEndExternalAPI;

import java.util.ArrayList;

/**
 * @author Delia
 */
public interface Variables {
//    public Variables ();

    public void addVariable(String name, double value);

    public ArrayList<Integer> getAllVariables();
}