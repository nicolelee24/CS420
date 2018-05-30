import java.util.ArrayList;

public class Board
{
	private char[][] board;
	private int[][] moveValues;
	private Position x_Pos;
	private Position o_Pos;
	private int spacesLeft;
	
	public Board(Position com, Position opp)
	{
		board = new char[8][8];
		moveValues = new int[8][8];
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[i].length; j++)
				board[i][j] = '-';
		x_Pos = com; // Computer is always X
		o_Pos = opp; // Opponent is always O
		board[x_Pos.getY()][x_Pos.getX()] = 'X';
		board[o_Pos.getY()][o_Pos.getX()] = 'O';
		updateMoveValues(null);
		spacesLeft = 62; // Initialize spaces left
	}
	
	//Update the number of moves at each position
	private void updateMoveValues(Position pos)
	{
		if(pos == null) {
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					if (board[j][i] != '#') {
						moveValues[j][i] = countMoves(i, j);
					}
				}
			}
		}
		else
		{
			int counter = 1;
			int x = pos.getX();
			int y = pos.getY();
			
			//Check up/left
			for(int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--)
			{
				if(board[j][i] == '-')
					counter++;
				else
					break;
			}
			//Update down/right
			for(int i = x + 1, j = y + 1; i < board.length && j < board.length; i++, j++)
			{
				if(board[j][i] == '-')
					moveValues[j][i] -= counter;
				else
				{
					moveValues[j][i] -= counter;
					break;
				}
			}
			
			counter = 1;	//Reset the counter
			//Check down/right
			for(int i = x + 1, j = y + 1; i < board.length && j < board.length; i++, j++)
			{
				if(board[j][i] == '-')
					counter++;
				else
					break;
			}
			//Update up/left
			for(int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--)
			{
				if(board[j][i] == '-')
					moveValues[j][i] -= counter;
				else
				{
					moveValues[j][i] -= counter;
					break;
				}
			}
			
			counter = 1;	//Reset the counter
			//Check up
			for(int j = y - 1; j >= 0; j--)
			{
				if(board[j][x] == '-')
					counter++;
				else
					break;
			}
			//Update down
			for(int j = y + 1; j < board.length; j++)
			{
				if(board[j][x] == '-')
					moveValues[j][x] -= counter;
				else
				{
					moveValues[j][x] -= counter;
					break;
				}
			}
			
			counter = 1;	//Reset the counter
			//Check down
			for(int j = y + 1; j < board.length; j++)
			{
				if(board[j][x] == '-')
					counter++;
				else
					break;
			}
			//Update up
			for(int j = y - 1; j >= 0; j--)
			{
				if(board[j][x] == '-')
					moveValues[j][x] -= counter;
				else
				{
					moveValues[j][x] -= counter;
					break;
				}
			}
			
			counter = 1;	//Reset the counter
			//Check up/right
			for(int i = x + 1, j = y - 1; i < board.length && j >= 0; i++, j--)
			{
				if(board[j][i] == '-')
					counter++;
				else
					break;
			}
			//Update down/left
			for(int i = x - 1, j = y + 1; i >= 0 && j < board.length; i--, j++)
			{
				if(board[j][i] == '-')
					moveValues[j][i] -= counter;
				else
				{
					moveValues[j][i] -= counter;
					break;
				}
			}
			
			counter = 1;	//Reset the counter
			//Check down/left
			for(int i = x - 1, j = y + 1; i >= 0 && j < board.length; i--, j++)
			{
				if(board[j][i] == '-')
					counter++;
				else
					break;
			}
			//Update up/right
			for(int i = x + 1, j = y - 1; i < board.length && j >= 0; i++, j--)
			{
				if(board[j][i] == '-')
					moveValues[j][i] -= counter;
				else
				{
					moveValues[j][i] -= counter;
					break;
				}
			}
			
			counter = 1;	//Reset the counter
			//Check right
			for(int i = x + 1; i < board.length; i++)
			{
				if(board[y][i] == '-')
					counter++;
				else
					break;
			}
			//Update left
			for(int i = x - 1; i >= 0; i--)
			{
				if(board[y][i] == '-')
					moveValues[y][i] -= counter;
				else
				{
					moveValues[y][i] -= counter;
					break;
				}
			}
			
			counter = 1;	//Reset the counter
			//Check left
			for(int i = x - 1; i >= 0; i--)
			{
				if(board[y][i] == '-')
					counter++;
				else
					break;
			}
			//Update right
			for(int i = x + 1; i < board.length; i++)
			{
				if(board[y][i] == '-')
					moveValues[y][i] -= counter;
				else
				{
					moveValues[y][i] -= counter;
					break;
				}
			}
		}
	}
	
	private void revertMoveValues(Position pos)
	{
		int counter = 1;
		int x = pos.getX();
		int y = pos.getY();
		
		//Check up/left
		for(int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--)
		{
			if(board[j][i] == '-')
				counter++;
			else
				break;
		}
		//Update down/right
		for(int i = x + 1, j = y + 1; i < board.length && j < board.length; i++, j++)
		{
			if(board[j][i] == '-')
				moveValues[j][i] += counter;
			else
			{
				moveValues[j][i] += counter;
				break;
			}
		}
		
		counter = 1;	//Reset the counter
		//Check down/right
		for(int i = x + 1, j = y + 1; i < board.length && j < board.length; i++, j++)
		{
			if(board[j][i] == '-')
				counter++;
			else
				break;
		}
		//Update up/left
		for(int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--)
		{
			if(board[j][i] == '-')
				moveValues[j][i] += counter;
			else
			{
				moveValues[j][i] += counter;
				break;
			}
		}
		
		counter = 1;	//Reset the counter
		//Check up
		for(int j = y - 1; j >= 0; j--)
		{
			if(board[j][x] == '-')
				counter++;
			else
				break;
		}
		//Update down
		for(int j = y + 1; j < board.length; j++)
		{
			if(board[j][x] == '-')
				moveValues[j][x] += counter;
			else
			{
				moveValues[j][x] += counter;
				break;
			}
		}
		
		counter = 1;	//Reset the counter
		//Check down
		for(int j = y + 1; j < board.length; j++)
		{
			if(board[j][x] == '-')
				counter++;
			else
				break;
		}
		//Update up
		for(int j = y - 1; j >= 0; j--)
		{
			if(board[j][x] == '-')
				moveValues[j][x] += counter;
			else
			{
				moveValues[j][x] += counter;
				break;
			}
		}
		
		counter = 1;	//Reset the counter
		//Check up/right
		for(int i = x + 1, j = y - 1; i < board.length && j >= 0; i++, j--)
		{
			if(board[j][i] == '-')
				counter++;
			else
				break;
		}
		//Update down/left
		for(int i = x - 1, j = y + 1; i >= 0 && j < board.length; i--, j++)
		{
			if(board[j][i] == '-')
				moveValues[j][i] += counter;
			else
			{
				moveValues[j][i] += counter;
				break;
			}
		}
		
		counter = 1;	//Reset the counter
		//Check down/left
		for(int i = x - 1, j = y + 1; i >= 0 && j < board.length; i--, j++)
		{
			if(board[j][i] == '-')
				counter++;
			else
				break;
		}
		//Update up/right
		for(int i = x + 1, j = y - 1; i < board.length && j >= 0; i++, j--)
		{
			if(board[j][i] == '-')
				moveValues[j][i] += counter;
			else
			{
				moveValues[j][i] += counter;
				break;
			}
		}
		
		counter = 1;	//Reset the counter
		//Check right
		for(int i = x + 1; i < board.length; i++)
		{
			if(board[y][i] == '-')
				counter++;
			else
				break;
		}
		//Update left
		for(int i = x - 1; i >= 0; i--)
		{
			if(board[y][i] == '-')
				moveValues[y][i] += counter;
			else
			{
				moveValues[y][i] += counter;
				break;
			}
		}
		
		counter = 1;	//Reset the counter
		//Check left
		for(int i = x - 1; i >= 0; i--)
		{
			if(board[y][i] == '-')
				counter++;
			else
				break;
		}
		//Update right
		for(int i = x + 1; i < board.length; i++)
		{
			if(board[y][i] == '-')
				moveValues[y][i] += counter;
			else
			{
				moveValues[y][i] += counter;
				break;
			}
		}
	}
	
	//Print the moveValues array. This was for testing only
	public void printMoveValues()
	{
		System.out.println("\n   1  2  3  4  5  6  7  8");
		//Print the board and each move so far
		for(int i = 0; i < board.length; i++)
		{
			System.out.print(String.valueOf((char)(i + 65)) + " ");	//Print the row number
			for(int j = 0; j < moveValues[i].length; j++)
			{
				if (moveValues[i][j] < 10) {
					System.out.print(" " + moveValues[i][j] + " ");
				}
				else
					System.out.print(moveValues[i][j] + " ");	//Print the value of the board at the current position
			}
			
			System.out.println("");
		}
	}
	
	//Count the number of moves at the current position
	private int countMoves(int x, int y)
	{
		int numMoves = 0;
		//Count left
		for(int i = x - 1; i >= 0; i--)
		{
			if(board[y][i] == '-')
				numMoves++;
			else
				break;
		}
		//Count right
		for(int i = x + 1; i < board.length; i++)
		{
			if(board[y][i] == '-')
				numMoves++;
			else
				break;
		}
		//Count up
		for(int i = y - 1; i >= 0; i--)
		{
			if(board[i][x] == '-')
				numMoves++;
			else
				break;
		}
		//Count down
		for(int i = y + 1; i < board.length; i++)
		{
			if(board[i][x] == '-')
				numMoves++;
			else
				break;
		}
		//Count up/left
		for(int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--)
		{
			if(board[j][i] == '-')
				numMoves++;
			else
				break;
		}
		//Count up/right
		for(int i = x + 1, j = y - 1; i < board.length && j >= 0; i++, j--)
		{
			if(board[j][i] == '-')
				numMoves++;
			else
				break;
		}
		//Count down/left
		for(int i = x - 1, j = y + 1; i >= 0 && j < board.length; i--, j++)
		{
			if(board[j][i] == '-')
				numMoves++;
			else
				break;
		}
		//Count up/left
		for(int i = x + 1, j = y + 1; i < board.length && j < board.length; i++, j++)
		{
			if(board[j][i] == '-')
				numMoves++;
			else
				break;
		}
		return numMoves;
	}
	
	// Method to generate all possible moves for a give position
	public ArrayList<Position> generatePossibleMoves(Position pos) {
		ArrayList<Position> moves = new ArrayList<Position>();
		
		// Going Up
		for (int i = pos.getY() - 1; i > -1; i--) {
			if (board[i][pos.getX()] == '-') {
				Position p = new Position(pos.getX(), i, moveValues[i][pos.getX()]);
				moves.add(p);
			} else {
				break;
			}
		}
		// Going Down
		for (int i = pos.getY() + 1; i < board.length; i++) {
			if (board[i][pos.getX()] == '-') {
				Position p = new Position(pos.getX(), i, moveValues[i][pos.getX()]);
				moves.add(p);
			} else {
				break;
			}
		}
		// Going Left
		for (int i = pos.getX() - 1; i > -1; i--) {
			if (board[pos.getY()][i] == '-') {
				Position p = new Position(i, pos.getY(), moveValues[pos.getY()][i]);
				moves.add(p);
			} else {
				break;
			}
		}
		// Going Right
		for (int i = pos.getX() + 1; i < board.length; i++) {
			if (board[pos.getY()][i] == '-') {
				Position p = new Position(i, pos.getY(), moveValues[pos.getY()][i]);
				moves.add(p);
			} else {
				break;
			}
		}
		// Going Up/Left Diagonal
		for (int i = pos.getX() - 1, j = pos.getY() - 1; i > -1 && j > -1; i--, j--) {
			if (board[j][i] == '-') {
				Position p = new Position(i, j, moveValues[j][i]);
				moves.add(p);
			} else {
				break;
			}
		}
		// Going Up/Right Diagonal
		for (int i = pos.getX() + 1, j = pos.getY() - 1; i < board.length && j > -1; i++, j--) {
			if (board[j][i] == '-') {
				Position p = new Position(i, j, moveValues[j][i]);
				moves.add(p);
			} else {
				break;
			}
		}
		// Going Down/Left Diagonal
		for (int i = pos.getX() - 1, j = pos.getY() + 1; i > -1 && j < board.length; i--, j++) {
			if (board[j][i] == '-') {
				Position p = new Position(i, j, moveValues[j][i]);
				moves.add(p);
			} else {
				break;
			}
		}
		// Going Down/Right Diagonal
		for (int i = pos.getX() + 1, j = pos.getY() + 1; i < board.length && j < board.length; i++, j++) {
			if (board[j][i] == '-') {
				Position p = new Position(i, j, moveValues[j][i]);
				moves.add(p);
			} else {
				break;
			}
		}	
		return moves;
	}
	
	//Takes in the new and old positions and makes sure the piece can be moved
	//Moves the piece if it is able to be moved
	//Returns true if piece is moved or false if it is not moved
	public boolean canMoveTo(Position oldPos, Position newPos)
	{
		//The positions are the same don't move the piece
		if(oldPos.equals(newPos))
			return false;
		boolean movingRight = false;	//Indicates if the piece is trying to move right
		boolean movingDown = false;		//Indicates if the piece is trying to move down
		//The piece is moving right
		if(oldPos.getX() < newPos.getX())
			movingRight = true;
		//The piece is moving down
		if(oldPos.getY() < newPos.getY())
			movingDown = true;
		//The piece is moving along the same column
		if(oldPos.getX() == newPos.getX())
		{
			//Piece is moving down
			if(movingDown)
			{
				//Make sure column is clear
				for(int y = oldPos.getY() + 1; y <= newPos.getY(); y++)
					//If the spot has been or is occupied, return false
					if(board[y][oldPos.getX()] != '-')
						return false;
			}
			//The piece is moving up
			else
			{
				//Make sure column is clear
				for(int y = oldPos.getY() - 1; y >= newPos.getY(); y--)
					//If the spot has been or is occupied, return false
					if(board[y][oldPos.getX()] != '-')
						return false;
			}
		}
		//The piece is moving along the same row
		else if(oldPos.getY() == newPos.getY())
		{
			//The piece is moving right
			if(movingRight)
			{
				//Make sure row is clear
				for(int x = oldPos.getX() + 1; x <= newPos.getX(); x++)
					if(board[oldPos.getY()][x] != '-')
						return false;
			}
			//The piece is moving left
			else
			{
				//Make sure row is clear
				for(int x = oldPos.getX() - 1; x >= newPos.getX(); x--)
					if(board[oldPos.getY()][x] != '-')
						return false;
			}
		}
		//The piece is moving along the diagonal
		else if(Math.abs((double)(oldPos.getY() - newPos.getY()) / (oldPos.getX() - newPos.getX())) == 1)
		{
			int yCounter = oldPos.getY();	//Keeps track of the y-coordinate
			//Set the yCounter appropriately
			if(movingDown)
				yCounter++;
			else
				yCounter--;
			//Moving right and down
			if(movingRight && movingDown)
			{
				for(int x = oldPos.getX() + 1; x <= newPos.getX(); x++)
					if(board[yCounter++][x] != '-')
						return false;
			}
			//Moving right and up
			else if(movingRight && !movingDown)
			{
				for(int x = oldPos.getX() + 1; x <= newPos.getX(); x++)
					if(board[yCounter--][x] != '-')
						return false;
			}
			//Moving left and down
			else if(!movingRight && movingDown)
			{
				for(int x = oldPos.getX() - 1; x >= newPos.getX(); x--)
					if(board[yCounter++][x] != '-')
						return false;
			}
			//Moving left and up
			else
			{
				for(int x = oldPos.getX() - 1; x >= newPos.getX(); x--)
					if(board[yCounter--][x] != '-')
						return false;
			}
		}
		//Not a valid move
		else
			return false;
		//Piece is able to be moved so return true
		return true;
	}
	
	public Position movePiece(Position oldPos, Position newPos, char letter)
	{
		//Move piece
		board[newPos.getY()][newPos.getX()] = board[oldPos.getY()][oldPos.getX()];
		board[oldPos.getY()][oldPos.getX()] = '#';	//Mark old spot as moved
		updateMoveValues(newPos);
		if(letter == 'x') {
			x_Pos = new Position(newPos);
			return x_Pos;
		}
		else {
			o_Pos = new Position(newPos);
			return o_Pos;
		}
	}
	
	public Position movePieceBack(Position originalPos, Position currentPos, char letter)
	{
		revertMoveValues(currentPos);
		board[originalPos.getY()][originalPos.getX()] = board[currentPos.getY()][currentPos.getX()];
		board[currentPos.getY()][currentPos.getX()] = '-';
		if(letter == 'x') {
			x_Pos = new Position(originalPos);
			return x_Pos;
		}
		else {
			o_Pos = new Position(originalPos);
			return o_Pos;
		}
	}
	
	// Function to make sure the player can make a move
	public boolean canMove(Position pos)
	{
		if (pos.getY() - 1 > -1 && board[pos.getY() - 1][pos.getX()] == '-') {
			return true; // player can move up
		}
		
		if (pos.getY() + 1 < 8 && board[pos.getY() + 1][pos.getX()] == '-') {
			return true; // player can move down
		}
		
		if (pos.getX() - 1 > -1 && board[pos.getY()][pos.getX() - 1] == '-') {
			return true; // player can move left
		}
		
		if (pos.getX() + 1 < 8 && board[pos.getY()][pos.getX() + 1] == '-') {
			return true; // player can move right
		}
		
		if (pos.getX() - 1 > -1 && pos.getY() - 1 > -1 && board[pos.getY() - 1][pos.getX() - 1] == '-') {
			return true; // player can move up/left diagonal
		}

		if (pos.getX() + 1 < 8 && pos.getY() - 1 > -1 && board[pos.getY() - 1][pos.getX() + 1] == '-') {
			return true; // player can move up/right diagonal
		}

		if (pos.getX() - 1 > -1 && pos.getY() + 1 < 8 && board[pos.getY() + 1][pos.getX() - 1] == '-') {
			return true; // player can move down/left diagonal
		}
		
		if (pos.getX() + 1 < 8 && pos.getY() + 1 < 8 && board[pos.getY() + 1][pos.getX() + 1] == '-') {
			return true; // player can move down/right diagonal
		}
		
		return false; // no move can be made
	}
	
	public void print(ArrayList<String> comMoves, ArrayList<String> oppMoves)
	{
		int i = 0;	//Keep track of what row we are on
		System.out.println("\n  1 2 3 4 5 6 7 8\tComputer vs. Opponent");
		//Print the board and each move so far
		for(; i < board.length; i++)
		{
			System.out.print(String.valueOf((char)(i + 65)) + " ");	//Print the row number
			for(int j = 0; j < board[i].length; j++)
			{
				System.out.print(board[i][j] + " ");	//Print the value of the board at the current position
			}
			//If there either player has made i+1 number of moves, print them out
			if(comMoves.size() > i || oppMoves.size() > i)
			{
				System.out.print("    " + (i + 1) + ".   ");	//Print what move number it was
				if(comMoves.size() > i)
					System.out.print(comMoves.get(i));	//Print com's move
				else
					System.out.print("  ");	//Just print a blank
				System.out.print("\t\t");
				if(oppMoves.size() > i)
					System.out.print(oppMoves.get(i));	//Print opp's move
			}
			System.out.println("");
		}
		//Continue to print the moves after the board has been printed
		while(comMoves.size() > i || oppMoves.size() > i)
		{
			//Just for proper spacing 
			if(i == 8)
				System.out.print("\t\t      ");
			else
				System.out.print("\t\t     ");
			System.out.print((i + 1) + ".   ");	//Print out what move number it is
			if(comMoves.size() > i)
				System.out.print(comMoves.get(i));	//Print out com's move
			else
				System.out.print("  ");	//print out a blank space
			System.out.print("\t\t");
			if(oppMoves.size() > i)
				System.out.print(oppMoves.get(i));	//Print out opp's move
			System.out.println("");
			i++;
		}
		System.out.println("\n");
	}
	
	public int evaluate(boolean isMax)
	{	
		boolean oMove = canMove(o_Pos);
		boolean xMove = canMove(x_Pos);
		
		// opponent can't make a move and computer can
		if (!oMove && xMove) { 
			return Integer.MAX_VALUE;
		}
		
		// opponent can make a move and computer can't
		if (oMove && !xMove) {
			return Integer.MIN_VALUE;
		}
		
		// no one can move, then the one that "moves" next loses
		if (!oMove && !xMove && isMax) {
			return Integer.MIN_VALUE; 
		}
		
		if (!oMove && !xMove && !isMax) {
			return Integer.MAX_VALUE;
		}
		
		// Super Aggressive Improved Score for first half of game
		if (spacesLeft >= 30) { 
			return (moveValues[x_Pos.getY()][x_Pos.getX()]) - (3 * moveValues[o_Pos.getY()][o_Pos.getX()]);
		}
		else { // Longest Path Length for second half of game
			int	x_length = LongestPathLength(true, x_Pos);
			int	o_length = LongestPathLength(false, o_Pos);
			return (x_length - o_length);
		}
		
	}

	// Finds the length of the longest path from a given position
	private int LongestPathLength(boolean isMax, Position pos) {
		int longest = 0;
		ArrayList<Position> moves = generatePossibleMoves(pos);
		moves.sort(null);
		for(int i = 0; i < moves.size(); i++)
		{
			Position temp = moves.get(i);
			Position original; // save a copy of the original position
			if (isMax) {
				original = new Position(x_Pos);
				movePiece(x_Pos, temp, 'x');
				int pathLength = LongestPathLength(isMax,temp) + 1; //get longest path from next position
				if (pathLength > longest) {
					longest = pathLength;
				}
				//Return the piece back to its original position
				movePieceBack(original, temp, 'x');
				if (longest >= 20) break; //just in case, terminate if there's a long enough path
			}
			else {
				original = new Position(o_Pos);
				movePiece(o_Pos, temp, 'o');
				int pathLength = LongestPathLength(isMax,temp) + 1; //get longest path from next position
				if (pathLength > longest) {
					longest = pathLength;
				}
				//Return the piece back to its original position
				movePieceBack(original, temp, 'o');
				if (longest >= 20) break; //just in case, terminate if there's a long enough path
			}
		}
		
		return longest;
	}
	
	/////////////////////////
	// Getters and Setters //
	/////////////////////////
	public char[][] getBoard() {
		return board;
	}

	public Position getXPos() {
		return x_Pos;
	}

	public Position getOPos() {
		return o_Pos;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public void setXPos(Position x_Pos) {
		this.x_Pos = x_Pos;
	}

	public void setOPos(Position o_Pos) {
		this.o_Pos = o_Pos;
	}
	
	public int getSpacesLeft() {
		return spacesLeft;
	}
	
	public void setSpacesLeft() {
		spacesLeft--;
	}
}
