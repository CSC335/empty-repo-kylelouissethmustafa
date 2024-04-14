/**
 * Card class to represent the cards in the memory game
 * 
 * @author Kyle Myint
 */

package model;

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
		if(revealed) {
			revealed = false;
		}else {
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
		if(this.color == other.getColor() && this.shape == other.getShape()) {
			return true;
		}
		return false;
	}
	
	// Getter for color
	public String getColor() {
		return color;
	}
	
	// Getter for shape
	public String getShape() {
		return shape;
	}
	
	// Getter for revealed
	public Boolean getRevealed() {
		return revealed;
	}
	
	// GUI can have a method to turn a card into a object
}
