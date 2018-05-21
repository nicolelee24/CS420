import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Driver
{
	private static final Scanner keyboard = new Scanner(System.in);
	private static Board board;
	private static ArrayList<String> comMoves = new ArrayList<String>();	//Keeps track of the computer's moves
	private static ArrayList<String> oppMoves = new ArrayList<String>();	//Keeps track of the opponenet's moves
	private static Random rand = new Random();
	
	public static void main(String[] args)
	{
		char first = ' ';	//Holds who is going first
		double timeLimit = 0;	//Holds the time limit for each turn
		char winner = ' ';	//Indicates who the winner is
		
		//Get the time limit for each move
		while(timeLimit <= 0)
		{
			//Make sure the user enters a number
			try
			{
				System.out.print("How many seconds for each turn? ");
				timeLimit = keyboard.nextDouble();
				keyboard.nextLine();
			}catch(Exception e)
			{
				keyboard.nextLine();
				timeLimit = 0;
			}
		}
		
		//Find out who goes first
		while(first != 'c' && first != 'o')
		{
			//Make sure user enters something
			try
			{
				System.out.print("Who goes first (c/o): ");
				first = keyboard.nextLine().toLowerCase().charAt(0);
			}catch(Exception e)
			{
				first = ' ';
			}
		}
		
		Position comPos;	//Position of the computer
		Position oppPos;	//Position of the opponent
		
		//Set the positions to the correct spots
		//Make the com top left if they go first
		if(first == 'c')
		{
			comPos = new Position("A1");
			oppPos = new Position("H8");
		}
		//Otherwise make the opp top left
		else
		{
			oppPos = new Position("A1");
			comPos = new Position("H8");
		}
		
		board = new Board(comPos, oppPos);
		char turn = first;	//Who's turn it is
		board.print(comMoves, oppMoves);
		
		///////////////////////////////////////////////////////
		//
		//	This was for testing the movement function
		//	This would be where I intended the game loop to go
		//
		///////////////////////////////////////////////////////
		
		//Continue until there is a winner
		while(winner == ' ')
		{
			Position newPos;
			//Computer's turn
			if (turn == 'c') 
			{
				//Make sure the computer has a valid move
				if(board.canMove(comPos))
				{
					newPos = getNewComPos(comPos);	//Get the computer's move
					//Make the move if the move is valid
					if(board.canMoveTo(comPos, newPos))
					{
						board.movePiece(comPos, newPos);	//Make move
						System.out.println("Computer's move is: " + newPos.toString());
						comPos = newPos;	//Update the computer's position
						turn = 'o';	//It's the opponents turn
						comMoves.add(newPos.toString());	//Add the move to the computer's list of moves
					}
					else
					{
						System.out.println("Move not made " + newPos.toString());
					}
					board.print(comMoves, oppMoves);
				}
				//The computer has no valid moves so the opponent wins
				else
				{
					winner = 'o';
				}
			}
			// opponent's turn to move
			else
			{
				//Make sure the opponent has a valid move
				if(board.canMove(oppPos))
				{
					newPos = getNewOppPos("opponent's");	//Get the opponent's move
					//Make the move if the move is valid
					if(board.canMoveTo(oppPos, newPos))
					{
						board.movePiece(oppPos, newPos);	//Make move
						System.out.println("Move made " + newPos.toString());
						oppPos = newPos;	//Update the opponent's position
						turn = 'c';	//It's the computer's turn next
						oppMoves.add(newPos.toString());	//Add the move to the opponent's list of moves
					}
					else
					{
						System.out.println("Move not made " + newPos.toString());
					}
					board.print(comMoves, oppMoves);
				}
				//The opponent has no valid moves so the computer wins
				else
				{
					winner = 'c';
				}
			}
		}
		
		//Print out who won
		if(winner == 'c')
			System.out.println("The computer wins!");
		else
			System.out.println("The opponent wins!");
		
		keyboard.close();
	}
	
	// Computer gets to make a random move
	private static Position getNewComPos(Position comPos)
	{	
		/*Position newPos = new Position(rand.nextInt(8), rand.nextInt(8));
		while(!board.canMoveTo(comPos, newPos)) {
			newPos.update(rand.nextInt(8), rand.nextInt(8));
		}
		*/
		ArrayList<Position> moves = board.generatePossibleMoves(comPos);
		moves.sort(null);	//Sort the positions so they are in order (Probably will change to a priority queue later)
		//Get the first element
		Position newPos = moves.get(0);//moves.get(rand.nextInt(moves.size()));
		return newPos;
	}
	
	private static Position getNewOppPos(String player)
	{
		String oppMove = "";
		Position newPos = new Position("");
		boolean onBoard = false;	//Flag to indicate if user's response is on the board
		while(!onBoard)
		{
			System.out.print("Enter " + player + " move: ");
			oppMove = keyboard.nextLine();
			newPos = new Position(oppMove);
			if(newPos.getX() < 0 || newPos.getX() > 7 || newPos.getY() < 0 || newPos.getY() > 7)
				onBoard = false;
			else
				onBoard = true;
		}
		return newPos;
	}
	
/*	private static boolean canMove(Position pos)
	{
		for(int y = pos.getY() - 1; y <= pos.getY() + 1; y++)
		{
			for(int x = pos.getX() - 1; x <= pos.getX() + 1; x++)
			{
				try
				{
					if(board.getBoard()[y][x] == '-')
						return true;
				}catch(Exception e)
				{}
			}
		}
		return false;
	}*/
}
