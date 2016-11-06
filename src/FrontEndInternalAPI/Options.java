package FrontEndInternalAPI;

import javafx.scene.Parent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @author Delia
 */
public interface Options {
    /**
     *
     * @return
     */
    Parent setUpWindow();

    /**
     *
     */
    void addNodes();

    /**
     *
     */
    void setParameters();

    /**
     *
     */
    void setBackgroundString();

    /**
     *
     */
    void setTurtleString();

    String getTurtleString();

    /**
     *
     */
    void addLineStylePicker();

    void changePenColor();

    /**
     *
     * @param defaultColor
     * @param x
     * @param y
     * @return
     */
    ColorPicker generateColorPicker(Color defaultColor, int x, int y);

    /**
     *
     */
    void changeBackground();

    /**
     *
     */
    void changeSpriteImage();

    /**
     *
     * @param text
     * @param x
     * @param y
     * @return
     */
    Label generateLabel(String text, int x, int y);

    /**
     *
     */
    void changeLanguage();

    /**
     *
     * @return
     */
    ColorPicker getPenColor();

    /**
     *
     * @return
     */
    Pane getStartWindow();

    /**
     *
     * @return
     */
    ComboBox<String> getBackgroundBox();

    /**
     *
     * @return
     */
    ComboBox<String> getTurtleBox();

    /**
     *
     */
    ComboBox<Line> getLineBox();

    /**
     *
     * @return
     */
    ComboBox<String> getLanguageBox();

    /**
     *
     * @return
     */
    Stage getStage();

    /**
     *
     * @return
     */
    void addTitle();

    /**
     *
     * @return
     */
    void addRectangle();

    /**
     *
     * @return
     */
    void addLaunchButton();

    /**
     *
     * @return
     */
    void initIDE(String background, String turtle);
}