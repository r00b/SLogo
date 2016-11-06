package FrontEndExternalAPI;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.shape.Rectangle;

/**
 * @author Delia
 */
public interface Console {

    /**
     *
     * @param text
     */
    void addConsole(String text);

    /**
     *
     * @param width
     * @param height
     */
    void bindNodes(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height);

    /**
     *
     * @return
     */
    Rectangle getBackdrop();
}