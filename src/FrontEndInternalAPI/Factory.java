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

    Button makeButton(String text, ImageView img, double x, double y);

    Button makeClearButton(double x, double y);

    Button makeBigButton(String text, int x, int y);

    ImageView makeHelpButton(double x, double y);

    Text makeTitle(String text, int x, int y);

    Text makePopupText(String text, int x, int y, int fontsize);

    TextField makeTextField(String promptText, double prefWidth, int x, int y);

    Rectangle makeBackdrop(Paint border, int width, int height, int x, int y);

    Rectangle makeBlueBackdrop(int width, int height, int x, int y);

    ColorAdjust makeEffect(Color color);

    String getButtonFill();

    String getOverButton();
}
