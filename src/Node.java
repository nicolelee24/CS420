
// Node class for game tree in minimax algorithm
public class Node {

	private char[][] state;
	private Position moveMade;
	private Node parent;
	private int heuristic;
	
	public Node(char[][] s, Position p, Node par, Boolean isCom) {
		parent = par;
		moveMade = p;
		
		// copy over previous state
		int x = 0, y = 0;
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length; j++) {
				// found the X position and the move is for the computer
				if (state[i][j] == 'X' && isCom) {
					y = i;
					x = j;
				} else if (state[i][j] == 'O' && !isCom) {
					// found the O position and the move is for the opponent
					y = i;
					x = j;
				}
				state[i][j] = s[i][j];
			}
		}
		
		// make the intended move from the given position
		state[y][x] = '#';
		if (isCom) {
			state[p.getY()][p.getX()] = 'X'; // computer move
		} else {
			state[p.getY()][p.getX()] = 'O'; // opponent move
		}
		
		// calculate heuristic method call
	}

	public char[][] getState() {
		return state;
	}

	public void setState(char[][] state) {
		this.state = state;
	}

	public Position getMoveMade() {
		return moveMade;
	}

	public void setMoveMade(Position moveMade) {
		this.moveMade = moveMade;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
}
