package tests;

import org.junit.jupiter.api.Test;

import model.Accounts;

import static org.junit.jupiter.api.Assertions.*;

public class AccountsTest {

	@Test
	public void testConstructor() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		assertEquals("Mustafa", account.getUsername());
        assertEquals("Mustafa123", account.getPassword());
        
        assertEquals(0, account.getGamesPlayed());
        
        assertEquals(-1, account.get2x2Score().intValue());
        assertEquals(-1, account.get3x3Score().intValue());
        assertEquals(-1, account.get4x4Score().intValue());
        assertEquals(-1, account.get5x5Score().intValue());
        assertEquals(-1, account.get6x6Score().intValue());
	}
	
	@Test
	public void testSetNewBestScore() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		account.setNewBestScore(100, 2);
		assertEquals(100, account.get2x2Score().intValue());
		
		account.setNewBestScore(80, 3);
		assertEquals(80, account.get3x3Score().intValue());
		
		account.setNewBestScore(60, 4);
		assertEquals(60, account.get4x4Score().intValue());
		
		account.setNewBestScore(40, 5);
		assertEquals(40, account.get5x5Score().intValue());
		
		account.setNewBestScore(20, 6);
		assertEquals(20, account.get6x6Score().intValue());
	}
	
	@Test
	public void testIncrementGamesPlayed () {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		account.incrementGamesPlayed();
		assertEquals(1, account.getGamesPlayed());
	}
	
	@Test
	public void testGetScore() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		assertEquals(-1, account.getScore());
	}
	
	@Test
	public void testGetBestScore() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		account.setNewBestScore(100, 2);
		assertEquals(100, account.getBestScore(2));
		
		account.setNewBestScore(150, 3);
		assertEquals(150, account.getBestScore(3));
		
		account.setNewBestScore(200, 4);
		assertEquals(200, account.getBestScore(4));
		
		account.setNewBestScore(250, 5);
		assertEquals(250, account.getBestScore(5));
		
		account.setNewBestScore(300, 6);
		assertEquals(300, account.getBestScore(6));
		
		assertEquals(-2, account.getBestScore(1));
	}
	
	@Test
	public void testGetBestScoreNotPlayedMode() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		assertEquals(-1, account.getBestScore(4));
	}
	
	@Test
	public void testGetBestScoreInvalidMode() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		assertEquals(-2, account.getBestScore(7));
	}
	
	@Test
	public void testEquals() {
		Accounts account1 = new Accounts("Mustafa", "Mustafa123");
		Accounts account2 = new Accounts("Mustafa", "Mustafa123");
		Accounts account3 = new Accounts("Seth", "Seth123");
		
		assertTrue(account1.equals(account2));
		assertFalse(account1.equals(account3));
	}
}
