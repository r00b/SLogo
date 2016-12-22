# CellSociety

### Robert Steilberg, Delia Li, Ezra Lieblich, Grayson Wise

#### Overview

SLogo, or Simple Logo, is a simplified version of the Logo programming language. Logo is a computer language designed to teach programming to children. It is a user-friendly, interpreted language, designed with a "low floor, high ceiling"; in other words, the designers of Logo intended for the language to allow novice programmers to get started with quickly writing programs but also wanted the language to be powerful and extensive for more advanced users.

I built an IDE for SLogo with Java in a team of four. I worked on the back end team and focused specifically on building the parse tree that represented each SLogo command issued from the UI. Through the parse tree, SLogo commands are decomposed into irreducible components and then executed to produce a cumulative result that is reflected in the UI. My other roles included robust error-checking and integration with the front end UI. The back end also supports complex SLogo functionalities such as user-defined methods, recursive calls, and variable scoping.

##### Instructions

The IDE initializes with a main menu for choosing initial settings. Then, a new instance of the SLogo IDE is created in which users can manipulate a turtle graphic via commands issued through the IDE's editor. Commands can be loaded in from or saved to external files before running in the editor. Previously executed commands can also be reissued via the history pane. Results from command execution are printed to the console pane. Variables can be stored and manipulaed via commands or the variable pane. IDE preferences can be saved and loaded via the menu bar. Multiple turtles can be created and commanded with various commands. Finally, a help menu is available where users can access information about the SLogo programming language.

##### Roles
* Robert: core back end engine, building parse tree, executing parse tree, interfacing with GUI, commands, error checking, refactoring the back end, saving files from the front end GUI
* Ezra: core back end engine, building parse tree, executing parse tree, interfacing with GUI, commands, reflecting turtle changes in the front end, all turtle and display commands, interfacing with the front end GUI, refactoring
* Delia: front end GUI, interfacing with the back end
* Grayson: front end GUI, interfacing with the back end

##### Design

Our project is divided into 11 packages. The BackEndCommands package contains all of the Command objects that represent Logo commands. The BackEndInterface contains classes that the front end will interact with when using the back end. The BackEndInterpreter package contains all the classes used to build and execute a parse tree made of inputted Logo commands. The Base package is a front end package used for creating JavaFX nodes and other JavaFX elements. The FrontEndExternalAPI, FrontEndInternalAPI, GUI, and GUIController packages all contain classes for creating the front end that interfaces with the back end. Specifically, the GUI package contains classes dealing with help menus. The GUIController package is the overall package that handles the GUI and its initialization. The front end and back end interface work together through the BackEndInterface package that has the CommandParser class. The CommandParser class is initialized once for each workspace and will subsequently accept Logo commands to interpret and reflect back to the front end. The only data exchanged between the front and back end is done through the executeCommands() method of CommandParser within the BackEndInterface package, and through observable properties that are initialized with CommandParser and used to reflect turtle and display changes between the front and back end. Finally, the GUI front end classes depend on the images within the Images package, and the BackEndInterpreter package depends on the properties files in the resources package that define regular expression definitions, class locations, or other settings.

##### Implementing new commands

To add a new command to the project, a new class for the command must be created that implements the overall Command class (or one of the sub-classes like ControlCommand or TurtleCommand, depending on the type of the new command). Then, it is only necessary to store the path to that class in the ClassLocations.properties properties file so that reflection will know exactly where the new command class is located when it is to be called. More complex commands, like those that interrupt the control flow of the program, may necessitate further manipulation of the ParseTreeBuilder class, but the actions associated with the new command need only be coded in the executeCommand() method of the new command class for the tree to correctly build and execute a parse tree using those commands. The number of arguments that the command expects should also be hard-coded in the new command class. As aforementioned, adding a new front end feature to the project is very easy through the standardization of styles and nodes found in places such as the NodeFactory.java class and various other superclasses like the OptionsMenu.

##### Known bugs
1.  The variable tableview does not clear properly. This happens when the "clear" button is pressed but more variables are defined. Previously cleared variables reappear in the chart.
2. Turtle path sometimes does not point perfectly at turtle if it goes off the screen. Only applies to straight paths that are long in distance. We think this is because the edge checking does not account for linear intersections of the path with the edge of the display.
3. Display mappings are not updated properly when the front end changes the pen color or background color. This is because the front end allows to change to any color whereas the backend only maps to a limited amount of colors.
