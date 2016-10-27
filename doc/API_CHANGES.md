#API changes
Robert Steilberg | rhs16
Delia Li | dl202
Ezria Lieblich | eml36
Grayson Wise | gkw

##API Details and revisions

###Front-end External API

* Classes: GuiController.java, StartMenu.java, Console.java, History.java, Variables.java, Editor.java
* Features supported: User interface, different portions of the IDE, user selection of parameters, displaying history and messages, allowing user to input commands
* Resources used: Bindings, Javafx graphics, lists, maps, nested classes
* Usage of this API: The front-end external is used for everything that needs to communicate with the back end. Parameters are generated, and back end messages can reach the user through these classes.
* Extension for additional requirements: These can be easily extended by introducing small helper classes and allowing GUIController to continue assuming the responsibility of handling all communication/data between the classes.
* Justification of classes listed:
	* GUIController.java- This class is crucial to facilitate communication between front-end and back-end and transfer of responsibility among all of the other front-end classes. This also controls some of the scene changing and logistical changes seen by the user.
```
public interface GUIController {
    public GUIController();

    public void init();

    public void getInitialParams();

    public void getCurrentCommand();

    public void passCurrentCommand();

    public void throwError();

    public void getErrors();

    public void storeOldCommand();

    public void returnAction();
}
```

* StartMenu.java- This class begins on launch and lets the user select parameters and colors before entering the IDE.
```

public interface StartMenu {
    public StartMenu();

    public void setParameters();

    public void initIDE();
}
```
* Console.java- This class controls what is printed and displayed in the console portion of the IDE, which typically will consist of error messages and notifications after each command is entered by the user.
```
public interface Console {
    public Console();

    public void printResult();
}
```
* History.java- This class controls the history portion of the IDE, which lists the commands entered in that session in the order that the user entered them. These commands are executable if clicked as well.
```
public interface History {
    public History();

    public void addCommand();

    public void callCommand();
}
```
* Variables.java- This class controls the variables portion of the IDE, which lists the variables that have been declared by the user. This class allows the user to edit variable values and see new variables that they declared on the command line.
```
public interface Variables {
    public Variables ();

    public void addVariable();

    public ArrayList<Integer> getAllVariables(){
        return null;
    }
}
```
* Editor.java- This class controls the editor (or the command line) in the IDE, which lets the user type commands and pass them to the back end through the controller.
```

public interface Editor{
    public Editor();

    public void enterPressed();
}
```

Front-end Internal API: RenderSprite.java, ButtonMenu.java, Options.java

* Features supported: Rendering graphics, creating buttons and options, passes data and parameters to front end controllers and external areas
* Resources used: Javafx API, nested classes, maybe a sprite object
* Usage of this API: The front end internal API is used for the classes that handle the display for the user. Thus, these classes would only be communicating amongst themselves and the front end external API, and would be responsible for generating just graphics for the user.
* Extension for additional requirements: This API is easily extended, since most of the classes are helper classes anyway. If we had to add a new option menu or implement other new features such as having the user select more parameters and introduce other modes, they would simply be added as a new class in the case of the new option menu, or very easily as new buttons with the proper eventHandlers attached.
* Justification of classes listed
	* RenderSprite.java: This class redraws the display each time the user inputs a command by updating positions and other attributes of nodes currently in the display. This also lets the user reset the display and start over from scratch.
```
public interface RenderSprite {
    public RenderSprite();

    public void updateNodes();

    public void updateDisplayOptions();

    public void resetIDE();
}
```
* ButtonMenu.java: This class generates the toolbar along the upper side of the IDE.
```
public interface ButtonMenu {
    public ButtonMenu();

    public void addButton();
}
```
* Options.java: This class generates the parameter selectors in the options popup from the IDE.
```
public interface Options {
    public Options();

    public void changeBackgroundColor();

    public void changePenColor();

    public void changeLanguage();

    public void changeSpriteImage();
}
```

