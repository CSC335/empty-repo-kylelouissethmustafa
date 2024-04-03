package model;

public class Card {
	// Characteristics of card to differentiate
	// Can change to wtv we want later
	private String color;
	private String shape;
	private Boolean revealed;
	
	// Constructor
	public Card(String color, String shape) {
		this.color = color;
		this.shape = shape;
		revealed = false;
	}
	
	// Toggles revealed status
	public void toggle() {
		if(revealed) {
			revealed = false;
		}else {
			revealed = true;
		}
	}
	
	// Returns boolean if this card matches other card
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
	
	// GUI can have a method to turn a card into a object
}
