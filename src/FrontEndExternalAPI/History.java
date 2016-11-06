package FrontEndExternalAPI;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.shape.Rectangle;

/**
 * @author Delia
 */
public interface History {

    /**
     *
     * @return
     */
    Rectangle getBackdrop();

    /**
     *
     * @param height
     */
    void bindNodes(ReadOnlyDoubleProperty height);

    /**
     *
     * @param text
     */
    void addCommand(String text);

    /**
     *
     * @param text
     */
    void callCommand(String text);

    /**
     *
     * @return
     */
    String getRedoCommand();    `
}