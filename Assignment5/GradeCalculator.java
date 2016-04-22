/*
* Author: Rui Ma
* StudentID: V00800795
* Program Name: GradeCalculator
* Purpose: Scan a txt format file, and analyze and arrange the content, 
* 			then output the required content to another file
* Program Input: grades.txt
* Program Outputs: gradeReport.txt
*/

import java.util.*;
import java.io.*;

public class GradeCalculator {
	
	//property for other methods' using
	static double[]  multiplier = new double[] {1.0, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.75, (15.0/35.0), (37.0/110.0)};
	static String[] level = new String[] {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "D", "F"};
	
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		Scanner fileScan = null;

		int lines = 0;	
		
		//loop body for checking file name
		while(fileScan == null) {
			try {
				System.out.println("Enter the name of your grade file: ");
				String fileName = userInput.next();
				File file = new File(fileName);
				lines = getNumEntries(file);
				fileScan = new Scanner(file);
				System.out.println("Calculating grades and generating report...");
				System.out.println("Check current directory for gradeReport.txt");
			} catch (FileNotFoundException e) {
				System.out.println("Your input file does not exist");
				System.out.println("Please enter again");
			}		
		}
			try{
				PrintStream output = new PrintStream(new File("gradeReport.txt"));
				int[] array = totals(fileScan,lines);
				int max = max(array);
				int min = min(array);
				int average = avg(array);
				String[] grade = getLetterGrades(array);
				int[] countGrade = countGrades(grade);
				
				//output maximum, minimum and average grade to file
				output.println("Maximum Grade:\t" + max);
				output.println("Minimum Grade:\t" + min);
				output.println("Average Grade: \t" + average);
				output.println();
				output.println();
				
				//output grades level and numbers to file
				output.println("Letter Grade\tCount");
				
				//property of level using
				for(int i = 0; i < level.length; i++) {
					output.println(level[i] + ":\t\t\t" + countGrade[i]);
				}
				output.println();
				output.println();
				
				//output grades level and percentage to file
				output.println("Letter Grade\tPercentage");
				for(int i = 0; i < array.length; i++) {
					output.println(grade[i] + "\t\t\t" + array[i]);
				}
				
			} catch (FileNotFoundException e) {
				System.out.println("Can't writing file named gradeReport.txt");
			}
	}
	
	/*
	* PURPOSE: gets the number of line entries in a file
	* INPUT: File fIn, file with text
	* OUTPUT: returns number of lines in the file as an integer
	*/
	public static int getNumEntries(File fIn) {
		int lines = 0;
		try {
			Scanner line = new Scanner(fIn);
			while(line.hasNextLine()) {
				line.nextLine();
				lines += 1;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Can't scan the file.");
		}
		return lines;
	}
	
	/*
	* PURPOSE: prints an array of integers to the console
	* INPUT: int[] values, array of integers
	* OUTPUT: none
	*/
	public static void print(int[] values) {
		for(int i = 0; i < values.length; i++) {
			System.out.println(values[i] + " ");
		}
	}

	public static int[] totals(Scanner file, int lines) {
		int [] array = new int [lines];
		for(int i = 0; i < lines; i++) {
			double sum = 0;
			
			//property of mutiplier using
			for(int j = 0; j < multiplier.length; j++) {
				sum += file.nextDouble() * multiplier[j]; 
			}
			//cast sum into an int type and assign it to an array
			array[i] = (int) sum;
		}
		return array;
	}
	
	/*
	* PURPOSE: identifies the max value of an array of integers
	* INPUT: int[] values, array of integers
	* OUTPUT: returns the max value as an integer
	*/
	public static int max(int[] values) {
		int maxNum = values[0];
		for(int i = 0; i < values.length; i++) {
			int currentNum = values[i];
			if(currentNum > maxNum) {
				maxNum = currentNum;
			}
		}
		return maxNum;
	}
	
	/*
	* PURPOSE: identifies the min value in an array of integers
	* INPUT: int[] values, array of integers
	* OUTPUT: returns the min value as an integer
	*/
	public static int min(int[] values) {
		int minNum = values[0];
		for(int i = 0; i < values.length; i++) {
			int currentNum = values[i];
			if(currentNum < minNum) {
				minNum = currentNum;
			}
		}
		return minNum;
	}
	
	/*
	* PURPOSE: calculates the average of an array of integers
	* INPUT: int[] values, array of integers
	* OUTPUT: returns the average as an integer
	*/
	public static int avg(int[] values) {
		int sum = 0;
		int average = 0;
		for(int i = 0; i < values.length; i++) {
			sum += values[i];
		}
		average = sum/values.length;
		return average;
	}
	
	/*
	* PURPOSE: gets the corresponding letter grades
	* for an array of integers
	* INPUT: int[] grades, array of grades as integers
	* OUTPUT: returns the letter grades in a String array
	*/
	public static String[] getLetterGrades(int[] grades) {
		String[] gradeArray = new String[grades.length];
		String gpa = null;
		for(int i = 0; i < grades.length; i++) {
			if(grades[i] > 89) {
				gpa = "A+";  
			} else if(grades[i] > 84 && grades[i] <= 89) {
				gpa = "A";
			} else if(grades[i] > 79 && grades[i] <= 84) {
				gpa = "A-";
			} else if(grades[i] > 76 && grades[i] <=79) {
				gpa = "B+";
			} else if(grades[i] > 72 && grades[i] <= 76) {
				gpa = "B";
			} else if(grades[i] > 69 && grades[i] <= 72) {
				gpa = "B-";
			} else if(grades[i] > 64 && grades[i] <= 69) {
				gpa = "C+";
			} else if(grades[i] > 59 && grades[i] <= 64) {
				gpa = "C";
			} else if(grades[i] >49 && grades[i] <= 59) {
				gpa = "D";
			} else {
				gpa = "F";
			}
			gradeArray[i] = gpa;
		}
		return gradeArray;
	}
	
	/*
	* PURPOSE: counts the number grades matching each letter grade
	* in the array of Strings passed in as a parameter.
	* The counts will be stored in an array of 10 integers where,
	* index 0 holds the number of "A+"'s in grades array
	* index 1 holds the number of "A"'s in grades array
	* index 2 holds the number of "A-"'s in grades array
	* index 3 holds the number of "B+"'s in grades array
	* index 4 holds the number of "B"'s in grades array
	* index 5 holds the number of "B-"'s in grades array
	* index 6 holds the number of "C+"'s in grades array
	* index 7 holds the number of "C"'s in grades array
	* index 8 holds the number of "D"'s in grades array
	* index 9 holds the number of "F"'s in grades array
	* INPUT: int[] grades, array of grade percentages as integers
	* OUTPUT: returns an integer array holding the letter grade counts
	*/
	public static int[] countGrades(String[] grades) {
		int[] countGrade = new int [10];
		for(int i = 0; i < grades.length; i++) {
			if(grades[i].equals("A+")) {
				countGrade[0] += 1;
			} else if(grades[i].equals("A")) {
				countGrade[1] += 1;
			} else if(grades[i].equals("A-")) {
				countGrade[2] += 1;
			} else if(grades[i].equals("B+")) {
				countGrade[3] += 1;
			} else if(grades[i].equals("B")) {
				countGrade[4] += 1;
			} else if(grades[i].equals("B-")) {
				countGrade[5] += 1;
			} else if(grades[i].equals("C+")) {
				countGrade[6] += 1;
			} else if(grades[i].equals("C")) {
				countGrade[7] += 1;
			} else if(grades[i].equals("D")) {
				countGrade[8] += 1;
			} else {
				countGrade[9] += 1;
			}
		}
		return countGrade;
	}
}