package FrontEndExternalAPI;

/**
 * @author Delia
 */
public interface GUIController {
//    public GUIController();

    public void init();

    public void getInitialParams();

    public void getCurrentCommand();

    public void passCurrentCommand();

    public void throwError();

    public void getErrors();

    public void storeOldCommand();

    public void returnAction();
}