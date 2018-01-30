/**
 * This class is for the ConnectFour Object to be used in Project1
 *
 * @author Jeremy Aguillon <agui1@umbc.edu>
 * @version Sep 30, 2013
 * @project CMSC 202 - Fall 2013 - Project #1
 * @section 06
 */
package proj1;

public class ConnectFour {
	private int rows;
	private int cols;
	private int[][] board;
	
	/** 
	 * Constructs a new ConnectFour instance with the board the size of the provided rows and columns.
	 * If the input is invalid, defaults to the value of 5.
	 * 
	 * Precondition: rows and cols must both be greater than 4
	 * Postcondition: None
	 * @param rows : integer number of rows
	 * @param columns : integer number of columns
	 */
	public ConnectFour(int rows, int columns){
	    
		if (rows <= 4 && columns > 4){

			this.rows = 5;
	        this.cols = columns;
	        
		} else if(rows > 4 && columns <= 4){
			
			this.rows = rows;
			this.cols = 5;
			
		}else if(rows <= 4 && cols <= 4){
			
			this.rows = 5;
			this.cols = 5;
			
		} else {  
			
	    	this.rows = rows;
	    	this.cols = columns;
		
	    	
	    }
		
		board = new int[this.rows][this.cols];

	}
	
	/**
	 * Constructs a default ConnectFour instance with 5 rows and 5 columns
	 */
	public ConnectFour(){
		this(5, 5);
	}
	
	/** getRows()
	 * 
	 * Retrieves the value in the rows variable
	 * 
	 * Precondition: None
	 * Postcondition: None
	 * @return rows : the integer value of rows
	 */
	public int getRows(){
		return rows;
	}
	
	/** getCols()
	 * 
	 * Retrieves the value in the cols variable
	 * 
	 * Precondition: None
	 * Postcondition: None
	 * @return cols : the integer value of columns
	 */
	public int getCols(){
		return cols;
	}
	
