import java.util.ArrayList;

public class Board
{
	private char[][] board;
	private Position x_Pos;
	private Position o_Pos;
	
	public Board(Position com, Position opp)
	{
		board = new char[8][8];
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[i].length; j++)
				board[i][j] = '-';
		x_Pos = com; // Computer is always X
		o_Pos = opp; // Opponent is always O
		board[x_Pos.getY()][x_Pos.getX()] = 'X';
		board[o_Pos.getY()][o_Pos.getX()] = 'O';
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
	
	public void movePiece(Position oldPos, Position newPos)
	{
		//Move piece
		board[newPos.getY()][newPos.getX()] = board[oldPos.getY()][oldPos.getX()];
		board[oldPos.getY()][oldPos.getX()] = '#';	//Mark old spot as moved
	}
	
	public void print(ArrayList<String> comMoves, ArrayList<String> oppMoves)
	{
		int i = 0;	//Keep track of what row we are on
		System.out.println("  1 2 3 4 5 6 7 8\tComputer vs. Opponent");
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
}
