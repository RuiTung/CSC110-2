/*
* Program Name: Loopy
* Purpose: Use a set of methods to output an ascii image to the console
*		   and use a set of methods to calculate the surface area of a house
* Program Input: None
* Program Outputs: to the console - Ascii image and surface area of a house
*/

public class Loopy {
	public static void main(String[] args) {

		//passing parameters to drawShape method
		drawShape(1);
		drawShape(2);
		drawShape(3);
		System.out.println();
		
		//passing parameters to drawImage method
		drawImage(1);
		drawImage(2);
		drawImage(3);
		System.out.println();

		//get return value from rectangleArea method and print out to console
		System.out.println("rectangle: with lenght 7.0, width: 3.0\n\t area: " + rectangleArea(7.0, 3.0));
		System.out.println();
		
		//get return value from triangleArea method and print out to console
		System.out.println("triangle: with height 7.0, base: 6.0\n\t area: " + triangleArea(7.0, 6.0));
		System.out.println();
		
		//get return value from circleArea method and print out to console
		System.out.println("circle: with diameter 5.0\n\tarea: " + circleArea(5.0));
		
		////get return value from circumferenceCircle method and print out to console
		System.out.println("\tcircumference: " + circumferenceCircle(5.0));
		System.out.println();
		
		////get return value from cylinderSurfaceArea method and print out to console
		System.out.println("cylinder: with height 7.0, diameter: 5.0\n\tsurface area: " + cylinderSurfaceArea(7.0, 5.0));
		System.out.println();
		
		//get return value from recPrismSurfaceArea method and print out to console
		System.out.println("rectangular prism: with height 7.0, depth: 7.0, width: 3.0\n\tsurface area: " + rectPrismSurfaceArea(7.0, 7.0, 3.0));
		System.out.println();
		
		//get return value from triPrismSurfaceArea method and print out to console
		System.out.println("triagular prism: with height 7.0, width: 6.0\n\tsurface area: " + triPrismSurfaceArea(7.0, 6.0));
		System.out.println();
		
		//get return value from houseSurfaceArea method and print out to console
		System.out.println("house: with wall height 10.0, peek height: 14.0, width: 6.0, depth: 8.0\n\tsurface area: " + houseSurfaceArea(10.0, 14.0, 6.0, 8.0));
		
	}

	//drawShape method, use for loop to dictate times of ascii image output
	public static void drawShape(int size) {
		String x = "/";
		String y = "\\";
		String z = "*";

		for(int i = 1; i <= size+1; i++) {
			System.out.print(x);
		}
		
		for(int j = 1; j <= size+1; j++) {
			System.out.print(y);
		}
		
		System.out.println();

		System.out.print(x);
		for(int k = 1; k <= size*2; k++) {
			System.out.print(z);
		}
		System.out.print(x);
		System.out.println();

		for(int j = 1; j <= size+1; j++) {
			System.out.print(y);
		}
		for(int i = 1; i <= size+1; i++) {
			System.out.print(x);
		}
		System.out.println();
	}

	//drawImage method, use for loop to dictate times of calling drawShape method
	public static void drawImage(int largestShape) {
		
		for(int i = 1; i <= largestShape; i++) {
			drawShape(i);
		}
		
		for(int i = largestShape; i >= 1; i--) {
			drawShape(i);
		}
	}
	
	//calculate rectangle area and return the value
	public static double rectangleArea(double length, double width) {
		double rectangle = length * width;
		return rectangle;
	}
	
	//calculate triangle area and return the value
	public static double triangleArea(double height, double base) {
		double triangle= height * base / 2;
		return triangle;
	}
	
	//calculate circle area and return the value
	public static double circleArea(double diameter) {
		double area= Math.PI * Math.pow((diameter/2),2);
		return area;
	}
	
	////calculate circumference and return the value
	public static double circumferenceCircle(double diameter) {
		double circumference= Math.PI * diameter;
		return circumference;
	}
	
	//calculate cylinder Surface area and return the value
	public static double cylinderSurfaceArea(double height, double diameter) {
		double cylinder = rectangleArea(height, circumferenceCircle(diameter)) + 2 * circleArea(diameter);
		return cylinder;
	}
	
	//calculate rectangular prism surface area and return the value
	public static double rectPrismSurfaceArea(double height, double depth, double width) {
		double face1 = 2 * rectangleArea(height, width);
		double face2 = 2 * rectangleArea(height, depth);
		double face3 = 2 * rectangleArea(depth, width);
		double rectangle = face1 + face2 + face3;
		return rectangle;
	}
	
	//calculate triagular prism surface area and return the value
	public static double triPrismSurfaceArea(double height, double width) {
		double triangleHeight = Math.sqrt((Math.pow(width,2) - Math.pow((width/2),2)));
		double triangleArea = 2 * triangleArea(width, triangleHeight);
		double rectangularArea = 3 * rectangleArea(height, width);
		double triagular = triangleArea + rectangularArea; 
		return triagular;
	}
	
	//calculate house surface area and return the value
	public static double houseSurfaceArea(double wallHeight, double peekHeight, double width, double depth) {
		double roofHeight = peekHeight - wallHeight;
		double houseSide = (width * roofHeight / 2) + rectangleArea(wallHeight, width);
		double houseFront = rectangleArea(wallHeight, depth);
		double hypotenuse = Math.sqrt(Math.pow(roofHeight, 2) + Math.pow(width/2, 2));
		double roof = rectangleArea(hypotenuse, depth);
		double house = 2 * houseSide + 2 * houseFront + 2 * roof;
		return house;
	}
}