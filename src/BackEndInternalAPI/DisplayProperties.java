package BackEndInternalAPI;

import GUIController.GUIDisplay;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class DisplayProperties {
	private DoubleProperty penSize;
	private DoubleProperty penColor;
	private DoubleProperty imageIndex;
	private DoubleProperty backgroundImage;
	private DoubleProperty paletteIndex;
	
	public DisplayProperties(GUIDisplay display) {
		imageIndex = new SimpleDoubleProperty(0);
		imageIndex.addListener((observable, oldValue, newValue) -> display.changeImage((Double) newValue));
		penColor = new SimpleDoubleProperty(0);
		penColor.addListener((observable, oldValue, newValue) -> display.changePenColor((Double) newValue));
		penSize = new SimpleDoubleProperty(0);
		penSize.addListener((observable, oldValue, newValue) -> display.setPenSize((Double) newValue));
		backgroundImage = new SimpleDoubleProperty(0);
		backgroundImage.addListener((observable, oldValue, newValue) -> display.setBackgroundImage((Double)newValue));
		paletteIndex = new SimpleDoubleProperty(0);
		paletteIndex.addListener((observable, oldValue, newValue) -> display.changePalette((Double) newValue));
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
}
