// MemoryGame will be tested visually through the GUI

package model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * The MemoryGame class holds info for the MemoryGame. This is the class
 * that the GUI will interact with to show the data.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */
public class MemoryGame extends OurObservable implements java.io.Serializable {
	
	private Board board;
	private int gameMode;
	private int moves;
	private int numCards;
	private int score;
	private ArrayList<Card> revealedCards = new ArrayList<>();
	private int numMatches;
	private boolean isGameActive;
	private int gameTheme; // 0 - Shapes, 1 - Animals
	private Timer timer;
	
	/**
	 * The constructor for memory game, creating a new board
	 * and initializing game stats.
	 * 
	 * @param gameMode The initialized game's game mode.
	 * @param size The board size for the MemoryGame.
	 */
	public MemoryGame(int gameMode, int size, int gameTheme) {
		board = new Board(size);
		numCards = size * size;
		this.gameMode = gameMode;
		System.out.println("Game Mode: " + gameMode);
		moves = 0;
		score = 0;
		this.gameTheme = gameTheme;
	}
	
	/**
	 * This method initializes the board to start the game,
	 * setting game mode, shuffling the board,
	 * and notifying observers.
	 */
	public void initGame() {
		board.changeMode(gameMode);
		board.shuffle();
		System.out.println("Init game called");
		this.isGameActive = true;
		notifyObservers(this);
	}
	
	public int getTheme() {
		return this.gameTheme;
	}
	
	/**
	 * A getter for the game Board.
	 * 
	 * @return The Board for the game.
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Method returns whether or not the game is active.
	 * 
	 * @return True if game is active, false otherwise.
	 */
	public boolean gameActive() {
		return this.isGameActive;
	}
	
	/**
	 * A getter for the number of moves in the game.
	 * 
	 * @return The number of moves in the MemoryGame.
	 */
	public int getNumMoves() {
		return moves;
	}
	
	/**
	 * A getter for the current score of the game.
	 * 
	 * @return The current score of the MemoryGame.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * A getter for the number of cards currently revealed.
	 * 
	 * @return The number of cards currently revealed.
	 */
	public int getNumCardsSelected() {
		return revealedCards.size();
	}
	
	/**
	 * A getter for the total number of cards in the game.
	 * 
	 * @return The total number of cards in the MemoryGame.
	 */
	public int getNumCards() {
		return numCards;
	}
	
	/**
	 * A getter for the game mode of the game.
	 * 
	 * @return The game mode of MemoryGame.
	 */
	public int getGameMode() {
		return gameMode;
	}
	
	/**
	 * A getter for the number of matches in the game.
	 * 
	 * @return The current number of matches in the MemoryGame.
	 */
	public int getNumMatches() {
		return numMatches;
	}
	
	/**
	 * Handles a card being clicked by revealing the card, adding
	 * the card to the array of revealed cards, creating an animation
	 * to flip 2 cards back over if they don't match, and ending the
	 * game when appropriate.
	 * 
	 * @param row The number specifying row of card clicked.
	 * @param col The number specifying col of card clicked.
	 */
	public void cardClicked(int row, int col) {
		Card clickedCard = board.getCard(row, col);
		
		int maxClicked;
		if(this.getGameMode() == 0 | this.getGameMode() == 1) {
			maxClicked = 2;
		} else if(this.getGameMode() == 2) {
			maxClicked = 3;
		} else {
			maxClicked = 2;
		}
		
		if(this.revealedCards.size() < maxClicked && !clickedCard.getRevealed()) {
			clickedCard.toggle();	
			revealedCards.add(board.getCard(row, col));
			
			if(this.revealedCards.size() == maxClicked) {
				moves++;
				int match = 0;
				if(maxClicked == 2) {
					if(this.checkMatch(revealedCards.get(0), revealedCards.get(1))) {
						revealedCards.clear();
						match = 1;
						numMatches++;
					}
				} else if(maxClicked == 3) {
					if(this.checkMatch(revealedCards.get(0), revealedCards.get(1), revealedCards.get(2))) {
						revealedCards.clear();
						match = 1;
						numMatches++;
					}
				}
				if(match == 0) {
					// NEED TO SLEEP HERE FOR 2 Seconds
					PauseTransition pause = new PauseTransition(Duration.seconds(1));
					pause.setOnFinished(event -> {
						System.out.println("Pause finished");
						for(int i = 0; i < maxClicked; i++) {
							revealedCards.get(i).toggle();
						}
						revealedCards.clear();
						notifyObservers(this);	
					});
					pause.play();
				} 
			} else if(gameMode == 1 && (numMatches * 2) + revealedCards.size() == numCards) { // denotes move for odd out card
				moves++;
			}
		}
		
		if(gameMode == 0) {
			if(numMatches == (numCards / 2)) {
				System.out.println("Congrats, you matched them all in " + this.moves + " total moves!");
				this.isGameActive = false;
			}
		} else if(gameMode == 1) {
			if((numMatches * 2) + revealedCards.size() == numCards) {
				System.out.println("Congrats, you matched them all in " + this.moves + " total moves!");
				this.isGameActive = false;
			}
		} else if(gameMode == 2) {
			if((numMatches * 3) == numCards) {
				System.out.println("Congrats, you matched them all in " + this.moves + " total moves!");
				this.isGameActive = false;
			}
		}
		
		updateScore();
		notifyObservers(this);
	}
	
	/**
	 * Updates the score of the game.
	 */
	public void updateScore() {
		this.score = moves * 10;
	}
	
	/**
	 * Determines whether or not two cards match based on
	 * attribute values.
	 * 
	 * @param card1 The first card being compared.
	 * @param card2 The second card being compared.
	 * @return True if the cards match in attribute value, false otherwise.
	 */
	public Boolean checkMatch(Card card1, Card card2) {
		return card1.checkMatch(card2);
	}
	
	public Boolean checkMatch(Card card1, Card card2, Card card3) {
		return card1.checkMatch(card2, card3);
	}
	
	/**
	 * Prints a string representation of the MemoryGame board.
	 */
	public void printBoard() {
		System.out.println(board.toString());
	}
	
	/**
	 * A getter for the size of the MemoryGame board.
	 * 
	 * @return The size of the MemoryGame board.
	 */
	public int getSize() {
		return board.getSize();
	}
}
