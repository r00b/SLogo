package FrontEndInternalAPI;

import BackEndExternalAPI.RGB;
import BackEndInternalAPI.ObservableProperties;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.paint.Paint;

/**
 * Created by Delia on 11/5/2016.
 */
public interface Display {
    /**
     *
     * @param newID
     * @return
     */
    ObservableProperties addTurtle(double newID);

    /**
     * @param width
     */
    void bindNodes(ReadOnlyDoubleProperty width);

    /**
     *
     * @param x
     * @param y
     * @param id
     */
    void moveTurtle(double x, double y, double id);

    /**
     *
     * @param id
     */
    void clearScreen(double id);

    /**
     *
     */
    void resetIDE();

    /**
     *
     * @param newValue
     */
    void changePenColor(Double newValue);

    /**
     *
     * @param newValue
     */
    void setPenSize(Double newValue);

    /**
     *
     * @param newValue
     */
    void changeImage(Double newValue);

    /**
     *
     * @param newValue
     */
    void changePalette(Double newValue);

    /**
     *
     * @param newValue
     */
    void changePaletteRGB(RGB newValue);

    /**
     *
     * @param isVisible
     * @param newID
     */
    void setVisibility(boolean isVisible, double newID);

    /**
     *
     * @return
     */
    String getTurtleStr();

    /**
     *
     * @return
     */
    Paint getPenColor();

    /**
     *
     */
    void updateDisplayOptions();

}
