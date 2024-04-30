/**
 * Tests the MemoryGame class.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Accounts;
import model.Card;
import model.MemoryGame;

class MemoryGameTest {

	@Test
	void testInit() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		MemoryGame game = new MemoryGame(0, 2, 0, account);
		game.initGame();
		game.printBoard();
		assertEquals(true, game.gameActive());
		assertEquals(0, game.getScore());
		assertEquals(0, game.getNumCardsSelected());
		assertEquals(0, game.getNumMoves());
		assertEquals(2, game.getSize());
		assertEquals(4, game.getNumCards());
		assertEquals(0, game.getGameMode());
		assertEquals(0, game.getNumMatches());
	}
	
	@Test
	void testGoodGuess() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		MemoryGame game = new MemoryGame(0, 2, 0, account);
		game.initGame();
		Card card1 = game.getBoard().getCard(0, 0);
		Card card2 = game.getBoard().getCard(0, 1);
		Card card3 = game.getBoard().getCard(1, 0);
		if(game.checkMatch(card1, card2)) {
			game.cardClicked(0, 0, 1);
			assertEquals(1, game.getNumCardsSelected());
			game.cardClicked(0, 1, 1);
		} else if(game.checkMatch(card1, card3)) {
			game.cardClicked(0, 0, 1);
			assertEquals(1, game.getNumCardsSelected());
			game.cardClicked(1, 0, 1);
		} else if(game.checkMatch(card3, card2)) {
			game.cardClicked(1, 0, 1);
			assertEquals(1, game.getNumCardsSelected());
			game.cardClicked(0, 1, 1);
		}
		
		assertEquals(1, game.getNumMoves());
		assertEquals(10, game.getScore());
		assertEquals(1, game.getNumMatches());
		assertEquals(1, game.getBestStreak());
		assertEquals(true, game.gameActive());
	}
	
	@Test
	public void testTheme() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		MemoryGame newGame = new MemoryGame(0, 2, 0, account);
		
		assertEquals(0, newGame.getTheme());
		
		MemoryGame newGame2 = new MemoryGame(0, 2, 1, account);
		
		assertEquals(1, newGame2.getTheme());
	}
	
//	@Test
//	void testBadGuess() {
//		MemoryGame game = new MemoryGame(0, 2, 0);
//		game.initGame();
//		Card card1 = game.getBoard().getCard(0, 0);
//		Card card2 = game.getBoard().getCard(0, 1);
//		Card card3 = game.getBoard().getCard(1, 0);
//		if(!game.checkMatch(card1, card2)) {
//			game.cardClicked(0, 0, 1);
//			assertEquals(1, game.getNumCardsSelected());
//			game.cardClicked(0, 1, 1);
//			assertEquals(2, game.getNumCardsSelected());
//		} else if(!game.checkMatch(card1, card3)) {
//			game.cardClicked(0, 0, 1);
//			assertEquals(1, game.getNumCardsSelected());
//			game.cardClicked(1, 0, 1);
//			assertEquals(2, game.getNumCardsSelected());
//		} else if(!game.checkMatch(card3, card2)) {
//			game.cardClicked(1, 0, 1);
//			assertEquals(1, game.getNumCardsSelected());
//			game.cardClicked(0, 1, 1);
//			assertEquals(2, game.getNumCardsSelected());
//		}
//		
//		assertEquals(1, game.getNumMoves());
//		assertEquals(10, game.getScore());
//		assertEquals(0, game.getNumMatches());
//		assertEquals(true, game.gameActive());
//	}
	
//	@Test
//	void testFlipBack() {
//		MemoryGame game = new MemoryGame(0, 2, 0);
//		game.initGame();
//		Card card1 = game.getBoard().getCard(0, 0);
//		Card card2 = game.getBoard().getCard(0, 1);
//		Card card3 = game.getBoard().getCard(1, 0);
//		if(!game.checkMatch(card1, card2)) {
//			//game.cardClicked(0, 0, 1);
//			//assertEquals(1, game.getNumCardsSelected());
//			//game.cardClicked(0, 1, 1);
//			//assertEquals(2, game.getNumCardsSelected());
//			
//			game.cardClicked(0, 0, 1);
//			assertEquals(1, game.getNumCardsSelected());
//			game.cardClicked(0, 1, 1);
//			assertEquals(0, game.getNumCardsSelected());
//		} else if(!game.checkMatch(card1, card3)) {
//			//game.cardClicked(0, 0, 1);
//			//assertEquals(1, game.getNumCardsSelected());
//			//game.cardClicked(1, 0, 0);
//			//assertEquals(2, game.getNumCardsSelected());
//			
//			game.cardClicked(0, 0, 1);
//			assertEquals(1, game.getNumCardsSelected());
//			game.cardClicked(1, 0, 1);
//			assertEquals(0, game.getNumCardsSelected());
//		} else if(!game.checkMatch(card3, card2)) {
//			//game.cardClicked(1, 0, 1);
//			//assertEquals(1, game.getNumCardsSelected());
//			//game.cardClicked(0, 1, 1);
//			//assertEquals(2, game.getNumCardsSelected());
//			
//			game.cardClicked(1, 0, 1);
//			assertEquals(1, game.getNumCardsSelected());
//			game.cardClicked(0, 1, 1);
//			assertEquals(0, game.getNumCardsSelected());
//		}
//		
//		assertEquals(1, game.getNumMoves());
//		assertEquals(10, game.getScore());
//		assertEquals(0, game.getNumMatches());
//		assertEquals(true, game.gameActive());
//	}
	
//	@Test
//	void testNoThirdClick() {
//		MemoryGame game = new MemoryGame(0, 2, 0);
//		game.initGame();
//		Card card1 = game.getBoard().getCard(0, 0);
//		Card card2 = game.getBoard().getCard(0, 1);
//		Card card3 = game.getBoard().getCard(1, 0);
//		if(!game.checkMatch(card1, card2)) {
//			game.cardClicked(0, 0, 1);
//			assertEquals(1, game.getNumCardsSelected());
//			game.cardClicked(0, 1, 1);
//			assertEquals(2, game.getNumCardsSelected());
//			
//			game.cardClicked(1, 0, 1);
//			assertEquals(2, game.getNumCardsSelected());
//		} else if(!game.checkMatch(card1, card3)) {
//			game.cardClicked(0, 0, 1);
//			assertEquals(1, game.getNumCardsSelected());
//			game.cardClicked(1, 0, 1);
//			assertEquals(2, game.getNumCardsSelected());
//			
//			game.cardClicked(0, 1, 1);
//			assertEquals(2, game.getNumCardsSelected());
//		} else if(!game.checkMatch(card3, card2)) {
//			game.cardClicked(1, 0, 1);
//			assertEquals(1, game.getNumCardsSelected());
//			game.cardClicked(0, 1, 1);
//			assertEquals(2, game.getNumCardsSelected());
//			
//			game.cardClicked(0, 0, 1);
//			assertEquals(2, game.getNumCardsSelected());
//		}
//		
//		assertEquals(1, game.getNumMoves());
//		assertEquals(10, game.getScore());
//		assertEquals(0, game.getNumMatches());
//		assertEquals(true, game.gameActive());
//	}
	
	@Test
	void testProperTermination() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		MemoryGame game = new MemoryGame(0, 2, 0, account);
		game.initGame();
		Card card1 = game.getBoard().getCard(0, 0);
		Card card2 = game.getBoard().getCard(0, 1);
		Card card3 = game.getBoard().getCard(1, 0);
		Card card4 = game.getBoard().getCard(1, 1);
		if(game.checkMatch(card1, card2)) {
			game.cardClicked(0, 0, 1);
			assertEquals(1, game.getNumCardsSelected());
			game.cardClicked(0, 1, 1);
			
			assertEquals(0, game.getNumCardsSelected());
			assertEquals(1, game.getNumMoves());
			assertEquals(10, game.getScore());
			assertEquals(1, game.getNumMatches());
			assertEquals(true, game.gameActive());
			
			game.cardClicked(1, 0, 1);
			assertEquals(1, game.getNumCardsSelected());
			game.cardClicked(1, 1, 1);
		} else if(game.checkMatch(card1, card3)) {
			game.cardClicked(0, 0, 1);
			assertEquals(1, game.getNumCardsSelected());
			game.cardClicked(1, 0, 1);
			
			assertEquals(0, game.getNumCardsSelected());
			assertEquals(1, game.getNumMoves());
			assertEquals(10, game.getScore());
			assertEquals(1, game.getNumMatches());
			assertEquals(true, game.gameActive());
			
			game.cardClicked(0, 1, 1);
			assertEquals(1, game.getNumCardsSelected());
			game.cardClicked(1, 1, 1);
		} else if(game.checkMatch(card3, card2)) {
			game.cardClicked(1, 0, 1);
			assertEquals(1, game.getNumCardsSelected());
			game.cardClicked(0, 1, 1);
			
			assertEquals(0, game.getNumCardsSelected());
			assertEquals(1, game.getNumMoves());
			assertEquals(10, game.getScore());
			assertEquals(1, game.getNumMatches());
			assertEquals(true, game.gameActive());
			
			game.cardClicked(0, 0, 1);
			assertEquals(1, game.getNumCardsSelected());
			game.cardClicked(1, 1, 1);
		}
		
		assertEquals(2, game.getNumMoves());
		assertEquals(20, game.getScore());
		assertEquals(2, game.getNumMatches());
		assertEquals(false, game.gameActive());
	}

}
