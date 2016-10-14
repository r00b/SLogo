#Design Overview
Robert Steilberg | rhs16
Delia Li | dl202


##Introduction

For this project, our team will develop an IDE meant to interpret the Logo language, which is a simple language that is used to help teach children how to program. We want to keep our IDE as simple as possible, with very easy-to-use features. To develop this application, we will divide our team into two teams: front-end and back-end. The front-end team will be in charge of creating the GUI, which convers taking in the code input, displaying error messages, and showing the output of the code given. The back-end team will be responsible for parsing the code that is input by the user, and determining whether it is valid or invalid. This section of our program will need to be very flexible to support adding more commands as we expand our project.

When the user inputs a line of code, the GUI will pass the input code to the back-end, which will then parse the input. Once they determine whether or not it is valid, they will pass back either an error message or the result of parsing the code. The GUI will then display what the back-end has sent to it. Therefore, the display and the parsing logic can both be closed, but the input from the user, as well as the instruction that it describes, must be open to be passed between the two sides. Other than communicating the code snippets, the GUI and the back-end logic will be completely separate from one another.

##Design Overview

The four APIs that we have designed are defined below:

* Front-end External API
	* GUIController.java
	* StartMenu.java
	* Console.java
	* History.java
	* Variables.java
	* Editor.java

The front end external API handles GUI manipulation, handling user input, interfacing that input with the back end of the application. GUIController interfaces the user-manipulated controls and the drawable Turtle with the event handlers that react to user inputs (getCurrentCommand(), passCurrentCommand()). GUIController also serves as the controller for the front end, interfacing with the controller of the back end to interpret commands and receive back a corresponding action (returnAction()). GUIController also receives errors to be reported to the user (getErrors(), throwErrors()). StartMenu handles an initial menu screen and sets initial program parameters (setParameters()) based on selections made by the user before it initializes the actual IDE (initIDE()). Console handles the console object, which prints results to each command issued by the user. History handles the history object, which displays previously issued commands through addCommand() in a list. These previous commands can be executed through callCommand(). Variables handles the variables object, which displays user-set variables that are stored in a list, and returns them via getVariable(). Editor handles the Editor object, which is where the user can type and submit commands to the IDE. Commands are issued when an event handler detects that the enter button has been pressed and subsequently calls enterPressed(), indicating to GUIController that there is a command to pass to the back end for interpretation.

* Front-end Internal API
	* RenderSprite.java
	* ButtonMenu.java
	* Options.java
	* GUIinit.java

The front end internal API handles the GUI functions that do not interact with the back end of the application. RenderSprite updates the Turtle sprite along with other GUI components as commands are interpreted and returned by the back end parser logic through the GUIController (updateNodes()). ButtonMenu creates the buttons that handle the control flow of the program (i.e. play and pause buttons, et cetera, through createButton()). Options handles user-changeable options, such as pen and background color, through each of the change methods. GUIinit initializes the GUI and its nodes with the initNodes() method.

* Back-end External API
	* CommandParser.java
	* ErrorChecker.java

The back end external API handles receiving commands from the front end, passing them to the internal API to be interpreted, and then passing the interpreted commands back up to the front end for execution. CommandParser (which should really be named InterpreterController, a decision made after creating our visual diagrams) will actually pass the commands back to the Command class, create the parser tree (createParseTree()), get the results (getTreeResults()), and pass these results back to the GUI (passResultToGUI()). ErrorChecker will check the list created by the parser tree or interpreter for detected errors (checkForErrors()) and return those errors back to the front end for reporting.

* Back end Internal API
	* Command.java
	* ParseTree.java

The back end internal API handles the actual interpretation of commands that has no interaction with the front end of the application. Command will use regular expressions to generate an action from each string command (makeActionFromCommand()) while ParseTree will create a parser tree that breaks each command down into its subcomponents for interpretation (createParseTree()). The results are then returned back to the GUI (passResultToGUI()). Detected errors will be placed in a list for ErrorChecker to check.

The following diagram shows the methods within our classes and how they interact with other classes and methods:

