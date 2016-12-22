package BackEndInterpreter;

import BackEndInterface.RGB;
import GUIController.GUIDisplay;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @author ezra, Robert Steilberg
 */
public class DisplayProperties {
    private DoubleProperty penSize;
    private DoubleProperty penColor;
    private DoubleProperty imageIndex;
    private DoubleProperty backgroundImage;
    private ObjectProperty<RGB> paletteIndex;
    private DoubleProperty stamp;
    private DoubleProperty clearStamp;

    public DisplayProperties(GUIDisplay display) {
        imageIndex = new SimpleDoubleProperty(0);
        imageIndex.addListener((observable, oldValue, newValue) -> display.changeImage((Double) newValue));
        penColor = new SimpleDoubleProperty(0);
        penColor.addListener((observable, oldValue, newValue) -> display.changePenColor((Double) newValue));
        penSize = new SimpleDoubleProperty(0);
        penSize.addListener((observable, oldValue, newValue) -> display.setPenSize((Double) newValue));
        backgroundImage = new SimpleDoubleProperty(0);
//        backgroundImage.addListener((observable, oldValue, newValue) -> display.setBackgroundImage((Double) newValue));
        paletteIndex = new SimpleObjectProperty<RGB>();
        paletteIndex.addListener((observable, oldValue, newValue) -> display.changePaletteRGB(newValue));

        stamp = new SimpleDoubleProperty(0);
        stamp.addListener(((observable, oldValue, newValue) -> display.stamp(imageIndex.get())));
        clearStamp = new SimpleDoubleProperty(0);
        clearStamp.addListener(((observable, oldValue, newValue) -> display.clearStamps()));
    }

    public double getPenColor() {
        return penColor.get();
    }

    public void setPenColor(double value) {
        penColor.set(value);
    }

    public void setPenSize(double value) {
        penSize.set(value);
    }

    public double getImageIndex() {
        return imageIndex.get();
    }

    public void setImageIndex(double value) {
        imageIndex.set(value);
    }

    public void setBackgroundImage(double value) {
        backgroundImage.set(value);
    }

    public void setPallete(double r, double g, double b) {
        RGB palette = new RGB(r, g, b);
        paletteIndex = new SimpleObjectProperty<>(palette);
    }

    /**
     * Triggered when a stamp is made; increments number of stamps made
     *
     * @return the image index of the turtle used to make the stamp
     */
    public double makeStamp() {
        stamp.set(1);
        return imageIndex.get();
    }

    /**
     * Triggered when all stamps are cleared; resets number of stamps made
     *
     * @return the number of stamps cleared
     */
    public double clearStamps() {
        double result = stamp.get();
        clearStamp.set(1); // trigger front end action
        stamp.set(0); // reset the number of stamps made
        return result;
    }

}