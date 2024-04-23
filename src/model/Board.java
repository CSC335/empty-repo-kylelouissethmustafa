package model;

import java.util.Random;
import java.util.ArrayList;

/**
 * Contains information and methods related to the
 * board on which the memory game is played. Uses the
 * Card class as objects in the board
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */
public class Board implements java.io.Serializable {
	// Only square boards - can change later
	private int size;
	private Card[][] board;
	/*
	 * Game modes:
	 *     - 0 = Basic
	 *     - 1 = Odd one out
	 *     - 2 = Three of a kind
	 *     - 3 = Powers
	 */
	private int gameMode;
	
	/*
	 * Card Designs
	 *     - 0 = Shapes/Colors
	 *     - 1 = General
	 *     - Let me know what other card design types you want & the string representations
	 *     - Make sure to run initBoard and shuffle after changing card design
	 *     - TODO: I can make it so you can change the design without changing the board if we need; let the user change in the middle of the game.
	 */
	private int cardDesign;
	
	// Preset to general type
	private String[] type1 = {"1", "2", "3"};
	private String[] type2 = {"A", "B", "C", "D", "E", "F"};
	
	// Green - #028A0F Orange - #FF7300 Yellow - #F6FF00
	
	/**
	 * Constructs a new Board object. Size must be less than or equal to 6.
	 * 
	 * @param size - Size of board (all boards will be square)
	 */
	public Board(int size) {
		if(size > 6) {
			System.out.println("Use sizes <= 6 only.");
			System.exit(0);
			gameMode = 0;
		}else if(size % 2 == 1) {
			System.out.println("Switch to gameMode 1 if trying to play odd 1 out");
			gameMode = 1;
		}
		this.size = size;
		//this.cardDesign = 0;
		//changeDesign(this.cardDesign);
		board = new Card[size][size];
		shuffle();
	}
	
	public int getDesign() {
		return this.cardDesign;
	}
	
	/**
	 * Getter for board size
	 * 
	 * @return - Size of one dimension of board
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 *  Initialize board with random cards based on mode
	 */
	public void initBoard() {
		// Basic game mode set board
		if(gameMode == 0) {
			System.out.println("Initializing normal game.");
			if(size % 2 == 1) {
				System.out.println("Gamemode and size are invalid. Ensure this is reinitialized before shuffle");
			}
			// Keeps track of color and row
			for(int i = 0; i < size; i++) {
				// Keeps track of shape and columns
				for(int j = 0; j < size / 2; j++) {
					Card temp1 = new Card(type2[i], type1[j]);
					Card temp2 = new Card(type2[i], type1[j]);
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
				// Temp = color/shape in order, then puts in board in order L->R, T->B
				Card temp = new Card(type2[(i/2) % 6], type1[(i/12)]);
				board[i/size][i%size] = temp;
			}
			// Set odd - Currently going to be an unused card from the normal deck
			Card odd = new Card(type2[((size*size-1)/2) % 6], type1[((size*size-1)/12)]);
			board[size - 1][size - 1] = odd;
		// 3 of a kind set board (only works with 3x3 or 6x6)
		}else if(gameMode == 2) {
			System.out.println("Initializing 3 of a kind game.");
			if(size % 3 != 0) {
				System.out.println("Gamemode and size are invalid. Ensure this is reinitialized before shuffle");
			}
			// Set normal cards
			for(int i = 0; i < (size * size); i++) {
				// Temp = color/shape in order, then puts in board in order L->R, T->B
				Card temp = new Card(type2[(i/3) % 6], type1[(i/18)]);
				board[i/size][i%size] = temp;
			}
		// Powers set board (works on any board size) power card count = size - 2
		}else if(gameMode == 3) {
			int powerCount = size - 2;
			System.out.println("Initializing powers game.");
			// Set normal cards
			for(int i = 0; i < (size * size) - powerCount; i++) {
				// Temp = color/shape in order, then puts in board in order L->R, T->B
				Card temp = new Card(type2[(i/2) % 6], type1[(i/12)]);
				board[i/size][i%size] = temp;
			}
			// Set power cards
			for(int i = 0; i < powerCount; i++) {
				board[size-1][size-1-i] = new Card("POWER", "POWER");
			}
		}
		
		// Add more game modes when we need
	}
	
	/**
	 * Finds and reveals the matching card of the given coordinate (for Powers)
	 * Run if the second card chosen isn't also a power card; just award like -100 points
	 * or something if a power card match is found
	 * Make sure to keep all 3 cards revealed; the power and the given match
	 * 
	 * @param x - x row of card to match
	 * @param y - y column of card to match
	 */
	public void findMatch(int x, int y) {
		String type2 = board[x][y].getType2();
		String type1 = board[x][y].getType1();
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[i][j].getType2().equals(type2) && board[i][j].getType1().equals(type1)) {
					board[i][j].toggle();
				}
			}
		}
	}
	
	/**
	 * Changes game mode
	 * 
	 * @param gameMode - Game mode to be set to. 0 = normal, 1 = odd one out, 2 = 3 of a kind, 3 = powers
	 */
	public void changeMode(int gameMode) {
		this.gameMode = gameMode;
		initBoard();
	}
	
	// TODO - I think we can now get rid of this...
	/**
	 * Changes card design
	 * Make sure to rerun initBoard and shuffle after changing the design. 
	 * TODO: If we need, I can make it so it just changes the design of the cards without changing the board
	 * 
	 * @param cardType - Card design to use. 0 = shapes/colors, 1 = general
	 */
	public void changeDesign(int cardType) {
		this.cardDesign = cardType;
		String[] shapes = {"Square", "Circle", "Plus"};
		String[] color = {"Red", "Blue", "Green", "Black", "Orange", "Yellow"};
		String[] general1 = {"1", "2", "3"};
		String[] general2 = {"A", "B", "C", "D", "E", "F"};
		
		if(cardType == 0) {
			type1 = shapes;
			type2 = color;
		}else if(cardType == 1) {
			type1 = general1;
			type2 = general2;
		}
	}
	
	/**
	 * Getter for game mode
	 * 
	 * @return - Numerical gameMode
	 */
	public int getMode() {
		return gameMode;
	}
	
	/**
	 * Getter for a card given its coordinates
	 * 
	 * @param x - Row of card
	 * @param y - Column of card
	 * 
	 * @return - Card object at given position
	 */
	public Card getCard(int x, int y) {
		return board[x][y];
	}
	
	/**
	 * Shuffle board (Must be initialized)
	 */
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
	
	/**
	 * Produces string representation of board for debugging
	 * 
	 * @return - String representation of board
	 */
	public String toString() {
		String ret = "";
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				ret += "|" + board[i][j].getType2() + " " + board[i][j].getType1();
			}
			ret += "\n";
		}
		return ret;
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	