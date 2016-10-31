package BackEndInternalAPI;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;



public class ObservableComposite implements ObservableManager{
	
	private double turtleCount;
	private Map<Double, ObservableProperties> myTurtles;
	private Set<Double> activeTurtles;
	private DoubleProperty penSize;
	private DoubleProperty penColor;
	//bind image????
	private DoubleProperty imageIndex;
	private DoubleProperty backgroundImage;
	private DoubleProperty paletteIndex;
	
	public ObservableComposite() {
		myTurtles = new HashMap<Double, ObservableProperties>();
		turtleCount = 1;
		activeTurtles = new HashSet<Double>();
		imageIndex = new SimpleDoubleProperty(0);
		imageIndex.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				//myDisplay.setImage();
			}
			
		});
		penColor = new SimpleDoubleProperty(0);
		penColor.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				//myDisplay.setPenColor()
			}
			
		});
		penSize = new SimpleDoubleProperty(0);
		penSize.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				//myDisplay.setPenSize()
			}
			
		});
		backgroundImage = new SimpleDoubleProperty(0);
		backgroundImage.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				//myDisplay.setBackgroundImage
			}
		});
		paletteIndex = new SimpleDoubleProperty(0);
		paletteIndex.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				//myDisplay.setPalette
			}
		});
	}
	@Override
	public boolean getNewLineProperty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getClearScreenProperty() {
		boolean answer = false;
		for (Double id : activeTurtles) {
			answer = myTurtles.get(id).getClearScreenProperty();
		}
		return answer;
	}

	@Override
	public boolean getImageVisibleProperty() {
		boolean answer = false;
		for (Double id : activeTurtles) {
			answer = myTurtles.get(id).getImageVisibleProperty();
		}
		return answer;	
	}

	@Override
	public double getRotateProperty() {
		double answer = 0.0;
		for (Double id : activeTurtles) {
			answer = myTurtles.get(id).getRotateProperty();
		}
		return answer;
	}

	@Override
	public double getXProperty() {
		double answer = 0.0;
		for (Double id : activeTurtles) {
			answer = myTurtles.get(id).getXProperty();
		}
		return answer;		
	}

	@Override
	public void setXProperty(double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getYProperty() {
		double answer = 0.0;
		for (Double id : activeTurtles) {
			answer = myTurtles.get(id).getYProperty();
		}
		return answer;		
	}

	@Override
	public void setYProperty(double value) {
		for (Double id : activeTurtles) {
			myTurtles.get(id).setYProperty(value);
		}
	}

	@Override
	public boolean getPathVisibleProperty() {
		boolean answer = false;
		for (Double id : activeTurtles) {
			myTurtles.get(id).getPathVisibleProperty();
		}
		return answer;
	}

	@Override
	public void addActiveTurtle(double id) {
		// TODO Auto-generated method stub
		if (!myTurtles.containsKey(id)) {
			addTurtle(id);
		}
		activeTurtles.add(id);
	}
	private void addTurtle(double id) {
		// TODO Auto-generated method stub
		turtleCount++;
		myTurtles.put(turtleCount, new ObservableProperties(null, null, 0));
	}
	@Override
	public double calculateTotalDistance(double x, double y) {
		double answer = 0;
		for (Double id : activeTurtles) {
			myTurtles.get(id).calculateTotalDistance(x, y);
		}
		return answer;
	}
	@Override
	public double calculateXDistance(ParseTreeNode hyp, boolean sign) {
		double answer = 0;
		for (Double id : activeTurtles) {
			double xDistance = myTurtles.get(id).calculateXDistance(hyp, sign);
			double currentPos = myTurtles.get(id).getXProperty();
			myTurtles.get(id).setXProperty(currentPos + xDistance);
			answer = xDistance;
		}
		return answer;
	}
	@Override
	public double calculateYDistance(ParseTreeNode hyp, boolean sign) {
		double answer = 0;
		for (Double id : activeTurtles) {
			double yDistance = myTurtles.get(id).calculateYDistance(hyp, sign);
			double currentPos = myTurtles.get(id).getYProperty();
			myTurtles.get(id).setXProperty(currentPos + yDistance);
			answer  = yDistance;
		}
		return answer;
	}
	@Override
	public void setNewLineProperty(boolean value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setClearScreenProperty(boolean value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setImageVisibleProperty(boolean value) {
		for (Double id : activeTurtles) {
			myTurtles.get(id).setImageVisibleProperty(value);
		}
	}
	@Override
	public double setRotateProperty(ParseTreeNode node, boolean isAbsolute, boolean sign) {
		double answer = 0;
		for (Double id : activeTurtles) {
			answer = myTurtles.get(id).setRotateProperty(node, isAbsolute, sign);
		}
		return answer;
	}
	@Override
	public void setPathVisibleProperty(boolean value) {
		for (Double id : activeTurtles) {
			myTurtles.get(id).setPathVisibleProperty(value);
		}
	}
	@Override
	public double calculateDegrees(ParseTreeNode x, ParseTreeNode y) {
		double answer = 0;
		for (Double id : activeTurtles) {
			myTurtles.get(id).calculateDegrees(x, y);
		}
		return answer;
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
