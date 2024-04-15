/**
 * Holds info for the MemoryGame. This is the class
 * that the GUI will interact with to show the data
 * 
 * @author Kyle Myint
 */

// MemoryGame will be tested visually through the GUI

package model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class MemoryGame extends OurObservable {
	
	private Board board;
	private int gameMode;
	private int moves;
	private int numCards;
	private int score;
	private ArrayList<Card> revealedCards = new ArrayList<>();
	private int numMatches;
	private boolean isGameActive;
	private Timer timer;
	
	public MemoryGame(int gameMode, int size) {
		board = new Board(size);
		numCards = size * size;
		this.gameMode = gameMode;
		moves = 0;
		score = 0;
	}
	
	// Initializes the board to start the game
	public void initGame() {
		board.changeMode(gameMode);
		//board.initBoard();
		board.shuffle();
		System.out.println("Init game called");
		this.isGameActive = true;
		notifyObservers(this);
	}
	
	public Board getBoard() {
		return board;
	}
	
	public boolean gameActive() {
		return this.isGameActive;
	}
	
	public int getNumMoves() {
		return moves;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getNumCardsSelected() {
		return revealedCards.size();
	}
	
	public int getNumCards() {
		return numCards;
	}
	
	public int getGameMode() {
		return gameMode;
	}
	
	public int getNumMatches() {
		return numMatches;
	}
	
	public void cardClicked(int row, int col) {
		Card clickedCard = board.getCard(row, col);
		
		if(this.revealedCards.size() < 2 && !clickedCard.getRevealed()) {
			clickedCard.toggle();	
			revealedCards.add(board.getCard(row, col));
			
			if(this.revealedCards.size() == 2) {
				moves++;
				if(this.checkMatch(revealedCards.get(0), revealedCards.get(1))) {
					System.out.println("You found a match!");
					revealedCards.clear();
					numMatches++;
				} else {
					// NEED TO SLEEP HERE FOR 2 Seconds
					PauseTransition pause = new PauseTransition(Duration.seconds(1));
					pause.setOnFinished(event -> {
						System.out.println("Pause finished");
						revealedCards.get(0).toggle();
						revealedCards.get(1).toggle();
						revealedCards.clear();
						System.out.println("Sorry, this is not a match!");
						notifyObservers(this);	
					});
					pause.play();
				}
			}
		}
		
		if(numMatches == (numCards / 2)) {
			System.out.println("Congrats, you matched them all in " + this.moves + " total moves!");
			this.isGameActive = false;
		}
		
		updateScore();
		notifyObservers(this);
	}
	
	public void updateScore() {
		this.score = moves * 10;
	}
	
	// TODO - add to this in the future for end of game...
	//public void endGame() {
		// Will update account/leaderboard.
	//}
	
	public Boolean checkMatch(Card card1, Card card2) {
		return card1.checkMatch(card2);
	}
	
	public void printBoard() {
		System.out.println(board.toString());
	}
	
	public int getSize() {
		return board.getSize();
	}
}
