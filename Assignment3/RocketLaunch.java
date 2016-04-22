/*
* Program Name: RocketLaunch
* Purpose: print a ascii roket ship &
* 				encrypts and decrypts users input
* Program Input: Scanner
* Program Outputs: an asci i prepresentation of Apllo Spaceship 
* 				   encrypted and decrypts version of msg returned as String
*/


import java.util.*;
public class RocketLaunch {
	public static void main(String[] args) {

		//Use Scanner to get input from the command line
		Scanner scan1 = new Scanner(System.in);
		System.out.println("What size rocket would you like to build?");
		int input1 = scan1.nextInt();
		
		Scanner scan2 = new Scanner(System.in);
		System.out.println("How many boosters?");
		int input2 = scan2.nextInt();
		
		//call drawRocket method and pass user's input to drawRocket method
		drawRocket(input1,input2);
		
		Scanner scan3 = new Scanner(System.in);
		System.out.println("\nWhat is your message?");
		String input3 = scan3.nextLine();
		
		Scanner scan4 = new Scanner(System.in);
		System.out.println("What is your key?");
		int input4 = scan4.nextInt();
		
		Scanner scan5 = new Scanner(System.in);
		System.out.println("type 1 for encrypt or 2 for decrypt?");
		int input5 = scan5.nextInt();
		
		//evaluate user's input is 1 or 2 and decide compile which method
		if(input5 == 1) {
			encrypt(input3, input4);
			System.out.println("Your message encrypted is: " + encrypt(input3, input4));
		} else {
			decrypt(input3, input4);
			System.out.println("Your message decrypted is: " + decrypt(input3, input4));
		}
	}
	
	/* Name: drawRocket
 	* Purpose: call other methods
 	* Input: int size, int numBoosters
 	* Output:  the whole rocket
 	*/ 
	public static void drawRocket(int size, int numBoosters) {
		spaceCraft(size);
		interLayer1(size);
		lemAdapater(size);
		interLayer2(size);
		instrumentUnit(size);
		interLayer2(size);
		boosters(size, numBoosters);
		tail(size);
	}
	
	/* Name: spaceCraft
 	* Purpose: print space craft
 	* Input: int x
 	* Output: space craft
 	*/ 	
	public static void spaceCraft(int x) {
		int a = x*2;
		for(int line = 1; line <= a; line++) {

			int b = x*2+3-line;
			int c = line-1;

			for(int j = 1; j <= b; j++) {
				System.out.print(" ");
			}
			for(int k = 1; k <= c; k++) {
				System.out.print("/");
			}
			for(int m = 1; m <= 1; m++) {
				System.out.print("**");
			}
			for(int k = 1; k <= c; k++) {
				System.out.print("\\");
			}
			System.out.println();
		}
	}
	
	/* Name: interLayer1
 	* Purpose: print the first inter layer
 	* Input: int x
 	* Output: the first interLayer of the rocket
 	*/
	public static void interLayer1(int x) {
		System.out.print("  +");

		int a = x*2;
		for(int i = 1; i <= a; i++) {
			System.out.print("=*");
		}
		System.out.print("+");
		System.out.println();
	}

	/* Name: lemAdapater
 	* Purpose: print the lem adapater
 	* Input: int x
 	* Output: the lemAdapater of the rocket
 	*/
	public static void lemAdapater(int x) {
		for(int line = 1; line <= 2; line++) {

			int a = 2-line;
			int b = x*2+(line-1);

			for(int i = 1; i <= a; i++) {
				System.out.print(" ");
			}
			for(int j = 1; j <= 1; j++) {
				System.out.print("//");
			}
			for(int k = 1; k <= b; k++) {
				System.out.print(" %");
			}
			for(int j = 1; j <=1; j++) {
				System.out.print("\\\\");
			}
			System.out.println();
		}
	}
	
	/* Name: interLayer2
 	* Purpose: print the rest of the inter layers
 	* Input: int x
 	* Output: inter layers
 	*/
	public static void interLayer2(int x) {
		System.out.print("+");

		int a = x*2+2;
		for(int i =1; i <= a; i++) {
			System.out.print("=*");
		}
		System.out.print("+");
		System.out.println();
	}

