##Front end internal:
Currently, we have
```
package FrontEndInternalAPI;
 public interface ButtonMenu {
      public Button newButton(String text, int x, int y);
    public void addButtons();
}
```
This is the interface for the button menu that shows up in the IDE. We have made very small changes from the original
interface, which is as follows:
```
public interface ButtonMenu {
    public ButtonMenu();

    public void addButton();
}
```
We added a newButton() method after we started refactoring for duplicated button creation code.

We also have
```
package FrontEndInternalAPI;
 public interface Options {
      public void changeBackground();
    public void changePenColor();
    public void changeLanguage();
    public void changeSpriteImage();
}
```
This is our Options interface that determines settings for what the IDE and display will look like.
Originally, it looked like this:
```
public interface Options {
    public Options();

    public void changeBackgroundColor();

    public void changePenColor();

    public void changeLanguage();

    public void changeSpriteImage();
}
```
We haven't changed anything.

We have
```
package FrontEndInternalAPI;
 public interface RenderSprite {
      public void updateNodes();
    public void updateDisplayOptions();
    public void resetIDE();
}
```
Which is the interface for our display.
Originally, we had
```
public interface RenderSprite {
    public RenderSprite();

    public void updateNodes();

    public void updateDisplayOptions();

    public void resetIDE();
}
```
Which hasn't been changed either.

##Front end external:
We have a console interface which controls the console.
```
 package FrontEndExternalAPI;
  public interface Console {
       public void printResult();
 }
```
The original interface looked like this.
```
public interface Console {
    public Console();

    public void printResult();
}
```
Nothing has changed.
Our Editor interface controls the editor.
```
 package FrontEndExternalAPI;
  public interface Editor{
       public String getCurrentText();
 }
```
Originally, we had
```
public interface Editor{
    public Editor();

    public void enterPressed();
}
```
Which we changed from enterPressed() (we don't enter commands with the enter key as we originally thought) to getCurrentText()
because the GUIManager needs to get what the textArea contains.
We have a GUIController interface that controls our GUIManager.
```
 package FrontEndExternalAPI;
  public interface GUIController {
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
Originally, we had
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
Which is clearly the same.

We have a History interface for our History part of the IDE.
```
 package FrontEndExternalAPI;
  public interface History {
       public void addCommand(String text);
     public void callCommand(String text);
 }
```
Originally we had
```
public interface History {
    public History();

    public void addCommand();

    public void callCommand();
}
```
Where the methods didn't have any parameters that we ended up adding.

We have a variables interface for the list of user-defined variables.
```
 package FrontEndExternalAPI;
  public interface Variables {
       public void addVariable(String name, double value);
     public ArrayList<Integer> getAllVariables();
 }
```
Originally, we had
```
public interface Variables {
    public Variables ();

    public void addVariable();

    public ArrayList<Integer> getAllVariables(){
        return null;
    }
}
```
Which hasn't changed.

We also have a StartMenu that controls the splash screen.
```
 package FrontEndExternalAPI;
  public interface StartMenu {
       public void setParameters();
     public void initIDE();
 }
```
Originally, we had
```
public interface StartMenu {
    public StartMenu();

    public void setParameters();

    public void initIDE();
}
```
Which was the same.
