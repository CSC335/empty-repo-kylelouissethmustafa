package model;

/**
 * Card class to represent the cards in the memory game
 * 
 * @author Kyle Myint
 */
public class Card {
	// Characteristics of card to differentiate
	private String color;
	private String shape;
	private Boolean revealed;

	// Constructor
	public Card(String color, String shape) {
		this.color = color;
		this.shape = shape;
		revealed = false;
	}

	/**
	 * Toggles revelaed status of card
	 */
	public void toggle() {
		if (revealed) {
			revealed = false;
		} else {
			revealed = true;
		}
	}

	/**
	 * Checks if a card matches another
	 * 
	 * @param other - The other card to be checked against
	 * 
	 * @return - Boolean on if the cards match
	 */
	public Boolean checkMatch(Card other) {
		if (this.color == other.getColor() && this.shape == other.getShape()) {
			return true;
		}
		return false;
	}

	public Boolean checkMatch(Card other1, Card other2) {
		if (this.color == other1.getColor() && this.color == other2.getColor() && this.shape == other1.getShape()
				&& this.shape == other2.getShape()) {
			return true;
		}
		return false;
	}

	/**
	 * A getter for color of the Card.
	 * 
	 * @return The color of the card.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * A getter for the shape on the Card.
	 * 
	 * @return The shape on the card.
	 */
	public String getShape() {
		return shape;
	}

	/**
	 * Returns whether or not the card is currently revealed.
	 * 
	 * @return True if card is revealed now, false otherwise.
	 */
	public Boolean getRevealed() {
		return revealed;
	}

	// GUI can have a method to turn a card into a object
}
