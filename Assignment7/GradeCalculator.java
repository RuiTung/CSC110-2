/*
* Program Name: GradeCalculator
* Purpose: Scan a txt format file, and analyze and arrange the content, 
* 				then output the required content to another file
* Program Input: grades.txt
* Program Outputs: report.txt
*/
import java.util.*;
import java.io.*;


public class GradeCalculator {
	
	//property for other methods' using
	static double[]  multiplier = new double[] {1.0, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.75, (15.0/35.0), (37.0/110.0)};
	static String[] level = new String[] {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "D", "F"};
	
	public static void main(String[] args) {
		if(args.length!= 2 || !args[0].equals("grades.txt") || !args[1].equals("report.txt")) {
			System.out.println("Wrong command line.");
			System.exit(-1);
		}
		
		File input = new File(args[0]);
		File output = new File(args[1]);
		try {
			Scanner scan = new Scanner(input);
			System.out.println("Please check 'Report.txt' to see the result.");
			int lines = entries(input);
			Csc110Student[] student = getStudents(scan, lines);
			double[] totals = total(input, lines);
			int mid = midterms(totals, lines);
			int lab = labs(totals, lines);
			int work = works(totals, lines);
			int finals = finalExam(totals, lines);
			
			// call sort method for storing value
			sort(student);
			String max = student[student.length-1].toString();
			String min = student[0].toString();
			String med = student[student.length / 2].toString();
			String maxFinal = student[student.length-1].toString();
			String minFinal = student[0].toString();
			
			PrintStream outputFile = new PrintStream(output);
			outputFile.println("Averages:");
			outputFile.println("Midterms\tLabs\tAssignments\tFinalExams");
			outputFile.println(mid+ "\t" + lab + "\t" + work + "\t" + finals);
			outputFile.println();
			
			outputFile.println("Maximum Final Grade: " + max);
			outputFile.println("Minimum Final Grade: " + min);
			
		} catch (FileNotFoundException e) {
			System.out.println("Your input file does not exist.");
			System.out.println("Please try again.");
		}
		
	}
	
	/*
	* PURPOSE: gets the number of line entries in a file
	* INPUT: File fIn, file with text
	* OUTPUT: returns number of lines in the file as an integer
	*/
	public static int entries(File fIn) {
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
	* PURPOSE: put all column elements into total array to become a 1D array
	* INPUT: File fIn, n
	* OUTPUT: returns total 1D array
	*/
	public static double[] total(File fIn, int n) {
		double[] array = new double [n];
		try {
			Scanner line = new Scanner(fIn);
			while(line.hasNextLine()) {
				line.nextLine();
				for(int i = 0; i < n;  i++) {
					double sum = 0;
					for(int j = 0; j < multiplier.length; j++) {
						sum +=  line.nextDouble() * multiplier[j];
					}
					array[i] = sum;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Can't scan the file.");
		}
		return array;
	}
	
	/*
	* PURPOSE: get midterm grade
	* INPUT: double[] total, int entries
	* OUTPUT: midterm grade
	*/
	public static int midterms(double[] total, int entries) {
		int midterms = (int) ((total[8] + total[9]) / entries);
		return midterms;
	}
	
	/*
	* PURPOSE: get labs grade
	* INPUT: double[] total, int entries
	* OUTPUT: labs grade
	*/
	public static int labs(double[] total, int entries) {
		int labs = (int) (total[1] / entries);
		return labs;
	}
	
	/*
	* PURPOSE: get assignment grade
	* INPUT: double[] total, int entries
	* OUTPUT: assignment grade
	*/
	public static int works(double[] total, int entries) {
		double sum = 0;
		int works = 0;
		for(int i = 1; i <= 7; i ++) {
			sum += total[i];
		}
		works = (int) (sum / entries);
		return works;
	}
	
	/*
	* PURPOSE: get final grade
	* INPUT: double[] total, int entries
	* OUTPUT: final grade
	*/
	public static int finalExam(double[] total, int entries) {
		int finalExam = (int) (total[10] / entries);
		return finalExam;
	}
	
	/*
	* PURPOSE: get assignment grade in total array
	* INPUT: double[] total
	* OUTPUT: assignment grade
	*/
	public static double assignment(double[] total) {
		double sum = 0;
		double assignments = 0;
		for(int i = 1; i < 8; i++) {
			sum += total[i];
		}
		assignments= sum / 7;
		return assignments;
	}
	
	/*
	* PURPOSE: creates an array of Csc110Student objects using data
	* from the input file. All fields of each object will have a value.
	* INPUT: Scanner s, access to the input data file and
	* int n, the number of entries in the input
	* OUTPUT: returns an array holding the Csc110Student objects
	*/
	public static Csc110Student[] getStudents(Scanner s, int n) {
		Csc110Student[] array = new Csc110Student[n];
		String[] mark = new String[12];
		try {
			while(s.hasNextLine()) {
				for(int i = 0; i < mark.length; i ++) { 
					mark[i] = s.next();
				}
				String stID = mark[0];
				
				double midterm1 = mark[9] * multiplier[9];
				double midterm2 = mark[10] * multiplier[10];
				double midterms = (midterm1 + midterm2) / 2;
				
				double labs = mark[1] * multiplier[1];
				
				double assignments = assignment(mark) * multiplier[2];
				
				double finalExam = mark[11] * multiplier.length-1;
			}
		} catch (Exception e) {
			System.out.println("Can't scan the file.");
		}
		return array;
	}
	
	/*
	* PURPOSE: sorts an array of Csc110Student objects
	* based on their finalGrade in ascending order
	* INPUT: Csc110Student[] students, the array of
	* Csc110Student objects to sort
	* OUTPUT: none
	*/
	public static void sort(Csc110Student[] student) {
		int size = student.length;
		Csc110Student temp = null;
		
		for(int i = 0; i < size; i ++) {
			for(int j = 0; j < size; j ++) {
				if(student[j].getFinalGrade() > student[j+1].getFinalGrade()) {
					temp = student[j];
					student[j] = student[j+1];
					student[j+1] = temp;
				}
			}
		}
	}

	/*
	* PURPOSE: gets the corresponding letter grades
	* for an array of integers
	* INPUT: int[] grades, array of grades as integers
	* OUTPUT: returns the letter grades in a String array
	*/
	public static String[] getLetterGrades(double[] total) {
		String[] gradeArray = new String[total.length];
		String gpa = null;
		for(int i = 0; i < total.length; i++) {
			if(total[i] > 89) {
				gpa = "A+";  
			} else if(total[i] > 84 && total[i] <= 89) {
				gpa = "A";
			} else if(total[i] > 79 && total[i] <= 84) {
				gpa = "A-";
			} else if(total[i] > 76 && total[i] <=79) {
				gpa = "B+";
			} else if(total[i] > 72 && total[i] <= 76) {
				gpa = "B";
			} else if(total[i] > 69 && total[i] <= 72) {
				gpa = "B-";
			} else if(total[i] > 64 && total[i] <= 69) {
				gpa = "C+";
			} else if(total[i] > 59 && total[i] <= 64) {
				gpa = "C";
			} else if(total[i] >49 && total[i] <= 59) {
				gpa = "D";
			} else {
				gpa = "F";
			}
			gradeArray[i] = gpa;
		}
		return gradeArray;
	}
}

