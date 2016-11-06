package FrontEndInternalAPI;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by Delia on 11/5/2016.
 */
public interface Factory {

    /**
     *
     * @param text
     * @param img
     * @param x
     * @param y
     * @return
     */
    Button makeButton(String text, ImageView img, double x, double y);

    /**
     *
     * @param x
     * @param y
     * @return
     */
    Button makeClearButton(double x, double y);

    /**
     *
     * @param text
     * @param x
     * @param y
     * @return
     */
    Button makeBigButton(String text, int x, int y);

    /**
     *
     * @param x
     * @param y
     * @return
     */
    ImageView makeHelpButton(double x, double y);

    /**
     *
     * @param text
     * @param x
     * @param y
     * @return
     */
    Text makeTitle(String text, int x, int y);

    /**
     *
     * @param text
     * @param x
     * @param y
     * @param fontsize
     * @return
     */
    Text makePopupText(String text, int x, int y, int fontsize);

    /**
     *
     * @param promptText
     * @param prefWidth
     * @param x
     * @param y
     * @return
     */
    TextField makeTextField(String promptText, double prefWidth, int x, int y);

    /**
     *
     * @param border
     * @param width
     * @param height
     * @param x
     * @param y
     * @return
     */
    Rectangle makeBackdrop(Paint border, int width, int height, int x, int y);

    /**
     *
     * @param width
     * @param height
     * @param x
     * @param y
     * @return
     */
    Rectangle makeBlueBackdrop(int width, int height, int x, int y);

    /**
     *
     * @param color
     * @return
     */
    ColorAdjust makeEffect(Color color);

    /**
     *
     * @return
     */
    String getButtonFill();

    /**
     *
     * @return
     */
    String getOverButton();
}
