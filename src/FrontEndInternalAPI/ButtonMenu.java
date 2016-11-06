package FrontEndInternalAPI;
import javafx.scene.shape.Rectangle;
import java.io.FileNotFoundException;

/**
 * @author Delia
 */
public interface ButtonMenu {

    /**
     *
     */
    void loadFile() throws FileNotFoundException;

    /**
     *
     */
    void saveFile();

    /**
     *
     */
    void addButtons();

    /**
     * @return
     */
    Rectangle getBackdrop();
}