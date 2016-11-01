package BackEndInternalAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;



public class ObservableComposite implements ObservableManager{
	private Double currentID;
	private double turtleCount;
	private Map<Double, ObservableProperties> myTurtles;
	private List<Double> activeTurtles;
	private DoubleProperty penSize;
	private DoubleProperty penColor;
	private DoubleProperty imageIndex;
	private DoubleProperty backgroundImage;
	private DoubleProperty paletteIndex;
	private DoubleProperty newTurtle;
	
	public ObservableComposite(ObservableProperties first) {
		Double val = 1.0;
		currentID = val;
		myTurtles = new HashMap<Double, ObservableProperties>();
		activeTurtles = new ArrayList<Double>();
		myTurtles.put(val, first);
		activeTurtles.add(val);
		turtleCount = 1;
		//activeTurtles = new HashSet<Double>();
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
		newTurtle = new SimpleDoubleProperty(0);
		newTurtle.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				//myDisplay.createNewTurtle(newValue) returns a new ObservableProperties class
				//myTurtles.add(result)
				turtleCount++;
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
			currentID = id;
			answer = myTurtles.get(id).getClearScreenProperty();
		}
		currentID = activeTurtles.get(0);
		return answer;
	}

	@Override
	public boolean getImageVisibleProperty() {
		return myTurtles.get(currentID).getImageVisibleProperty();
	}

	@Override
	public double getRotateProperty() {
		return myTurtles.get(currentID).getRotateProperty();	
	}

	@Override
	public double getXProperty() {
		return myTurtles.get(currentID).getXProperty();	
	}

	@Override
	public void setXProperty(double value) {
		// TODO Auto-generated method stub
		for (Double id : activeTurtles) {
			currentID = id;
			myTurtles.get(id).setXProperty(value);
		}
		currentID = activeTurtles.get(0);
	}

	@Override
	public double getYProperty() {
		return myTurtles.get(currentID).getYProperty();	
	
	}

	@Override
	public void setYProperty(double value) {
		for (Double id : activeTurtles) {
			currentID = id;
			myTurtles.get(id).setYProperty(value);
		}
		currentID = activeTurtles.get(0);
	}

	@Override
	public boolean getPathVisibleProperty() {
		return myTurtles.get(currentID).getImageVisibleProperty();
	}

	
	public double performTell(ParseTreeNode node) {
		// TODO Auto-generated method stub
		activeTurtles.clear();
		double id = 1.0;
		for (ParseTreeNode turtle : node.getChild(0).getChildren()) {
			id  = turtle.executeCommand(turtle);
			if (!myTurtles.containsKey(id)) {
				newTurtle.set(id);
			}
			if (!activeTurtles.contains(id)) {
				activeTurtles.add(id);
			}
		}
		currentID = activeTurtles.get(0);
		return id;
	}
	
	@Override
	public double calculateTotalDistance(double x, double y) {
		double answer = 0;
		for (Double id : activeTurtles) {
			currentID = id;
			answer = myTurtles.get(id).calculateTotalDistance(x, y);
		}
		currentID = activeTurtles.get(0);
		return answer;
	}
	@Override
	public double calculateXDistance(ParseTreeNode hyp, boolean sign) {
		double answer = 0;
		for (Double id : activeTurtles) {
			currentID = id;
			double xDistance = myTurtles.get(id).calculateXDistance(hyp, sign);
			double currentPos = myTurtles.get(id).getXProperty();
			myTurtles.get(id).setXProperty(currentPos + xDistance);
			answer = xDistance;
		}
		currentID = activeTurtles.get(0);
		return answer;
	}
	@Override
	public double calculateYDistance(ParseTreeNode hyp, boolean sign) {
		double answer = 0;
		for (Double id : activeTurtles) {
			currentID = id;
			double yDistance = myTurtles.get(id).calculateYDistance(hyp, sign);
			double currentPos = myTurtles.get(id).getYProperty();
			myTurtles.get(id).setYProperty(currentPos + yDistance);
			answer  = yDistance;
		}
		currentID = activeTurtles.get(0);
		return answer;
	}
	@Override
	public void setNewLineProperty(boolean value) {
		// TODO Auto-generated method stub
		for (Double id : activeTurtles) {
			currentID = id;
			myTurtles.get(id).setNewLineProperty(value);
		}
		currentID = activeTurtles.get(0);
	}
	@Override
	public void setClearScreenProperty(boolean value) {
		// TODO Auto-generated method stub
		for (Double id : activeTurtles) {
			currentID = id;
			myTurtles.get(id).setClearScreenProperty(value);
		}
		currentID = activeTurtles.get(0);
	}
	@Override
	public void setImageVisibleProperty(boolean value) {
		for (Double id : activeTurtles) {
			currentID = id;
			myTurtles.get(id).setImageVisibleProperty(value);
		}
		currentID = activeTurtles.get(0);
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
			currentID = id;
			myTurtles.get(id).setPathVisibleProperty(value);
		}
		currentID = activeTurtles.get(0);
	}
	@Override
	public double calculateDegrees(ParseTreeNode x, ParseTreeNode y) {
		double answer = 0;
		for (Double id : activeTurtles) {
			currentID = id;
			answer = myTurtles.get(id).calculateDegrees(x, y);
		}
		currentID = activeTurtles.get(0);
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
	public double getCurrentID() {
		return currentID;
	}
	public double getTurtleCount() {
		return turtleCount;
	}

	public double performAsk(ParseTreeNode node) {
		// TODO Auto-generated method stub
		List<Double> oldActiveTurtles = new ArrayList<Double>(activeTurtles);
		List<ParseTreeNode> tempActiveTurtles = node.getChild(0).getChildren();
		ParseTreeNode commandNode = node.getChild(1);
		activeTurtles.clear();
		for (ParseTreeNode child : tempActiveTurtles) {
			activeTurtles.add(child.executeCommand(child));
		}
		currentID = activeTurtles.get(0);
		double answer = commandNode.executeCommand(commandNode);
		activeTurtles = oldActiveTurtles;
		currentID = activeTurtles.get(0);
		return answer;
	}
	public double performAskWith(ParseTreeNode node) {
		//wont work if there is a tell in a askwith 
		List<Double> oldActiveTurtles = new ArrayList<Double>(activeTurtles);
		activeTurtles.clear();
		ParseTreeNode condition = node.getChild(0);
		ParseTreeNode command = node.getChild(1);
		for (Double turtle : myTurtles.keySet()) {
			Double evaluated = condition.executeCommand(condition);
			if (evaluated.equals(1.0)) {
				activeTurtles.add(turtle);
			}
		}
		currentID = activeTurtles.get(0);
		double answer = command.executeCommand(command);
		activeTurtles = oldActiveTurtles;
		currentID = activeTurtles.get(0);
		return answer;
		
	}
}
