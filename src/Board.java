
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
	
	
	//////////////////////////////////////////////////////////////
	//
	//	I will come back to this later. I'm sure there is a better
	//	way, I'm just too tired to think lol.
	//
	//////////////////////////////////////////////////////////////
	public boolean movePiece(Position oldPos, Position newPos)
	{
		int leftX;
		int leftY;
		int rightX;
		int rightY;
		if(oldPos.getX() < newPos.getX())
		{
			leftX = oldPos.getX();
			leftY = oldPos.getY();
			rightX = newPos.getX();
			rightY = newPos.getY();
		}
		else
		{
			rightX = oldPos.getX();
			rightY = oldPos.getY();
			leftX = newPos.getX();
			leftY = newPos.getY();
		}
		//Tried to move to the same location
		if(oldPos.equals(newPos))
			return false;
		//Check if the column is clear
		else if(leftX == rightX)
		{
			int startY = (leftY < rightY) ? leftY : rightY;
			int endY = (leftY > rightY) ? leftY : rightY;
			for(int i = startY + 1; i <= endY; i++)
				//If the spot has been or is occupied, return false
				if(board[i][leftX] != '-')
					return false;
			//Move piece
			board[newPos.getY()][newPos.getX()] = board[oldPos.getY()][oldPos.getX()];
			board[oldPos.getY()][oldPos.getX()] = '#';
		}
		//Check if the row is clear
		else if(leftY == rightY)
		{
			int startX = (leftX < rightX) ? leftX : rightX;
			int endX = (leftX > rightX) ? leftX : rightX;
			for(int i = startX + 1; i <= endX; i++)
				//If the spot has been or is occupied, return false
				if(board[leftY][i] != '-')
					return false;
			//Move piece
			board[newPos.getY()][newPos.getX()] = board[oldPos.getY()][oldPos.getX()];
			board[oldPos.getY()][oldPos.getX()] = '#';
		}
		//Check if the diagonal is clear
		else if(Math.abs((double)(leftY - rightY) / (leftX - rightX)) == 1)
		{
			int yCounter = leftY;
			//Moving up and right
			if(leftY < rightY)
			{
				for(int i = leftX; i < rightX; i++)
				{
					if(board[yCounter++][i] != '-')
						return false;
				}
			}
			//Moving down and right
			else
			{
				for(int i = leftX; i < rightX; i++)
				{
					if(board[yCounter--][i] != '-')
						return false;
				}
			}
			//Move piece
			board[newPos.getY()][newPos.getX()] = board[oldPos.getY()][oldPos.getX()];
			board[oldPos.getY()][oldPos.getX()] = '#';
		}
		//Not a valid move
		else
		{
			return false;
		}
		
		return true;
	}
	
	public void print()
	{
		System.out.println("  1 2 3 4 5 6 7 8");
		for(int i = 0; i < board.length; i++)
		{
			System.out.print(String.valueOf((char)(i + 65)) + " ");
			for(int j = 0; j < board[i].length; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");
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
