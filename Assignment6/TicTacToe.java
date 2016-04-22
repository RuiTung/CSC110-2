/*
* Program Name: TicTacToe
* Purpose: use length of args to build a board and play game
* Program Input: Scanner
* Program Outputs: X and O
*/


import java.util.*;

public class TicTacToe {
	
	//property of String type array a 
	static String [] a = new String [] {"X", "O"};
	
	public static void main(String[] args ) {
		int SIZE = 3;
		
		// check whether length is available
		if(args.length > 0 && Integer.parseInt(args[0]) % 3 == 0) {
			SIZE = Integer.parseInt(args[0]);
		} else {
			System.out.println("Sorry, it is an invalide boardsize");
			System.out.println(" -- setting board to 3 X 3");
		}
		char[][] gameBoard = new char[SIZE][SIZE];
		Scanner s = new Scanner(System.in);
		Random r = new Random(System.currentTimeMillis());
		playGame(gameBoard, s, r);
	}
	
	/*
	PURPOSE: to play a game of TicTacToe taking input from 2 players
	INPUT: char[][] board, a matrix holding tokens:
	? indicates a blank space
	O indicates a space claimed by X player
	X indicates a space claimed by O player
	Scanner s, to provide command line input from the 2 players
	Random r, a random number generator to decide who goes first
	OUTPUT: the board will be printed initially with all cells
	containing ‘?’ characters. It will be reprinted
	to the console with each move a player makes.
	*/
	public static void playGame(char[][] gameBoard, Scanner s, Random r) {
		String answer = chooseToken(s, r);
		char[][] a = fillBoard(gameBoard);
		print(a);
		System.out.println();
		boolean contin = false;
		String player= answer;
		
		// loop body
		while(contin == false){	
			while(s.hasNextLine()) {
				System.out.println("enter the coordinates of an untaken cell in row column format");
				System.out.println("ie. 1 0 will place your make in row 1 column 0 of the grid");
				System.out.println("your entry please...");
				System.out.println();
				int c = s.nextInt();
				int d = s.nextInt();
				
				// check whether a position is avaliable for assign X or O
				if(a[c][d] == '?') {	
					if((c < 0 || c > a.length) || (d < 0 || d > a.length)) {
						System.out.println("bad cell cordinates\nyour entry please...");
					} else {
						if(player.equalsIgnoreCase("X")) {
							a[c][d] = 'X';
						} else {
							a[c][d] = 'O';
						}

						if(whetherPlay(a, c, d, player) == true) {					
							print(a);
							System.out.println();
							System.out.println(player + " wins");
							contin = true;
							break;
						} else {
							player= changePlayer(player);
							print(a);
							System.out.println();
							System.out.println(player + " it is your turn!");
							System.out.println();
						}
					}
				} else {
					System.out.println();
					System.out.println("bad cell cordinates\nyour entry please...");
				}			
			}
		}	
	}
	
	/*
	PURPOSE: change player
	INPUT: X or O
	OUTPUT: X or O
	*/	
	public static String changePlayer(String m) {
		String currentPlayer = m;
		if(currentPlayer.equals("X")) {
			currentPlayer = "O";
		} else {
			currentPlayer = "X";
		}
		return currentPlayer;
	}
	
	/*
	PURPOSE: let user choose a token that they want to play
	INPUT: Scanner, Random
	OUTPUT: random user
	*/
	public static String chooseToken(Scanner s, Random r) {
		System.out.println();
		System.out.print("Please choose X or O as your token to play Tic Tac Toe game: ");
		String answer = s.next();
		System.out.println(answer);
		while(!answer.equalsIgnoreCase("X") && !answer.equalsIgnoreCase("O")) {
			System.out.print("Invalid answer. Please choose X or O as your token to play this game: ");
			answer = s.next();
		}
		System.out.println("OK! Another token is for your friends now.");
		
		System.out.println();
		int first = r.nextInt(a.length);
		String z = null;
		if(first == 0) {
			z = a[0];
		} else
			z = a[a.length-1];
		System.out.println(z + " it is your turn!");
		return z;
	}	

	/*
	PURPOSE: fill in '?' in the board
	INPUT: char[][] arr
	OUTPUT: board with '?'
	*/
	public static char[][] fillBoard(char[][] arr) {
		char[][] a = new char[arr.length][arr.length];
		for(int i =  0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				a[i][j] = '?';
			}
		}
		return a;
	}
	
	/*
	PURPOSE: print other parts of the board
	INPUT: char[][] arr
	OUTPUT: nothing
	*/
	public static void print(char[][] arr) {
		System.out.println();
		System.out.print("  |");
		for(int i = 0; i < arr.length; i ++) {
			System.out.print(i + "|" );
		}
		System.out.println();
		System.out.print("  -");
		for(int j = 0; j < arr.length * 2; j ++) {
			System.out.print("-");
		}
		System.out.println();
		for(int i = 0; i < arr.length; i ++) {
			System.out.print(i + " " + "|");
			for(int j = 0; j <arr[i].length; j++) {
				System.out.print(arr[i][j] + "|");
			}
			System.out.println();
		}
	}
	
	/*
	PURPOSE: 
	INPUT: char[][] arr, users input, String X or O
	OUTPUT: boolean
	*/
	public static boolean whetherPlay (char[][] arr, int a, int b, String answer){
		char temp = '\0';
		if (answer.equals("X")) {
			temp= 'X';
		}else if(answer.equals("O")) {
			temp='O';
		}
		
		// initialize boolean value
		boolean choice1 = false;
		boolean choice2 = false;
		boolean choice3 = false;
		boolean choice4 = false;
		boolean choice5 = false;
		boolean decision = false;
			
		int m = 0;
			
		// check lines
		choice1= true;
		while(choice1 == true && m < arr.length) {			
			if(arr[a][m] == temp){
				m++;
				choice1 = true;
			} else{
				choice1 = false;
			}
		}
			
		//check rows
		m = 0;
		choice2 = true;
		while(choice2 == true && m < arr.length) {			
			if(arr[m][b] == temp){
				m++;
				choice2 = true;
			} else {
				choice2 = false;
			}
		}
		
			
		// check oblique lines
		if(a == b) {
			m = 0;
			choice3 = true;
			while(choice3 == true && m < arr.length) {			
				if(arr[m][m] == temp){
					m++;
					choice3 = true;
				} else {
					choice3 = false;
				}
			}
		}
				
		// check oblique lines
		else if(a + b == arr.length-1) {
			choice4 = true;
			m=0;
			while(choice4 == true && m<arr.length) {			
				if(arr[m][arr.length-1-m] == temp){
					m++;
					choice4 = true;
				} else {
					choice4 = false;
				}
			}
		}
		
		// check if the whole broad is full
		int n = 0;
		for(int j = 0; j < arr.length; j ++) {
			for(int k = 0; k < arr[j].length; k ++) {
				if(arr[j][k] == '?') {
					choice5 = false;
				} else {
					n++;
				}
			}
		}
		
		// check if the board is full, the whole game will be quit
		if (n == arr.length * arr.length){
			choice5 = true;
			System.out.println();
			System.out.println("Game over.");
			System.exit(0);
		}
		
		if(choice1 == true || choice2 == true || choice3 == true || choice4 == true) {
			decision = true;
		} else {
			decision = false;
		}
		return decision;
	}
}

