package model;

public class Board {
	// Only square boards - can change later
	private int size;
	private Card[][] board;
	/*
	 * Game modes:
	 *     - 0 = Basic
	 */
	private int gameMode;
	
	private String[] shapes = {"Square", "Circle", "Plus"};
	private String[] colors = {"Red", "Blue", "Green", "Black", "Orange", "Yellow"};
	
	// Constructor **SIZE MUST BE EVEN & LESS THAN / EQUAL TO 6**
	public Board(int size) {
		if(size % 2 != 0 || size > 6) {
			System.out.println("Use even sizes only");
		}
		this.size = size;
		board = new Card[size][size];
		gameMode = 0;
		initBoard(size);
		shuffle();
	}
	
	// Initialize board with random cards based on mode
	public void initBoard(int size) {
		// Basic game mode set board
		if(gameMode == 0) {
			// Set color
			for(int i = 0; i < size; i++) {
				// Set shape
				for(int j = 0; j < size / 2; j++) {
					Card temp1 = new Card(colors[i], shapes[j]);
					Card temp2 = new Card(colors[i], shapes[j]);
					board[i][2*j] = temp1;
					board[i][2*j + 1] = temp2;
				}
			}
		}
		
		// Add more game modes when we need
	}
	
	// Change game mode
	public void changeMode(int gameMode) {
		this.gameMode = gameMode;
	}
	
	// Getter
	public Card getCard(int x, int y) {
		return board[x][y];
	}
	
	// Shuffle board (Must be initialized)
	public void shuffle() {
		Card[][] tempBoard = new Card[size][size];
		// To be implemented
	}
}
