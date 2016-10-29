package BackEndInternalAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;

public class ObservableComposite implements ObservableManager{
	
	private double turtleCount;
	private Map<Double, ObservableProperties> myTurtles;
	private Set<Double> activeTurtles;
	
	public ObservableComposite() {
		myTurtles = new HashMap<Double, ObservableProperties>();
		turtleCount = 1;
		activeTurtles = new HashSet<Double>();
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
		myTurtles.put(turtleCount, new ObservableProperties(null, null));
	}
	@Override
	public double calculateTotalDistance(double x2, double x1, double y2, double y1) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double calculateXDistance(double hyp) {
		// TODO Auto-generated method stub
		double answer = 0;
		for (Double id : activeTurtles) {
			double currentPos = myTurtles.get(id).getXProperty();
			double distance = myTurtles.get(id).calculateYDistance(hyp);
			myTurtles.get(id).setXProperty(currentPos + distance);
		}
		return answer;
	}
	@Override
	public double calculateYDistance(double hyp) {
		// TODO Auto-generated method stub
		return 0;
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
	public void setRotateProperty(double value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setPathVisibleProperty(boolean value) {
		for (Double id : activeTurtles) {
			myTurtles.get(id).setPathVisibleProperty(value);
		}
	}

}
