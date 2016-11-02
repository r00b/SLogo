package BackEndInternalAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GUIController.GUIDisplay;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author ezra
 */
public class ObservableComposite implements ObservableManager{
	private Double currentID;
	private double turtleCount;
	private Map<Double, ObservableProperties> myTurtles;
	private List<Double> activeTurtles;
	private DoubleProperty newTurtle;
	
	public ObservableComposite(GUIDisplay display) {

		myTurtles = new HashMap<Double, ObservableProperties>();
		activeTurtles = new ArrayList<Double>();
		newTurtle = new SimpleDoubleProperty(0);

		newTurtle.addListener((observable, oldValue, newValue) -> {
				ObservableProperties result = display.addTurtle((double) newValue); //returns a new ObservableProperties class
				myTurtles.put((double) newValue, result);
				turtleCount++;
				activeTurtles.add((double) newValue);
		});
		//Creates intial Turtle
		newTurtle.setValue(1.0);
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
		for (Double id : activeTurtles) {
			currentID = id;
			myTurtles.get(id).setXProperty(value);
		}
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
	}

	@Override
	public boolean getPathVisibleProperty() {
		return myTurtles.get(currentID).getImageVisibleProperty();
	}
	
	@Override
	public double calculateTotalDistance(double x, double y) {
		double answer = 0;
		for (Double id : activeTurtles) {
			currentID = id;
			answer = myTurtles.get(id).calculateTotalDistance(x, y);
		}
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
		return answer;
	}
	@Override
	public void setNewLineProperty(boolean value) {
		for (Double id : activeTurtles) {
			currentID = id;
			myTurtles.get(id).setNewLineProperty(value);
		}
	}

	public void setNewTurtle(double value){
		if(!myTurtles.containsKey(value)){
			newTurtle.set(value);
		}
	}

	@Override
	public void setClearScreenProperty(boolean value) {
		for (Double id : activeTurtles) {
			currentID = id;
			myTurtles.get(id).setClearScreenProperty(value);
		}
	}
	@Override
	public void setImageVisibleProperty(boolean value) {
		for (Double id : activeTurtles) {
			currentID = id;
			myTurtles.get(id).setImageVisibleProperty(value);
		}
	}
	@Override
	public double setRotateProperty(ParseTreeNode node, boolean isAbsolute, boolean sign) {
		double answer = 0;
		for (Double id : activeTurtles) {
			currentID = id;
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
	}
	@Override
	public double calculateDegrees(ParseTreeNode x, ParseTreeNode y) {
		double answer = 0;
		for (Double id : activeTurtles) {
			currentID = id;
			answer = myTurtles.get(id).calculateDegrees(x, y);
		}
		return answer;
	}

	public double getCurrentID() {
		return currentID;
	}
	public double getTurtleCount() {
		return turtleCount;
	}

	public double performTell(ParseTreeNode node) {
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
		return id;
	}
	
	@Override
	public double setXY(ParseTreeNode arg1, ParseTreeNode arg2) {
		double answer = 0;
		for (Double id : activeTurtles) {
			currentID = id;
			answer = myTurtles.get(id).setXY(arg1, arg2);
		}
		return answer;
	}
	
	public double performAsk(ParseTreeNode node) {
		List<Double> oldActiveTurtles = new ArrayList<Double>(activeTurtles);
		List<ParseTreeNode> tempActiveTurtles = node.getChild(0).getChildren();
		ParseTreeNode commandNode = node.getChild(1);
		activeTurtles.clear();
		for (ParseTreeNode child : tempActiveTurtles) {
			currentID = child.executeCommand(child);
			activeTurtles.add(currentID);
		}
		double answer = commandNode.executeCommand(commandNode);
		activeTurtles = oldActiveTurtles;
		return answer;
	}
	
	public double performAskWith(ParseTreeNode node) {
		List<Double> oldActiveTurtles = new ArrayList<Double>(activeTurtles);
		activeTurtles.clear();
		ParseTreeNode condition = node.getChild(0);
		ParseTreeNode command = node.getChild(1);
		for (Double turtle : myTurtles.keySet()) {
			Double evaluated = condition.executeCommand(condition);
			if (evaluated.equals(1.0)) {
				activeTurtles.add(turtle);
				currentID = turtle;
			}
		}
		double answer = command.executeCommand(command);
		activeTurtles = oldActiveTurtles;
		return answer;
	}
}