	/** getMove()
	 * 
	 * A move in ConnectFour consists of taking the column move the player enters, validating the input, 
	 * and finding the cell that is being called by checking all of the cells in the column starting at the 
	 * bottom and changing the first empty cell to the number of the player moving.
	 * 
	 * @param move 
	 *           : integer value of the column the user wants to move to
	 * @param player
	 *           : integer value of the player that is making the move
	 * @return madeMove
	 *           : boolean value of whether or not the move was valid
	 */
	public boolean getMove(int move, int player){
		// Constant, top row on the board
		int FIRST = 0;
		// Keeps track of the rows starting from the bottom row
		int rowCount = rows - 1;
		// Changes move to correct column(i.e. Column 1 Bottom row ==  Board[rows - 1][0])
		int curCol = move - 1;
		int checkFlag = 1;
		boolean madeMove = true;
		
		// Checks if the move is within the columns of the game board
		if(curCol < 0 || curCol > cols){
			madeMove = false;
			
		// Checks if the row on top of the move has a move already made
		} else if (board[FIRST][curCol] != 0){
			madeMove = false;
			
		} else {
			// Loops until it finds and empty spot on the board
			while(checkFlag == 1){
				
				// When it gets to an empty space, makes the move based on the player moving
				if(board[rowCount][curCol] == 0){
					
					if(player == 1){
						
						board[rowCount][curCol] = 1;
						madeMove = true;
						
					}else if (player == 2){

						board[rowCount][curCol] = 2;
						madeMove = true;
						
					} // end inner if statement
					checkFlag = 0;
					
				} else {
					
					rowCount -= 1;
				} // end outer if statement
			
			} // end while loop
			
		} // end validation if statement
		return madeMove;
	}
	/** toString()
	 * 
	 *  This method creates a string of the board consisting of "_"'s for an empty space,
	 *  "x"'s for player 1 and "o"'s for player 2. The board is the size of the rows and 
	 *  columns the user entered
	 * 
	 * @return printBoard
	 *                  : a string of the board
	 */
	public String toString(){
		
		String printBoard = new String("\n");

		// loops through all rows
		for(int i = 0; i < rows; i++){
			
			// loops through each column of each row
			for(int j = 0; j < cols; j++){
				
				// prints corresponding character
				if(board[i][j] == 0){
					printBoard += "_";
					
				} else if (board[i][j] == 1){
					printBoard += "x";
					
				} else if (board[i][j] == 2){
					printBoard += "o";
				} // end if statement
				
			}// end nested for loop
			printBoard += "\n";
			
		}// end outer for loop
		return printBoard;
	}
	/** horizontalCheck()
	 * 
	 *  This method checks whether or not the current player has moved in
	 *  four spaces in a row horizontally by cycling through each row and
	 *  keeping a counter of the number of the same player in a row. Returns
	 *  true if the player has won. 
	 *  
	 * @param player
	 *             : integer value of the player that is moving currently
	 * @return gameWon
	 *             : boolean value of whether or not the game has been won
	 */
	public boolean horizontalCheck(int player){
		// keeps track of the number of consecutive moves
		int counter;
		boolean gameWon = false;

		
		// loops through all rows
		for(int i = 0; i < rows; i++){
			
			//resets counter for each new row
			counter  = 0;
			
			// loops through each column of each row
			for(int j = 0; j < cols; j++){
				
				// counts consecutive moves
				if(board[i][j] == player){
					counter += 1;
					
				} else {
					counter = 0;

				} // end if statement
				
				if(counter == 4){
					// game won with 4 consecutive moves
					gameWon = true;
				}
				
			} // end nested for loop

		} // end outer for loop
		return gameWon;
	}
	/** verticalCheck()
	 * 
	 *  This method checks whether or not the current player has moved in
	 *  four spaces in a row vertically by cycling through each column and
	 *  keeping a counter of the number of the same player in a column. 
	 *  Returns true if the player has won. 
	 *  
	 * @param player
	 *             : integer value of the player that is moving currently
	 * @return gameWon
	 *             : boolean value of whether or not the game has been won
	 */
	public boolean verticalCheck(int player){
		// keeps track of the number of consecutive moves
		int counter;
		boolean gameWon = false;

		
		// loops through each column
		for(int i = 0; i < cols; i++){
			
			// resets counter after each column
			counter  = 0;
			
			// loops through each row of each column
			for(int j = 0; j < rows; j++){
				
				// counts consecutive moves
				if(board[j][i] == player){
					counter += 1;

				} else {
					counter = 0;

				} // end if statement
				
				if(counter == 4){
					// game won with 4 moves in a row
					gameWon = true;
				}
				
			} // end nested for loop
			
		} // end outer for loop
		
		return gameWon;
	}
	/** diagonalCheckLtoR()
	 * 
	 * This method checks whether or not the current player has moved four
	 * spaces in a row diagonally going from the bottom left to the top right.
	 * It checks this by using a nested loop to treat each diagonal with more 
	 * than 4 entries as a row or a column and uses a counter to check if there
	 * are 4 moves by the current player next to each other. The first pair of 
	 * nested loops check the diagonals with at least 4 spaces that begin on
	 * the first column. The second pair of nested loops check the diagonals with
	 * at least 4 spaces that begins on the bottom row not including the first 
	 * column. Returns a boolean value of true if the player has won diagonally 
	 * and false if they have not.
	 * 
	 * @param player
	 *             : integer value of the player that is moving currently
	 * @return gameWon
	 * 			   : boolean value of whether or not the game has been won
	 */				
	public boolean diagonalCheckLtoR(int player){
		// first column in the gameBoard
		int colCount = 0;
		// first row that has 4 spaces diagonally up and to the right of it
		int rowCount = 3;
		// keeps track of the number of consecutive moves
		int counter = 0;
		boolean gameWon = false;

		
		// loops through all rows  in the first column starting at the first row with 4 spaces in its diagonal, going until the bottom of the board
		for(int i = 3; i < rows; i++){
			
			// loops through the amount of rows because that is the max amount of spaces a diagonal can have
			for(int j = 0; j < rows; j++){
				
				// checks if the current space is on the board
				if(rowCount >= 0 && colCount < cols - 1){
					
					// counts the consecutive moves
					if(board[rowCount][colCount] == player){
							counter += 1;
							
					} else {
						counter = 0;
						
					} // end if statement
					
					if(counter == 4){
						// game won with 4 moves in a row
						gameWon = true;
					}
					// moves the current space down one row (up 1) and up one column (right 1) each iteration of nested loop
					rowCount -= 1; colCount += 1;

				} // end validation if statement

			} // end nested for loop
			
			// updates the row counter , resets the column counter, resets the move counter
			rowCount = i + 1; colCount = 0; counter = 0;
		} // end outer for loop
		
		// first column on the bottom row that has a diagonal with 4 spaces
		colCount = cols - 4;
		// bottom row
		rowCount = rows - 1;

		// loops through each column in the bottom row (except the first column), that have at least 4 spaces in their diagonal
		for(int i = cols - 4; i > 0; i--){
			
			// loops through the amount of rows because that is the max amount of spaces a diagonal can have
			for(int j = 0; j < rows; j++ ){
				
				// checks if the current space is on the board
				if(rowCount >= 0 && colCount < cols - 1){
					
					// counts the consecutive moves
					if(board[rowCount][colCount] == player){

						counter += 1;
						
					} else {
						counter = 0;
						
					} // end if statement
					
					if(counter == 4){
						// game won with 4 moves in a row
						gameWon = true;
					}
					// moves the current space down one row (up 1) and up one column (right 1) each iteration of nested loop
					rowCount -= 1; colCount += 1;

				} // end validation if statement
				
			} // end nested for loop
			
			// resets the row counter, updates the column counter, resets the move counter
			rowCount = rows - 1; colCount = i - 1; counter  = 0;

		} // end outer for loop
		
		return gameWon;
	}
	/** diagonalCheckRtoL()
	 * 
	 * This method checks whether or not the current player has moved four
	 * spaces in a row diagonally going from the bottom right to the top left.
	 * It checks this by using a nested loop to treat each diagonal with more 
	 * than 4 entries as a row or a column and uses a counter to check if there
	 * are 4 moves by the current player next to each other. The first pair of 
	 * nested loops check the diagonals with at least 4 spaces that begin on
	 * the last column. The second pair of nested loops check the diagonals with
	 * at least 4 spaces that begins on the bottom row not including the last 
	 * column. Returns a boolean value of true if the player has won diagonally 
	 * and false if they have not.
	 * 
	 * @param player
	 *             : integer value of the player that is moving currently
	 * @return gameWon
	 * 			   : boolean value of whether or not the game has been won
	 */	
	public boolean diagonalCheckRtoL(int player){
		// last column on the board
		int colCount = cols - 1;
		// first row that has 4 diagonal spaces
		int rowCount = 3;
		// keeps track of the number of consecutive moves
		int counter = 0;		
		boolean gameWon = false;
		
		// loops through all rows  in the first column starting at the first row with 4 spaces in its diagonal, going until the bottom of the board 
		for(int i = 3; i < rows; i++){
			
			// loops through the amount of rows because that is the max amount of spaces a diagonal can have
			for(int j = 0; j < rows; j++){
				
				// checks if the current space is on the board
				if(rowCount >= 0 && colCount >= 0){

					// counts the consecutive moves
					if(board[rowCount][colCount] == player){
						counter += 1;
						
					} else {
						counter = 0;
						
					} // end if statement
					
					if(counter == 4){
						// game won with 4 moves in a row
						gameWon = true;
						
					}
					
					// moves the current space down one row (up 1) and down one column (left 1) each iteration of nested loop
					rowCount -= 1; colCount -= 1;
				} // end validation if statement

			} // end nested for loop
			
			// updates the row counter , resets the column counter, resets the move counter
			rowCount = i + 1; colCount = cols - 1; counter = 0;
			
		} // end outer for loop

		// first column that has 4 spaces in its diagonal on the bottom row
		colCount = 3;
		// bottom row 
		rowCount = rows - 1;

		// loops through each column in the bottom row (except the first column), that have at least 4 spaces in their diagonal
		for(int i = 4; i < cols; i++){
			
			// loops through the amount of rows because that is the max amount of spaces a diagonal can have
			for(int j = 0; j < rows; j++ ){
				
				// checks if the current space is on the board
				if(rowCount >= 0 && colCount >= 0){

					// counts the consecutive moves
					if(board[rowCount][colCount] == player){
						counter += 1;
						
					} else {
						counter = 0;
						
					} // end if statement
					
					if(counter == 4){
						// game won with 4 moves in a row
						gameWon = true;
						
					}
					// moves the current space down one row (up 1) and down one column (left 1) each iteration of nested loop
					rowCount -= 1; colCount -= 1;
					
				} // end validation if statement
				
			} // end nested for loop
			
			// resets the row counter , updates the column counter, resets the move counter
			rowCount = rows - 1; colCount = i; counter = 0;

		}
		return gameWon;
	}
	/** checkDraw()
	 * 
	 * This method checks the top row of each column to see if it is full.
	 * If every column is full, returns true for a draw game.
	 * 
	 * @return drawGame 
	 *                : the boolean value of true if a draw and false if not
	 */
	public boolean checkDraw(){
		// top row of the game board
		int FIRST = 0;
		// number of top spaces that need to be filled for a draw
		int maxSpaces = cols;
		// keeps track of the number of full spaces
		int counter = 0;
		boolean drawGame = false;

		// loops through each of the columns
		for(int i = 0; i < cols; i++){
			
			// counts the number of filled columns
			if(board[FIRST][i] != 0){
				counter += 1;
				
			}
			
			if(counter == maxSpaces){
				// game is a draw if all columns are filled
				drawGame = true;
				
			}
		} // end for loop
		
		return drawGame;
	}
	/** wonGame()
	 * 
	 * This method checks if the current player has won vertically,
	 * horizontally and diagonally from each direction. Retruns a 
	 * boolean value depending on whether or no the game has been won
	 * 
	 * @param player
	 *             : the integer value of the player that is moving currently 
	 * @return gameWon
	 *             : the boolean value of true if the game is won, and false if not
	 */
	public boolean wonGame(int player){
		boolean gameWon = false;
		
		//horizontal check
		gameWon = horizontalCheck(player);
		
		// vertical check if the game has not been won
		if(gameWon == false){
			gameWon = verticalCheck(player);
		}
		// diagonal check left to right if the game has not been won
		if(gameWon == false){
			gameWon = diagonalCheckLtoR(player);
		}
		// diagonal check right to left if the game has not been won
		if(gameWon == false){
			gameWon = diagonalCheckRtoL(player);
		}

		return gameWon;

	}

}
