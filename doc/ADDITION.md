# SLogo addition

Robert Steilberg | rhs16


## Estimation: before looking at the old code

> How long do you think it will take you to complete this new feature?

I estimate that it will take approximately an hour to complete this new feature. This feature should be relatively easy to implement on the back end, though I don't know how I will be able to finish implementing this feature without meeting with the front end team.

> How many files will you need to add or update? Why?

I estimate that I will need to update approximately four files. First, I will need to create two new files, each for `STAMP` and `CLEARSTAMPS`. Then, I will need to modify the class location properties file to include the locations of these new classes. Finally, I will need to update the observer classes to pass the results of these commands to the front end.

## Review: after completing the feature:

> How long did it take you to complete this new feature?

It took my approximately an hour to complete this new feature and an extra 30 minutes to consult with my fellow back end partner and finish documentation.

> How many files did you need to add or update? Why?

I ended up updating five files to add this feature. I had to create two new classes, Stamp.java and ClearStamps.java, to define the two new commands. I then needed to create observable properties in DisplayProperties.java that would be triggered when calling `STAMP` and `CLEARSTAMP`. I also had to define the class location (for reflection) or Stamp.java and ClearStamps.java in ClassLocations.properties. Finally, I had to add two new methods to GUIDisplay.java called `stamp()` and `clearStamps()` to actually manipulate the GUI and reflect the back end changes to the front end. I accidentally tried to add the observable commands to ObservableComposite.java, but this was incorrect so I reverted my changes.

> Did you get it completely right on the first try?

No. I had to consult with my fellow back end teammate because I did not work with display commands during my back end work with SLogo. At first, I tried defining the `stamp()` and `clearstamp()` methods only within Stamp.java and ClearStamp.java, and I didn't realize that methods needed to be created within GUIDisplay.java to reflect changes to the front end. I also inadvertently created the new commands as turtle commands rather than display commands, which was an incorrect classification on my part, requiring me to revert changes made to ObservableComposite.java.

## Analysis: what do you feel this exercise reveals about your project's design and documentation?

It was relatively easy to implement this new command on the back end, which indicates to me that our back end was well designed and easily extensible. Documentation helped me remember where I needed to define new classes to add new commands to the SLogo language. I think that the ease with which I implemented this command is a testament to the good design that was implemented in the back end of our SLogo project.

> Was it as good (or bad) as you remembered?

I believe that it was better than I remembered--I honestly did not expect to be able to easily remember and quickly extend the original SLogo functionality. It was relatively simple to create two new classes in the correct location (after a little help from my teammate) and once I remembered how DisplayProperties interfaced with the front end, it was not hard at all implementing this command. The flow of the program made sense to me and I was able to easily understand how the control passed from command class to observable to the front end.

> What could be improved?

The front end code could be improved--I was completely unable to figure out how the front end manipulates ImageViews or keeps track of turtle such that a stamp could be made with them. I was unable to consult with the front end team because they were no longer at Duke. I think the DisplayProperties class properly implements observable properties to trigger actions within the front end but the front end itself is not easily extensible and it was extremely challenging to figure out where exactly `stamp()` and `clearStamps()` should be called. It took several tries to realize that the proper class to call these methods was GUIDisplay.java.

> What would it have been like if you were not familiar with the code at all?

I feel like it would have been somewhat more difficult since I would not be aware of the nuances associated with creating new commands in our SLogo implementation. Luckily, I wrote documentation describing how to make new commands, and with this documentation I could have easily figured out how to properly add new commands and implement this feature had I not already known how to add new commands to our application. My previous experience with the back end of our SLogo application made it significantly easier to figure out how our observable classes interact with the command classes and the front end.