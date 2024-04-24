/**
 * This class represents an item in the shop that users
 * can purchase to unlock more features for their account
 * 
 *  @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */

package model;

public class shopItem {
	private String itemName;
	private int price;

	public shopItem(String itemName, int price) {
		this.itemName = itemName;
		this.price = price;
	}

	// Getter for itemName
	public String getItemName() {
		return itemName;
	}

	// Getter for item price
	public int getPrice() {
		return price;
	}
}
