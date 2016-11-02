package BackEndExternalAPI;

public class RGB {
	private static double red;
	private static double green;
	private static double blue;
	
	public RGB(double r, double g, double b) {
		
		red = r;
		green = g;
		blue = b;
	}
	
	public static double getRed() {
		return red;
	}

	public static double getGreen() {
		return green;
	}

	public static double getBlue() {
		return blue;
	}

}
