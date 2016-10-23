package FrontEndExternalAPI;

import java.util.ArrayList;

public interface Variables {
//    public Variables ();

    public void addVariable(String name, String value);

    public ArrayList<Integer> getAllVariables();
}