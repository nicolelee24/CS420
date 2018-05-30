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
	private static long timeLimit;
	private static long startTime; // time at which computer's turn starts
	private static int max_depth;
	private static Position comPos;	//Position of the computer
	private static Position oppPos;	//Position of the opponent
	
	public static void main(String[] args)
	{
		char first = ' ';	//Holds who is going first
		timeLimit = 0;	//Holds the time limit for each turn
		char winner = ' ';	//Indicates who the winner is
		
		//Get the time limit for each move
		while(timeLimit <= 0)
		{
			//Make sure the user enters a number
			try
			{
				System.out.print("How many seconds for each turn? ");
				timeLimit = keyboard.nextLong();
				timeLimit *= 1000; // convert time to milliseconds
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
		//	Start of Game! Let's play ISOLATION!
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
					newPos = getNewComPos();	//Get the computer's move
					//Make the move if the move is valid
					if(board.canMoveTo(comPos, newPos))
					{
						board.movePiece(comPos, newPos, 'x');	//Make move
						board.setSpacesLeft();
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
					newPos = getNewOppPos();	//Get the opponent's move
					//Make the move if the move is valid
					if(board.canMoveTo(oppPos, newPos))
					{
						board.movePiece(oppPos, newPos, 'o');	//Make move
						board.setSpacesLeft();
						System.out.println("Opponent's move is: " + newPos.toString());
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
	
	private static Position getNewComPos()
	{
		startTime = System.currentTimeMillis();
		ArrayList<Position> moves = board.generatePossibleMoves(comPos);
		//There is only one move for the computer so make this move
		if(moves.size() == 1)
			return moves.get(0);
		moves.sort(null);
		ArrayList<Position> bestMoves = new ArrayList<Position>();
		max_depth = 1;	//The limit for the depth
		Position bestLastDepth = moves.get(0);
		int depth = 0;	//Our current depth

		while(!isTimeUp())
		{
			int a = Integer.MIN_VALUE;	//Alpha
			int b = Integer.MAX_VALUE;	//Beta

			//Move through each valid move
			for(int i = 0; i < moves.size() && !isTimeUp(); i++)
			{
				Position temp = moves.get(i);
				Position originalCom = new Position(comPos);	//So we can move back properly
				comPos = board.movePiece(comPos, temp, 'x');
				int v = min(a, b, (depth + 1));
				//Return the piece back to its original position
				comPos = board.movePieceBack(originalCom, temp, 'x');
				//Check if this move is better than alpha
				//If it is better then set a to v and reset bestMoves with this move
				if(v > a)
				{
					a = v;
					bestMoves.clear();
					bestMoves.add(temp);
				}
				//If v equals a then add this move to the list of bestMoves
				else if(v == a)
					bestMoves.add(temp);
				
				//Prune if alpha is greater than or equal to b
				if(a >= b)
					return bestMoves.get(0);
				
			}
			bestLastDepth = bestMoves.get(rand.nextInt(bestMoves.size()));
			max_depth++;
		}
		System.out.println("Depth Reached: " + (max_depth - 1));
		System.out.println("Time: " + ((System.currentTimeMillis() - startTime) / 1000.0));
		return bestLastDepth;
	}
	
	//Min function
	private static int min(int a, int b, int depth)
	{
		ArrayList<Position> moves = board.generatePossibleMoves(oppPos);
		moves.sort(null);
		//Terminal test
		if(depth == max_depth)
			return board.evaluate(false);
		int minVal = Integer.MAX_VALUE;
		for(int i = 0; i < moves.size() && !isTimeUp(); i++)
		{
			Position temp = moves.get(i);
			Position originalOpp = new Position(oppPos);	//So we can move back properly
			oppPos = board.movePiece(oppPos, temp, 'o');
			minVal = Math.min(minVal, max(a, b, (depth + 1)));
			//Return the piece back to its original position
			oppPos = board.movePieceBack(originalOpp, temp, 'o');
			if(minVal <= a)
				break;
			b = Math.min(minVal, b);
		}
		return minVal;
	}
	
	//Max function
	private static int max(int a, int b, int depth)
	{
		ArrayList<Position> moves = board.generatePossibleMoves(comPos);
		moves.sort(null);
		//Terminal test
		if(depth == max_depth)
			return board.evaluate(true);
		int maxVal = Integer.MIN_VALUE;
		for(int i = 0; i < moves.size() && !isTimeUp(); i++)
		{
			Position temp = moves.get(i);
			Position originalCom = new Position(comPos);	//So we can move back properly
			comPos = board.movePiece(comPos, temp, 'x');
			maxVal = Math.max(maxVal, min(a, b, (depth + 1)));
			//Return the piece back to its original position
			comPos = board.movePieceBack(originalCom, temp, 'x');
			if(maxVal >= b)
				break;
			a = Math.max(maxVal, a);
		}
		return maxVal;
	}
	
	// Opponent/User inputs position
	private static Position getNewOppPos()
	{
		String oppMove = "";
		Position newPos = new Position("");
		boolean onBoard = false;	//Flag to indicate if user's response is on the board
		while(!onBoard)
		{
			System.out.print("Enter opponent's move: ");
			oppMove = keyboard.nextLine();
			newPos = new Position(oppMove);
			if(newPos.getX() < 0 || newPos.getX() > 7 || newPos.getY() < 0 || newPos.getY() > 7)
				onBoard = false;
			else
				onBoard = true;
		}
		return newPos;
	}
	
	// Automated opponent: move to a random position based on number of available moves
//	private static Position getNewOppPos()
//	{
//		ArrayList<Position> moves = board.generatePossibleMoves(oppPos);
//		moves.sort(null);
//		//Get the first element
//		Position newPos = moves.get(0);//moves.get(rand.nextInt(moves.size()));
//		ArrayList<Position> bestMoves = new ArrayList<Position>();
//		bestMoves.add(newPos);
//		for(int i = 1; i < moves.size(); i++)
//		{
//			Position temp = moves.get(i);
//			if(temp.getMoveValue() == newPos.getMoveValue())
//				bestMoves.add(temp);
//			else
//				break;
//		}
//		newPos = bestMoves.get(rand.nextInt(bestMoves.size()));
//		return newPos;
//	}
	
	private static boolean isTimeUp() {
		// stops ~1 millisecond before time limit is reached
		if ((System.currentTimeMillis() - startTime) > (timeLimit - 2)) {
			return true;
		}
		return false;
	}
}