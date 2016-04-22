/*
* Program Name: Welcome
* Purpose: Use a set of methods to output an ascii image to the console
*		   and use a method to calculate the surface area of a cylinder of height 5 and diameter 4
* Program Input: None
* Program Outputs: to the console - Ascii image and surface area of a cylinder
*/


//import DecimalFormat class of java.text package
import java.text.DecimalFormat;

public class Welcome {

	/*
	 * Name: main method
	 * Purpose: concluds all methods and output to the console
	 * Input: None
	 * Output: Output ascii image and surface area of cylinder to the console
	 */
	public static void main(String[] args) {

		System.out.println("Welcome");
		System.out.println();

		// method call, output pig and frog
		printTotemPole();
		System.out.println();
		
		// method call, output surface area of cylinder
		calcSurfaceArea();
	}

	/* Name: printTotemPole method
	 * Purpose: print askii image of frog and pig
	 * Input: None
	 * Output: ascii image
	 */
	public static void printTotemPole(){
		waveLine();
		printFrog();
		
		waveLine();
		printPig();

		waveLine();
		printFrog();
		
		waveLine();
		printPig();
		
		waveLine();
		waveLine();
	}

	/* Name: waveLine method
	 * Purpose: print wave line
	 * Input: None
	 * Output: wave line
	 */
	public static void waveLine(){
		System.out.println("/~~~~~~\\");
	}

	/* Name: printPig method
	 * Purpose: print pig with a blank line
	 * Input: None
	 * Output: pig with a blank line
	 */
	public static void printPig(){
		System.out.println("  ^__^");
		System.out.println(" (0  0)");
		System.out.println("  (oo)");
		System.out.println(" (\")_(\")@");
		System.out.println();
	}

	/* Name: printFrog method
	 * Purpose: print frog with a blank line
	 * Input: None
	 * Output: Frog with a blank line
	 */
	public static void printFrog(){
		System.out.println("  @..@");
		System.out.println(" (----)");
		System.out.println("( >__< )");
		System.out.println("\"\"    \"\"");
		System.out.println();
	}

	/* Name: calcSurfaceArea method
	 * Purpose: calculate surface area of cylinder, then output the result
	 * Input: DecimalFormat class of java.text package
	 * Output: surface area of cylinder
	 */
	public static void calcSurfaceArea(){
		int height = 5;
		int diameter = 4;
		double circumference = Math.PI * diameter;
		double topArea = Math.PI * Math.pow((diameter/2),2);
		double wallsArea = circumference * height;
		double surfaceArea = 2 * topArea + wallsArea;
		System.out.println("Surface Area is: " + Double.parseDouble(new DecimalFormat("#.##").format(surfaceArea)));
	}
}