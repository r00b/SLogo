package BackEndInternalAPI;


import com.sun.javafx.UnmodifiableArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class LogoMethod {

    private ArrayList<String> arguments;
    private ParseTreeNode method;


    public LogoMethod() {
        arguments = new ArrayList<String>();
    }

    public void addArgument(String argument) {
        arguments.add(argument);
    }

    public String getArgument(int index) {
        return arguments.get(index);
    }

    public int numArguments() {
        return arguments.size();
    }

    public void setMethod(ParseTreeNode newMethod) {
        method = newMethod;
    }

    public ParseTreeNode getMethod() {
        return method;
    }

}
