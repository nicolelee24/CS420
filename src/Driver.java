import java.util.Scanner;

public class Driver
{
	public static void main(String[] args)
	{
		char first = ' ';
		double timeLimit = 0;
		Scanner keyboard = new Scanner(System.in);
		
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
		
		// TODO: I don't think we need current if we have 'first' already
		Position current;	//The position of the current player
		
		//Set the positions to the correct spots
		//Make the com top left if they go first
		if(first == 'c')
		{
			comPos = new Position("A1");
			oppPos = new Position("H8");
			current = comPos;
		}
		//Otherwise make the opp top left
		else
		{
			oppPos = new Position("A1");
			comPos = new Position("H8");
			current = oppPos;
		}
		
		Board board = new Board(comPos, oppPos);
		char turn = first;	//Who's turn it is
		board.print();
		
		///////////////////////////////////////////////////////
		//
		//	This was for testing the movement function
		//	This would be where I intended the game loop to go
		//
		///////////////////////////////////////////////////////
		
		// Some valid moves arn't working, change "G6" to "E7" or "F1"
		// The logic in 'movePiece' might be off, I'm still trying to understand it
		String[] positions = {"A4", "F6", "D1", "F1", "D8"};
		for (int i = 0; i < 5; i++) {
			Position newPos = new Position(positions[i]);
			
			// computer's turn to move
			if (turn == 'c') {
				if(board.movePiece(comPos, newPos)) {
					System.out.println("Move made: " + positions[i]);
					comPos = newPos;
				} else {
					System.out.println("Move not made: "+ positions[i]);
				}
				board.print();
				turn = 'o';
			} else {
				// opponent's turn to move
				if(board.movePiece(oppPos, newPos)) {
					System.out.println("Move made: " + positions[i]);
					oppPos = newPos;
				} else {
					System.out.println("Move not made: " + positions[i]);
				}
				board.print();
				turn = 'c';
			}
		}
		/*
		if(board.movePiece(current, new Position("A4")))
		{
			System.out.println("Move made");
			current = new Position("A4");
		}
		else
		{
			System.out.println("Move not made");
		}
		board.print();
		if(board.movePiece(current, new Position("A5")))
		{
			System.out.println("Move made");
			current = new Position("A5");
		}
		else
		{
			System.out.println("Move not made");
		}
		board.print();
		if(board.movePiece(current, new Position("A3")))
		{
			System.out.println("Move made");
			current = new Position("A3");
		}
		else
		{
			System.out.println("Move not made");
		}
		board.print();
		*/
		keyboard.close();
	}
	
}
