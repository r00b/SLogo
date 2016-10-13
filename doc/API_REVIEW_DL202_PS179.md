
#Part 1
### 1. What about your API/design is intended to be flexible?
Delia: Our API design is flexible because we use many helper classes and plan to implement a number of the new Java skills we learned,
such as enumerated types, generic variables, and binding.
Pratiksha: The API we have is flexible because the API is implemented through a bunch of interfaces that can be easily extended.

### 2. How is your API/design encapsulating your implementation decisions?
Pratiksha: The API is using controllers so the data passed in between the front end and the back end is very minimal.
Delia: Our design has the front end and the back end communicating through controller classes and very little direct passing of data.
This removes many dependencies between classes and means that we can easily target problems that arise in the passing of variables.
### 3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
Pratiksha: Wrong Commands entered in the Text Editor: Pop up that displays the error to the user
Delia: Errors that could occur in my part would be parsed out of the editor and passed to the CommandParser class. These would be
caught in the back end when the classes parse instructions. This would be reflected on the front end which marks errors in two places:
the Console and the display window. Users would know these are errors by their distinctive color and the fact that they can be clicked
to generate an error message about what went wrong.
### 4. Why do you think your API/design is good (also define what your measure of good is)?
Pratiksha: I think the API design is good because we have different classes that implement interfaces, which are a part of the larger
API.
Delia: Our API design is good because each class is small and responsible for a very specific purpose. This means that we can easily
find problems and add new features. My definition of good is that a project is extensible, flexible, and easy to debug.

#Part 2
### 1. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
Fd 50, right 20, left 12, fd 30 pen up fd 20
### 2. How do you think at least one of the "advanced" Java features will help you implement your design?
Binding: Binding the command entered in the front end with the string equivalent in the back end will automatically update the value
on the back end without sending it
One can also use binding with the entered command in the front end and the history display.

Regular Expression Parser:
This helps in parsing the commands as they are entered in the text editor.

### 3. What feature/design problem are you most excited to work on?
Delia: I am the most excited to be working on the graphics. Dealing with design issues and how, for example, errors show up to the
user should be very interesting.
Pratiksha: I am more excited in working with the binding features in java
### 4. What feature/design problem are you most worried about working on?
Delia: I am worried about adding new features especially if we have already created a design that is not so flexible for unforseen
changes.
Pratiksha: I am more worried about making the APIs extensible and thinking about the APIs while coding.





























































