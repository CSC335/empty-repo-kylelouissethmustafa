package model;

/**
 * Card class to represent the cards in the memory game
 * 
 * @author Kyle Myint
 */
public class Card implements java.io.Serializable {
	// Characteristics of card to differentiate
	private String type1; // was shape
	private String type2; // was color
	private Boolean revealed;

	// Constructor
	public Card(String type2, String type1) {
		this.type1 = type1;
		this.type2 = type2;
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
		if (this.type2 == other.getType2() && this.type1 == other.getType1()) {
			return true;
		}
		return false;
	}

	public Boolean checkMatch(Card other1, Card other2) {
		if (this.type2 == other1.getType2() && this.type2 == other2.getType2() && this.type1 == other1.getType1()
				&& this.type1 == other2.getType1()) {
			return true;
		}
		return false;
	}

	/**
	 * A getter for color of the Card.
	 * 
	 * @return The color of the card.
	 */
	public String getType2() {
		return type2;
	}

	/**
	 * A getter for the shape on the Card.
	 * 
	 * @return The shape on the card.
	 */
	public String getType1() {
		return type1;
	}

	/**
	 * Returns whether or not the card is currently revealed.
	 * 
	 * @return True if card is revealed now, false otherwise.
	 */
	public Boolean getRevealed() {
		return revealed;
	}
	
	public Boolean isPower() {
		return this.type1 == "POWER" && this.type2 == "POWER";
	}

	// GUI can have a method to turn a card into a object
}
