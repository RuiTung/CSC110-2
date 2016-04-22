// Csc110Student 
// contains instance data (fields) associated with student's term grades,
// where each field is the student's percent they received 
// in the given component of the course
// EXAMPLE -- if a student received 4/5 on their lab component
// 	of the course, 80.0 will be stored in the the labs field
//
// provides instance methods for:
//  accessing and changing instance data (fields)
//  calculating and setting the final grade
//  creating and returning a String representation of the student's grades
//
public class Csc110Student {

	// private data fields
	private String stID;
	private double midterms;
	private double labs;
	private double assignments;
	private double finalExam;
	private int finalGrade;
	private String letterGrade;
	
	// purpose: non-default constructor, initializes the stId, midterms, labs, assignments
	//			and final exam grades to the percentages passed in.
	//
	// input: 	stId as String and midterms, labs, assignments and finalExam percents as doubles
	//
	public Csc110Student(String stId, double midterms, double labs, double assignments, double finalExam) {
		this.stID = stId;
	 	this.midterms = midterms;
		this.labs = labs;
		this.assignments = assignments;
		this.finalExam = finalExam;	
	}
	
	/************** INSTANCE METHODS **************/

	// purpose: gets the midterm percent
	// input: 	none
	// output:	the student's midterm percent
	//
	public double getMidterms() {
		return this.midterms;
	}

	// purpose: sets the midterm percent
	// input: 	the student's midterm percent
	// output:	none
	//
	public void setMidterms(double midterms) {
		this.midterms = midterms;
	}
	
	// purpose: gets the labs percent
	// input: 	none
	// output:	the student's lab percent
	//	
	public double getLabs() {
		return this.labs;
	}
	
	// purpose: sets the labs percent
	// input: 	the student's lab percent
	// output:	none
	//	
	public void setLabs(double labs) {
		this.labs = labs;
	}
	
	// purpose: gets the assignments percent
	// input: 	none
	// output:	the student's assignments percent
	//	
	public double getAssignments() {
		return this.assignments;
	}

	// purpose: sets the assignments percent
	// input: 	the student's assignments percent
	// output:	none
	//
	public void setAssignments(double assignments) {
		this.assignments = assignments;
	}
	
	// purpose: gets the finalExam percent
	// input: 	none
	// output:	the student's finalExam percent
	//	
	public double getFinalExam() {
		return this.finalExam;
	}

	// purpose: sets the finalExam percent
	// input: 	the student's finalExam percent
	// output:	none
	//	
	public void setFinalExam(double finalExam) {
		this.finalExam = finalExam;
	}
		
	// purpose: gets the letterGrade
	// input: 	none
	// output:	the student's letterGrade
	//	
	public String getLetterGrade() {
		return this.letterGrade;
	}

	// purpose: sets the letterGrade
	// input: 	the student's letterGrade
	// output:	none
	//
	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}
	
	// purpose: gets the finalGrade
	// input: 	none
	// output:	the student's finalGrade
	//
	public int getFinalGrade() {
		return this.finalGrade;
	}		

	// purpose: calculates and sets the finalGrade given the weights of the 4 components.
	//			finalGrade set to -1 if weights do not add up to 100
	// input: 	the weight of the midterms, labs, assignments and final exam as integers 
	// output:	none
	//
	public void calculateFinalGrade(int midtermWeight, int labWeight, int assignmentWeight, 
		int finalExamWeight) {
	
		if (midtermWeight + labWeight + assignmentWeight + finalExamWeight != 100){
			System.out.println("invalid weighting");
			this.finalGrade = -1;
		}
		
		double grade = midterms*midtermWeight/100 + labs*labWeight/100 +
		assignments*assignmentWeight/100 + finalExam*finalExamWeight/100;
		
		this.finalGrade = (int)grade;
	}

	// purpose: provides a String representation of the student's grade
	// input: 	none 
	// output:	the Student's information as a String
	//	
	public String toString() {
		String student = stID + ":\t MIDTERMS:" + midterms + ", LABS:" + labs + 
			", ASSIGNMENTS:" + assignments + ", FINAL EXAM:" + finalExam + "\n";
		student += "FINALGRADE:\t" + letterGrade + ":" + finalGrade + "\n";
		
		return student;
		
	}
}