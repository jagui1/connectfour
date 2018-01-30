/**
 * This program takes in the amount of rows and columns for the size of 
 * a connect four board and completes the game. The program prompts the
 * user to keep playing until they say no. The user can also quit during 
 * any move.
 *
 * @author Jeremy Aguillon <agui1@umbc.edu>
 * @version Sep 30, 2013
 * @project CMSC 202 - Fall 2013 - Project #1
 * @section 06
 */
package proj1;
import java.util.Scanner;

public class Project1 {
	
	/** getValidInt()
	 * 
	 * This function takes a minimum and prompts the user until they enter a valid integer,
	 * which is defined as any integer greater than the minimum.
	 * 
	 * 
	 * @param min 
	 *           : integer value of the minimum amount of columns the user can have in a board
	 * @param input
	 *           : Scanner used to get input from the user
	 * @return integer
	 *           : returns the valid integer the user enters
	 */
	public static int getValidInt(int min, Scanner input){

		boolean validInt = false;
		int integer = 0;
		
		while (validInt == false){
    		integer = input.nextInt();

			if (integer < min ) {
				System.out.print("Integer is too small, try again: ");
			}
			else {
				validInt = true;
			}
		}
		return integer;
	}
	/** getValidMove()
	 * 
	 * This function takes in a maximum and prompts the user for a move
	 * until they enter a valid move. A valid move is any integer greater
	 * than 0 and less than the max or the character 'q'.
	 * 
	 * @param max : the maximum move the user can make
	 * @param input : Scanner used to get input from the user
	 * @return curInt : the integer value of the validated move of the user
	 */
	public static int getValidMove(int max, Scanner input){
		boolean validMove = false;
		int MIN_ROW = 1;
		int curInt = 0;
		String curMove;
		
		while(validMove == false){
			curMove = input.next();
			if(curMove.equals("q")){
				curInt = -1;
				validMove = true;
			} else {
				
				curInt = Integer.parseInt(curMove);
			
				if(curInt < MIN_ROW){
					System.out.print("Move is too small, try again: ");
				} else if (curInt > max){
					System.out.print("Move is too large, try again: ");
				} else {
					validMove = true;
				}
			}
		}
		return curInt;
	}
	/** makeMove()
	 * 
	 * This function takes in the player and current game. Then it gets and 
	 * makes a move for the current player. When it successfully makes the 
	 * move it returns true, if the user presses 'q' to quit, it returns false
	 * 
	 * @param player : integer value of the player currently moving
	 * @param input : Scanner used to get input from the user
	 * @param curGame : the current instance of the ConnectFour class
	 * @return gameQuit : boolean value of true if user quit the game, and false if not
	 */
	public static boolean makeMove(int player, Scanner input, ConnectFour curGame){
		int curMove = 0;
		boolean gameQuit = false;
		boolean madeMove = false;
		
		while(madeMove == false){
		
			curMove = getValidMove(curGame.getCols(), input);
			
			madeMove = curGame.getMove(curMove, player);
			if(curMove == -1){
				gameQuit = true;
				madeMove = true;
			}else if(madeMove == false){
				System.out.print("Invalid input. Row is full. Try again: ");
				
			}
			
		}
		if(gameQuit == false){
			System.out.println("Current Game board: " + curGame.toString());
		}
		
	return gameQuit;	
	}
	/** getPlayAgain()
	 * 
	 * This function asks the user if they want to play again.
	 * If yes, returns true.
	 * 
	 * @param input : Scanner used to get input from the user
	 * @return playAgain - the boolean value of true if the user wants to play again
	 */
	public static boolean getPlayAgain(Scanner input){
		boolean validStr = false;
		boolean playAgain = true;
		
		System.out.print("Do you want to play again? (y or n): ");
		
		while(validStr == false){
			String answer = input.next();
			if(answer.equals("y")){
				validStr = true;
				playAgain = true;
			} else if(answer.equals("n")){
				validStr = true;
				playAgain = false;
			} else{
				System.out.print("Invalid input. Try again (y or n): ");
			}
		}
		return playAgain;
	}
	/** fullGame()
	 * 
	 * This function actually plays and does each step of the game.
	 * First asks for the rows and columns. Gets a move for each of
	 * the users and after each move, checks for a quit, win or draw.
	 * If there is a quit, win or draw, the game does not do any
	 * subsequent code.
	 * 
	 * @param playGame : boolean value of true if the user wants to play
	 * @param input : Scanner to get input from the user
	 */
	public static void fullGame(boolean playGame, Scanner input){
		while(playGame == true){
			int MIN = 5;
			int player1 = 1;
			int player2 = 2;
			boolean gameWon = false;
			boolean gameQuit = false;
			boolean draw = false;
			
			System.out.print("Hi, please enter a number of rows: ");
			int rows = getValidInt(MIN, input);
			System.out.print("Please enter a number of columns: ");
			int cols = getValidInt(MIN, input);
		
			ConnectFour curGame = new ConnectFour(rows, cols);
	    
			System.out.println("Current Game board: " + curGame);

			while(gameWon == false){
				//Player 1
				System.out.printf("Player 1, please enter a move: ");
				gameQuit = makeMove(player1, input, curGame);
				if(gameQuit == false){
					gameWon = curGame.wonGame(player1);
					draw = curGame.checkDraw();
				
					//Player 2
					if(draw == false){
					
						if(gameWon == false){
							System.out.printf("Player 2, please enter a move: ");
							gameQuit = makeMove(player2, input, curGame);
							if(gameQuit == false){
								
								gameWon = curGame.wonGame(player2);
								draw = curGame.checkDraw();
						
								if(draw == false){
							
									if(gameWon == true){
										System.out.println("Player 2 wins!");
									}
								} else {
									System.out.println("Draw game.");
									gameWon = true;
								}
							} else {
								gameWon = true;
								playGame = false;
							}
						} else {
							System.out.println("Player 1 wins!");
						}
					} else {
						System.out.println("Draw game.");
						gameWon = true;
					}
					
			
				} else {
					gameWon = true;
					playGame = false;
				}
			}
			if(gameQuit == false){
				
				playGame = getPlayAgain(input);				
			}

		}
	}
	/** main()
	 * 
	 * the main function that is used to play the game
	 * 
	 * @param args - none
	 */
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		boolean playGame = true;
		
		fullGame(playGame, input);
		
		input.close();
	}
}
	

