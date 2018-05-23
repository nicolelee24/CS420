/*
 * A position class to convert a string of two characters (Ex: "A1") into the correct 
 * x- and y-coordinates.
 * If a string is entered that is not two characters long, the program sets the x- and
 * y-coordinates to -1.
 */

public class Position implements Comparable<Position>
{
	private int x;	//X-coordinate
	private int y;	//Y-coordinate
	private int moveValue;	//The number of moves at the current position
	
	//Constructor
	//Takes a string and sets the x- and y-coordinates accordingly
	public Position(String pos)
	{
		//Make sure the string is of the correct length
		if(pos.length() == 2)
		{
			pos = pos.toUpperCase();	//Make it capitalized for ease of use
			x = pos.charAt(1) - '1';	//The X-coordinate starts at 1 so subtract '1'
			y = pos.charAt(0) - 'A';	//The Y-coordinate starts at A so subtract 'A'
		}
		//The length of pos is not correct
		else
		{
			x = -1;
			y = -1;
		}
		moveValue = -1;
	}
	
	public Position(int i, int j, int moveValue) {
		x = i;
		y = j;
		this.moveValue = moveValue;
	}
	
	public Position(Position other)
	{
		this.x = other.x;
		this.y = other.y;
		this.moveValue = other.moveValue;
	}
	
	public void update(int i, int j) {
		x = i;
		y = j;
	}
	
	public boolean equals(Position other)
	{
		if(other.getX() == this.x && other.getY() == this.y)
			return true;
		return false;
	}
	
	public String toString()
	{
		String pos = "";
		pos += (char)('A' + y);
		pos += (char)('1' + x);
		return pos;
	}

	@Override
	public int compareTo(Position other)
	{
		if(this.moveValue < other.moveValue)
			return 1;
		if(this.moveValue > other.moveValue)
			return -1;
		return 0;
	}

	/////////////////////////
	// Getters and Setters //
	/////////////////////////
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getMoveValue() {
		return moveValue;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setMoveValue(int moveValue) {
		this.moveValue = moveValue;
	}
}
