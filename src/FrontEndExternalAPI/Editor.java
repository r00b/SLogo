package FrontEndExternalAPI;

import javafx.scene.shape.Rectangle;

/**
 * @author Delia
 */
public interface Editor{

    /**
     * @return
     */
    Rectangle getBackdrop();

    /**
     *
     */
    void startNewCommand();

    /**
     *
     * @return
     */
    String getCurrentText();

    /**
     * @param str
     */
    void redoCommand(String str);
}