package model;

public class MemoryGame {
	
	private Board board;
	private int gameMode;
	private int moves;
	private int score;
	
	public MemoryGame(int gameMode, int size) {
		board = new Board(size);
		this.gameMode = gameMode;
		moves = size * (size/2) + size;
		score = 0;
	}
	
	// Initializes the board to start the game
	public void initGame() {
		board.changeMode(gameMode);
		board.initBoard(gameMode);
		board.shuffle();
	}
	
	public void endGame() {
		score = moves * 10;
		// Save to user account & update leaderboards
	}
	
	// Checks if the given coordinates are matching
	public Boolean checkMatch(int x1, int y1, int x2, int y2) {
		Card c1 = board.getCard(x1, y1);
		Card c2 = board.getCard(x2, y2);
		if(c1.checkMatch(c2)) {
			// Do stuff, mark revealed and add score or wtv
			return true;
		}
		return false;
	}
}
