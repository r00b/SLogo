package BackEndExternalAPI;

public class RGB {
	private static double red;
	private static double green;
	private static double blue;
	
	public RGB(double r, double g, double b) {
		red = checkBounds(r);
		green = checkBounds(g);
		blue = checkBounds(b);
	}
	
	public double getRed() {
		return red;
	}

	public double getGreen() {
		return green;
	}

	public double getBlue() {
		return blue;
	}
	
	private double checkBounds(double value) {
		double answer = value;
		if (answer < 0 || answer > 256) {
			//default value if it is out of bounds
			answer = 128;
		}
		return answer;
	}
}