	/* Name: instrumentUnit
 	* Purpose: print instrument unit of the rocket
 	* Input: int x
 	* Output: instrument unit
 	*/
	public static void instrumentUnit(int x) {
		for(int line = 1; line <= 2; line++) {
			for(int i = 1; i <= 1; i++) {
				System.out.print("||");
			}
			int a = 2*x+1;
			for(int j = 1; j <= a; j++) {
				System.out.print("~#");
			}
			for(int i = 1; i <= 1; i++) {
				System.out.print("||");
			}
			System.out.println();
		}
	}

	/* Name: boosters
 	* Purpose: control the number of booster
 	* Input: int x, int y
 	* Output: booster
 	*/
	public static void boosters(int x, int y) {
		for(int i = 1; i <= y; i++) {
			if(i==y) {
				booster(x);	
			} else {
				booster(x);
				interLayer2(x);
			}
		}
	}

	/* Name: booster
 	* Purpose: print booster of the rocket
 	* Input: int x
 	* Output: booster part
 	*/
	public static void booster(int x) {

		int a = x+1;

		for(int line = 1; line <= a; line++) {

			int b = x-line+1;
			int c = x*2-2*(line-1);
			int d = x-line+1;

			for(int i = 1; i <= 1; i++) {
				System.out.print("|");
			}
			for(int j = 1; j <= b; j++) {
				System.out.print(".");
			}
			for(int k = 1; k <= line; k++) {
				System.out.print("/\\");
			}
			for(int m = 1; m <= c; m++) {
				System.out.print(".");
			}
			for(int k = 1; k <= line; k++) {
				System.out.print("/\\");
			}
			for(int j = 1; j <= d; j++) {
				System.out.print(".");
			}
			for(int i = 1; i <=1; i++) {
				System.out.print("|");
			}
			System.out.println();
		}

		for(int line = 1; line <= a; line++) {

			int b = line-1;
			int c = x+2-line;
			int d = line*2-2;

			for(int i = 1; i <= 1; i++) {
				System.out.print("|");
			}
			for(int j = 1; j <= b; j++) {
				System.out.print(".");
			}
			for(int k = 1; k <= c; k++) {
				System.out.print("\\/");
			}
			for(int m = 1; m <= d; m++) {
				System.out.print(".");
			}
			for(int k = 1; k <= c; k++) {
				System.out.print("\\/");
			}
			for(int j = 1; j <= b; j++) {
				System.out.print(".");
			}
			for(int i = 1; i <= 1; i++) {
				System.out.print("|");
			}
			System.out.println();
		}
	}

	/* Name: tail
 	* Purpose: print the tail of the rocket
 	* Input: int x
 	* Output: tail part
 	*/
	public static void tail(int x) {
		interLayer2(x);
		System.out.print("//");
		for(int i = 1; i <= x; i++) {
			System.out.print("  /\\");
		}
		System.out.print("  \\\\");
	}
	
	/*
	PURPOSE: encrypts msg given an encryption key between 1 and 95
	INPUT: String msg, the message to be encrypted
				int key, the amount to increase the ascii character by
				to encrypt the message
	OUTPUT: an encrypted version of msg returned as a String
	*/
	public static String encrypt(String msg, int key) {
		int size = msg.length();
		String newWord = "";
		for(int i = 0; i < size; i++) {
			char letter = msg.charAt(i);
			
			int value = (int) letter;
			value +=key;
			char newChar= (char) value;
			newWord = newWord + newChar;
		}
		return newWord;
	}
	
	/*
	PURPOSE: decrypts msg given an encryption key between 1 and 95
	INPUT: String msg, the message to be decrypted
	int key, the amount to shift the ascii character by
	to decrypt the message
	OUTPUT: a decrypted version of msg returned as a String
	*/
	public static String decrypt(String msg, int key) {
		int size = msg.length();
		String newWord = "";
		for(int i = 0; i < size; i++) {
			char letter = msg.charAt(i);
			
			int value = (int) letter;
			value -=key;
			char newChar= (char) value;
			newWord = newWord + newChar;
		}
		return newWord;
	}
}