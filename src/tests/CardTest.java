/**
 * Tests the Card method
 * 
 * @author Kyle Myint
 */

package tests;

import model.Card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Board;

class CardTest {

	@Test
	public void testInit() {
		Card testCard = new Card("Red", "Square");
		assertEquals("Red", testCard.getColor());
		assertEquals("Square", testCard.getShape());
		assertEquals(false, testCard.getRevealed());
	}
	
	@Test
	public void testToggle() {
		Card testCard = new Card("A", "1");
		assertEquals(false, testCard.getRevealed());
		testCard.toggle();
		assertEquals(true, testCard.getRevealed());
	}
	
	@Test
	public void testMatch() {
		Card testCard = new Card("Red", "Square");
		Card testCard2 = new Card("Red", "Square");
		Card testCard3 = new Card("Green", "Circle");
		assertEquals(true, testCard.checkMatch(testCard));
		assertEquals(true, testCard.checkMatch(testCard2));
		assertEquals(false, testCard.checkMatch(testCard3));
		assertEquals(false, testCard2.checkMatch(testCard3));
	}
	
	@Test
	public void testAll() {
		Card testCard = new Card("Red", "Square");
		Card testCard2 = new Card("Red", "Square");
		Card testCard3 = new Card("Green", "Circle");
		Card testCard4 = new Card("Red", "Circle");
		assertEquals(true, testCard.checkMatch(testCard2));
		assertEquals(false, testCard.checkMatch(testCard3));
		assertEquals(false, testCard2.checkMatch(testCard3));
		assertEquals(false, testCard.checkMatch(testCard4));
		testCard2.toggle();
		assertEquals(true, testCard.checkMatch(testCard2));
		testCard.toggle();
		assertEquals(true, testCard.checkMatch(testCard2));
		testCard.toggle();
		assertEquals(true, testCard.checkMatch(testCard2));
	}
}