Back-end External API: CommandParser.java, ErrorChecker.java

* Features supported: Interpreting commands, throwing errors, generating results
* Resources used: Maybe generics, internal back end API
* Usage of this API: The back-end external API communicates with the front-end external API in order to transfer data such as commands, results, errors, and other variables from the back end to the front end.
* Extension for additional requirements: This could be extended through the implementation of more helper classes and objects, with little change to the ones currently listed. This would help when new features are added in order to smoothly integrate them with the front end.
* Justification of classes listed
	* CommandParser.java: This class interprets each command passed in from the Console, and generates Command objects and parse trees in order to fetch results.
```
public interface CommandParser {
    public CommandParser();

    public void createParseTree();

    public void getTreeResults();

    public void passResultToGUI();
}
```

###Changes made:
* createParseTree() has been renamed to getAction()
* getTreeResults() was removed from the API since createParseTree() returns the built parse tree. Rather than using getTreeReults() to execute the tree, we simply call the Command object's executeCommand() method on the head of the tree
* passResultToGUI() was removed since createParseTree() returns the result to the GUI and all other communication is handled through binding
* setProperties() has been added to pass down observable properties from the front end to the back end
* getVariables() has been added as a getter for the front end to display set variables

* ErrorChecker.java: This class checks each command for errors in formatting, logic, arithmetic, and syntax. It communicates with the console in the front end.
```
public interface ErrorChecker {
    public ErrorChecker();

    public void checkForErrors();
}
```

Back end Internal API: Command.java, ParseTree.java

* Features supported: Interpreting commands, recursive parsing work, determining whether errors exist
* Resources used: Reflections, tree structure, recursion, error checking
* Usage of this API: The back end internal API communicates with the back end external API through these helper classes. It does the "busy" work such as parsing each command letter by letter and analyzing its meaning through parseTrees.
* Extension for additional requirements: Since this API is all helper classes anyway, creating more helper classes in order to implement additional features would not be so hard. Minimal amounts of code in the front end external API may need to be modified in order to account for changes here.
* Justification of classes listed
	* Command.java: This class takes a string parameter and converts it through the use of a ParseTree into a usable object that stores each portion of the inputted command. This allows string inputs to be organized as commands which can be added to lists and easily sorted through, without having to convert a string into a command every time it is needed.
```
public interface Command {
    public Command(String userInput);

    public void makeActionFromCommand();

    public void getCommandType();
}
```
###Changes made:
* makeActionFromCommand() is now renamed as executeCommand() and takes in a ParseTreeNode
* We added a public method numArgs() to assist with getting the number of arguments for a particular command

* ParseTree.java: This class interprets each command by setting up a tree structure and finding out how many iterations are required to get from the leaf nodes to the root node. This determines the values of variables such as distances in commands.
```
public interface ParseTree {
    public ParseTree(Command c);

    public void getLeaves();

    public int returnNewParam();
}
```
###Changes made:
* ParseTree is now named ParseTreeBuilder
* returnNewParam() is now named initParseTree() and is called by CommandParser to return the head of the tree
* getLeaves() was made private and is now essentially buildParseTree()
* We added a public method setProperties() that is called by CommandParser to set the observable properties associated with the turtle

##Additions
* The CommandTypeDetector class was created to use regular expressions to detect the type of command issued; it helps with initializing ParseTreeNodes
    * getCommandObj() uses reflection to get the Command object associated with a specified command
    * getCommandType() determines the type of a specified command (such as if it is a variable, etc.)
* The ObservableProperties class was created to handle observable data structures for passing data between the front and back end
    * There are public getters for all of the properties associated with the display
* The ParseTreeNode class holds information about nodes in the tree of commands
    * There are public getters and setters for all of the properties associated with a ParseTreeNode
* The TurtleCommand class has a public setPropties() method for binding turtle properties with the front end
* The ControlCommand class has a public setVariables() method for executing MakeVariable and To kinds of instructions