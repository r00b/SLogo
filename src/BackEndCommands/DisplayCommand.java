package BackEndCommands;


import BackEndInternalAPI.Command;
import BackEndInternalAPI.DisplayProperties;
import BackEndInternalAPI.ParseTreeNode;


/**
 * An abstract class that all Display Commands extend. This parent class is needed
 * because all these commands need access to DisplayProperties whether it is for getting or setting
 * All children of this class have access to an instance of the DisplayProperties class
 *
 * @author ezra
 */
public abstract class DisplayCommand implements Command {

    protected DisplayProperties properties;

    /**
     * Sets the properties file in the class.
     *
     * @param o is the object to set
     */
    @Override
    public void setProperties(Object o) {
        if (o.getClass().equals(DisplayProperties.class)) {
            properties = (DisplayProperties) o;
        }
    }

    @Override
    public double executeCommand(ParseTreeNode node) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int numArguments() {
        // TODO Auto-generated method stub
        return 0;
    }
}