![Class Communication](https://lh3.googleusercontent.com/-Ivy_tkTMZGg/V__SpEutfII/AAAAAAAAAOo/zymHf0ta6uAame-F1jxaCSmTfbF_RqvjwCLcB/s0/IMG_5574.JPG "ClassCommunications.JPG")

##User Interface

1. Start Menu: allows users to select beginning parameters before launching the program.

 ![Start Menu](https://lh3.googleusercontent.com/-ZJO2mYwCGfM/V__TOAXAmII/AAAAAAAAAOw/312wyH4oRmIBA57kQepnmG87efaMBj4vQCLcB/s0/IMG_5571.JPG "StartMenu.JPG")

2. IDE window: contains a console, command line, display, toolbar, variable list, and list of previously issued commands
3. Console: reports errors and results of issuing commands. Ideally the user can click on them for more detailed description. We would also like to implement error reporting where the console tells the display to highlight errors in red so the user can see exactly where on the image their commands are failing.
4. Command editor: Editor where commands are typed and issued.
5. Display: Window in which sprites such as the turtle, its paths, and the background update in real time. Ideally, the display will communicate with console to mark parts of the display where the commands are failing.
6. Toolbar: Allows user to access number of options, including the start menu.
7. Variables: Shows current variables user has introduced, along with their values, and allows user to change their values by clicking and inputting.

 ![Editor](https://lh3.googleusercontent.com/-GqmFfbftHdM/V__TeVJ-LUI/AAAAAAAAAO4/RCyzrToa_hoyUYp9p4ajPezoeTpNO9OQQCLcB/s0/IMG_5569.JPG "Editor.JPG")

8. Options: Pop up that allows user to change attributes of the editor

![Options](https://lh3.googleusercontent.com/-FL_cEPHedDc/V__TutnmQyI/AAAAAAAAAPE/u19nLYV4MtEIY6TSpC4EVINhmF4oE-CrQCLcB/s0/IMG_5570.JPG "optionsMenu.JPG")

Erroneous situations reported to the user include formatting problems, incorrectly typed instructions, commands that don't exist, and undefined variables.

##API Details

Front-end External API

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

* Features supported:
* Resources used: Maybe generics,
* Usage of this API
* Extension for additional requirements
* Justification of classes listed

Back end Internal API: Command.java, ParseTree.java

* Features supported:
* Resources used: Reflections
* Usage of this API
* Extension for additional requirements
* Justification of classes listed

##API Example Code

* Show actual "sequence of code" that implements the following use case:
The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.

```
Editor.enterPressed(); //user presses enter after typing command in editor
GUIController.passCurrentCommand(); //Takes from editor, passes to back end parsers
CommandParser.createParseTree(); //parser builds parse tree to interpret command
	CommandParser.getTreeResults() //retrieves results from parse tree
		ParseTree.getLeaves();
ErrorChecker.checkForErrors(); //checks command for errors
CommandParser.passResultToGUI(); //sends translation back to controller class
GUIController.storeOldCommand(); //adds command to history
	History.addCommand();
GUIController.returnAction(); //transfers responsibility to front end
RenderSprite.updateNodes(); //render updates image in the display
Console.printResult(); //result of command prints in console
```

Use cases:

* User issues valid command: The command is passed back through the controller into the back end. The back end builds a parser tree with the command to reduce it down into parseable subcomponents. The actions associated with each command are then calculated and passed back up to the front end GUI, where the actions are reflected on the display.
* User issues "fs" command instead of "fd" command: The error will be checked for by ErrorChecker.java in the external back end API, which will use the internal back end API to check the parser tree for syntax issues. The syntax issue will be caught and an exception will be shown to the user.
* User issues a command that moves the turtle out of the display view: The command is passed to the back end, executed, and then passed back up to the front end. The front end detects that the command is moving the turtle out of the display, halts execution, and shows an exception to the user.
* User issues a command that references an undefined variable: When the command is interpreted by the back end, the command interpreter determines attempts to find a reference for the defined variable. If no reference is found, ErrorChecker.java is invoked, and an error is thrown back up to the user indicating that an undefined variable was referenced.
* User issues a command that was previously interpreted: before each command is executed, the interpreter checks to see if the command is in the command history list. If it is, the back end controller (currently CommandParser.java, but subject to change) will not call for a new parser tree to be made, nor will it attempt to parse and interpret the command. Rather, the stored action of the previously called command will be returned back up to the front end GUI for execution.

##Design Considerations

1. Having a Command object so that finding history and determining actions is easier. A ton of getters would help return different components of each Command
2. We decided to split CommandParser and add a ParseTree class. This way instead of CommandParser performing logic and feeding information back to the front end, its only responsibility is reading the ParseTree and passing it back to the front end. This makes its job simpler.
3. We decided to make it the back ends responsibility to handle new variables. We were deciding whether the front end or back end should handle creating and basically determining whether addVariable is an internal or external API method in the front end. Ultimately, since we are declaring variables in the editor and interpreting that in the backend, we decided the backend should handle it, thus making it a external method.

##Team Responsibilities

* Grayson
	* Grayson will focus on designing graphics, building the options and help menus, and helping Delia with overall GUI implementation.

* Delia
	* Delia will handle the front end and GUI implementation, setting the GUI objects to communicate with back end, designing text, backgrounds, buttons, and setting action commands.

* Robert
	* Robert will work with the back-end of the project, specifically dealing with how the Command object is interpreted and translated into a command for the GUI to execute on the Turtle cursor object. He will also handle interfacing the back-end with the front-end of the project.

* Ezra
	* Ezra will be working on the back-end component of the project with Robert. Specifically, he will be in charge of error handling and throwing errors back to the front end for the user to deal with.







