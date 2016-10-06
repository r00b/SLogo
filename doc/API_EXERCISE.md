#SLogo API Design Exercise

Robert Steilberg | rhs16
Ezra Lieblich | eml36
Grayson Wise | gkw
Delia Li | dl202

## API Critique

We decided to use team seven's CellSociety project for our analysis.



###Simulation API
####Internal
Regarding the Cell class, we decided that all of its methods would be in the internal API, with the exception of the the public static final cell and the method isValid(), which should not be in the API at all. For the State class, all of its methods are in the internal API (State(), String(), equals(), hashCode()). We also decided for the Neighborhood class that all of its methods would be a part of the internal API since they don't appear to interact with the frontend (getNeighbors(), addNeighbors(), removeNeighbors()). This is also true for the NeighborHood definer class and its method getPotentialNeighbors(), along with its extensions. All of the Simulation extensions and the getNeighborhoodDefiner() method also belong to the internal Simulation API.
####External
For the ColorMap class, the getColor() method is a component of the external API because it interacts with the visualization API.

###Configuration API
####Internal
The XMLDataFactory class and its getData() method are a part of the internal configuration API because they deal with reading in an XML file and parsing its data so that it can be directed to other components of the program. The XMLParserException class is also internal because it deals with errors reading in XML files.
####External
For the DataInput class, its lone method getInputData() is external because it passes the logic from the input (such as an XML file) to some other component of the program. All of the simulation XML parser classes are external because they specifically take in data and interact with the backend to initialize the parameters for a particular simulation. Each simulation XML parser contains unique methods that are specifically designed to fit the specifications of each respective simulation.

###Visualization API
####Internal
We decided that the entire ui_components package is a part of the internal API. Its only purpose is to create JavaFX nodes to render on the screen; there is no algorithmic or backend logic implemented in this package. This is also true of the view package. We weren't sure what the functionality was of the View and StructureView classes, but they seem to be solely associated with the internal visualization API since they deal with rendering different grid cell shapes.
####External
We decided that the Structure superclass and all of its subclasses were a part of the external visualization API because they define the actual grid cell shapes that are displayed to the user. Furthermore, the back end interacts with the Structure classes in order to calculate new neighbors. However, we decided that some methods implemented in the Structure classes are also a part of the Simulation external API because they deal with the configuration of the simulation. Examples include calculateNeighborsForCells(), setNeighborhoods(), getWidth(), and getHeight(), which are not a part of the external visualizaiton API.
