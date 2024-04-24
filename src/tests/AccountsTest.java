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
        
        assertEquals(-1, account.get2Normal().intValue());
        assertEquals(-1, account.get3Odd().intValue());
        assertEquals(-1, account.get4Normal().intValue());
        assertEquals(-1, account.get5Odd().intValue());
        assertEquals(-1, account.get6Normal().intValue());
	}
	
	@Test
	public void testSetNewBestScore() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		account.setNewBestScore(100, 2, 0);
		assertEquals(100, account.get2Normal().intValue());
		
		account.setNewBestScore(80, 3, 1);
		assertEquals(80, account.get3Odd().intValue());
		
		account.setNewBestScore(60, 4, 0);
		assertEquals(60, account.get4Normal().intValue());
		
		account.setNewBestScore(40, 5, 1);
		assertEquals(40, account.get5Odd().intValue());
		
		account.setNewBestScore(20, 6, 0);
		assertEquals(20, account.get6Normal().intValue());
	}
	
	@Test
	public void testIncrementGamesPlayed () {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		account.incrementGamesPlayed();
		assertEquals(1, account.getGamesPlayed());
	}
	
	@Test
	public void testGetBestScore() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		account.setNewBestScore(100, 2, 0);
		assertEquals(100, account.getBestScore(2, 0));
		
		account.setNewBestScore(150, 3, 1);
		assertEquals(150, account.getBestScore(3, 1));
		
		account.setNewBestScore(200, 4, 0);
		assertEquals(200, account.getBestScore(4, 0));
		
		account.setNewBestScore(250, 5, 1);
		assertEquals(250, account.getBestScore(5, 1));
		
		account.setNewBestScore(300, 6, 0);
		assertEquals(300, account.getBestScore(6, 0));
		
		assertEquals(-2, account.getBestScore(1, 0));
	}
	
	@Test
	public void testGetBestScoreNotPlayedMode() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		assertEquals(-1, account.getBestScore(4, 0));
	}
	
	@Test
	public void testGetBestScoreInvalidMode() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		assertEquals(-2, account.getBestScore(7, 0));
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
