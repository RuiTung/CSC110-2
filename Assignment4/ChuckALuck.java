/*
* Program Name: ChuckALuck
* Purpose: play the chuck a luck game
* Program Input: Scanner,Random
* Program Outputs: total amount of money before the first round, current round money,
* 					user's target number, 3 random number from PC, wins money of each round,
* 					round number and money
*/

import java.util.Random;
import java.util.Scanner;


public class ChuckALuck {
	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		Scanner scan = new Scanner(System.in);
		play(scan, rand);
	}
	
	/*
	PURPOSE: to play the chuck a luck game
	INPUT: Scanner scanner, to collect input from the user for the
	the bet amount and if they want to continue playing
	Random randomObject, to simulate dice roll
	OUTPUT: prompts to ask user for bet and updates of bankroll
	*/
	public static void play(Scanner userInput, Random generator) {
		
		//call bank method and get return value of it
		double bank = bankRollMoney(userInput);
		
		//initialize round value
		int round = 0;
		
		//initialize round value
		boolean playing = true;
		
		//game loop body
		while (playing == true) {
			
			//method call
			gameStart(bank);
			double bet = nextBet(userInput, bank); 
			int guess = chooseNum(userInput);
			int same = diceRoll(generator, guess);
			double winnings = win(same, bet);
			bank = currentAmount(bet, winnings, bank);
			
			//round number
			round++;
			
			gameEnd(round, bank);

			playing = playAgain(userInput, bank);
		}
	}
	
	/*
	PURPOSE: cast value of bankroll from user's input
	INPUT: Scanner userInput, to collect input from the user for the the total amount of money
	OUTPUT: user's money before the first round
	*/
	public static double bankRollMoney(Scanner userInput) {
		System.out.println("How much do you want to add to your bankroll? ");
		double money = userInput.nextDouble();
		
		//cast money value into two decimal places
		money = (double)Math.round(money*100)/100;
		return money;
	}
	
	/*
	PURPOSE: cast amount of money of each round and make a question inquire bet value
	INPUT: amount of money after each round
	OUTPUT: question before every round including amount of money of each round
	*/
	public static void gameStart(double bank) {
		System.out.println();
		bank = (double)Math.round(bank*100)/100;
		System.out.print("What is the value of your next bet ($0.00 - $" + bank + ")? ");
	}
	
	/*
	PURPOSE: get amount of money of each round
	INPUT: every round's bet, every round's wins and every round's amount of money
	OUTPUT: amount of money of each round 
	*/
	public static double currentAmount(double bet, double winnings, double bank) {
		double nowMoney = bank - bet + winnings;
		return nowMoney;
	}
	
	/*
	PURPOSE: get bet value of each round and check validity range of user's bet value
	INPUT: bet value and every round's amount of money
	OUTPUT: bet value of each round
	*/
	public static double nextBet(Scanner userInput, double bank) {  
		double bet = userInput.nextDouble();
		
		//check validity range of user's value bet
		while (bet < 0 || bet > bank) { 
			System.out.print("Invalid bet value, please type in your bet value between $0.00 to $ " + bank + ": ");
			bank = (double)Math.round(bank*100)/100;
			bet = userInput.nextDouble();
		}
		return bet;
	}
	
	/*
	PURPOSE: get cast-money of each round wins
	INPUT: quantity of the same number between PC and user, bet value
	OUTPUT: wined money of each round
	*/
	public static double win(int same, double bet) {
		double wins = 0;
		
		//check the same number of user's target number and PC's random number
		if(same == 1) {
			wins = bet;
		} else if(same == 2) {
			wins = bet * 2;
		} else if(same == 3) {
			wins = bet * 10;
		} else {
			wins = 0;
		}
		
		//cast amount of wined money  into two decimal places and return the value
		System.out.format("\nWinnings = " + "%.2f", wins);
		return wins;
	}
	
	/*
	PURPOSE: get user's target number
	INPUT: Scanner userInput
	OUTPUT: user's target number
	*/
	public static int chooseNum(Scanner userInput) {
		System.out.print("Choose a number between 1 and 6: ");
		int num = userInput.nextInt();
		
		//check validity range of user's bet value
		while(num < 1 || num > 6) {
			System.out.print("Invalid number, please choose a number between 1 and 6: ");
			num = userInput.nextInt();
		}
		return num;
	}

	/*
	PURPOSE: get 3 random number from PC and get quantity of the same number between PC and user
	INPUT: Scanner userInput, user's target number
	OUTPUT: random number from PC and the same user's target number
	*/
	public static int diceRoll(Random generator, int guess) {
		int num = 0;
		int same = 0;
		System.out.print("Dice rolls: ");
		
		//output 3 random number from for loop
		for(int i = 1; i <= 3; i++) {
			num = generator.nextInt(6)+1;
			System.out.print(num + " ");
			
			//check the same value's number
			if(num == guess) {
				same++;
			}
		}
		return same;
	}
	
	/*
	PURPOSE: tell user's current round and money left
	INPUT: round number and amount of money of each round
	OUTPUT: statement of current round money
	*/
	public static void gameEnd(int round, double bank) {
		System.out.format("\nAfter bet " + round + " your bank roll is $" + "%.2f", bank );
		System.out.println();
	}
	
	/*
	PURPOSE: check whether user want to continue to play
	INPUT: Scanner userInput, amount of money of each round
	OUTPUT: boolean value 
	*/
	public static boolean playAgain(Scanner userInput, double bank) {
		System.out.println();
		boolean keepPlaying = false;
		if(bank <= 0) {
			System.out.println("Sorry, you don't have enough money.");
			System.out.println("Thank you for playing the game.");
			keepPlaying = false;	
			
		//check validity of user's input to determine whether keep playing
		} else {
			System.out.print("Do you want to keep playing? ");
			String answer = userInput.next();
			while(!answer.equalsIgnoreCase("n") && !answer.equalsIgnoreCase("y") && 
					!answer.equalsIgnoreCase("no") && !answer.equalsIgnoreCase("yes")) {
				System.out.println("Invalid answer. please enter y or n, or yes or no.");
				System.out.print("Do you want to keep playing? ");
				answer = userInput.next();
			}
			
			//relevant "yes" case
			if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
				keepPlaying = true;
			}
			
			//relevant "no" case
			if(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
				System.out.println("Thank you for playing the game.");
				keepPlaying = false;	
			}	
		}
		return keepPlaying;
	}
}