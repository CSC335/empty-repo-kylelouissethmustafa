/**
 * Contains information and methods related to the
 * board on which the memory game is played. Uses the
 * Card class as objects in the board
 * 
 * @author Kyle Myint
 */

package model;

import java.util.Random;
import java.util.ArrayList;

public class Board {
	// Only square boards - can change later
	private int size;
	private Card[][] board;
	/*
	 * Game modes:
	 *     - 0 = Basic
	 *     - 1 = Odd one out
	 */
	private int gameMode;
	
	private String[] shapes = {"Square", "Circle", "Plus"};
	private String[] colors = {"Red", "Blue", "Green", "Black", "Orange", "Yellow"};
	
	// Constructor **SIZE MUST BE EVEN & LESS THAN / EQUAL TO 6**
	public Board(int size) {
		if(size > 6) {
			System.out.println("Use sizes <= 6 only.");
			System.exit(0);
		}else if(size % 2 == 1) {
			System.out.println("Switch to gameMode 1 if trying to play odd 1 out");
		}
		this.size = size;
		board = new Card[size][size];
		gameMode = 0;
		shuffle();
	}
	
	public int getSize() {
		return this.size;
	}
	
	// Initialize board with random cards based on mode
	public void initBoard() {
		// Basic game mode set board
		if(gameMode == 0) {
			System.out.println("Initializing normal game.");
			if(size % 2 == 1) {
				System.out.println("Gamemode and size are invalid. Ensure this is reinitialized before shuffle");
			}
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
		// Odd one out set board
		}else if(gameMode == 1) {
			System.out.println("Initializing odd one out game.");
			if(size % 2 == 0) {
				System.out.println("Gamemode and size are invalid. Ensure this is reinitialized before shuffle");
			}
			// Set normal cards
			for(int i = 0; i < (size * size) - 1; i++) {
				Card temp = new Card(colors[(i/2) % 6], shapes[(i/2) % 3]);
				board[i/size][i%size] = temp;
			}
			// Set odd - Do we want it to be a unique card or a random extra of the normal ones?
			Card odd = new Card("ODD", "ODD");
			board[size - 1][size - 1] = odd;
		}
		
		// Add more game modes when we need
	}
	
	// Change game mode
	public void changeMode(int gameMode) {
		this.gameMode = gameMode;
		initBoard();
	}
	
	// Getter
	public int getMode() {
		return gameMode;
	}
	
	// Getter
	public Card getCard(int x, int y) {
		return board[x][y];
	}
	
	// Shuffle board (Must be initialized)
	public void shuffle() {
		System.out.println("starting shuffle");
		Card[][] tempBoard = new Card[size][size];
        ArrayList<Card> cardList = new ArrayList<Card>();
        // Copy cards from the 2D array to the list
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                cardList.add(board[i][j]);
            }
        }
		// Randomize entry into new board
        Random random = new Random();
        int x = 0;
        int y = 0;
        while(cardList.size() != 0) {
        	int index = random.nextInt(cardList.size());
        	tempBoard[x][y] = cardList.get(index);
        	cardList.remove(index);
        	y++;
        	if(y == size) {
        		y = 0;
        		x++;
        	}
        }
        board = tempBoard;
        System.out.println("End Shuffle");
	}
	
	// Return string representation of board
	public String toString() {
		String ret = "";
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				ret += "|" + board[i][j].getColor() + " " + board[i][j].getShape();
			}
			ret += "\n";
		}
		return ret;
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	