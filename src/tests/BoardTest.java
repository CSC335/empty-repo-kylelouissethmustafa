/**
 * Tests the Board class.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */

package tests;

import model.Board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.util.Scanner;


public class BoardTest {

	Scanner scanner = new Scanner(System.in);

	@Test
	public void testInit() {
		Board testBoard = new Board(4);
		testBoard.initBoard();
		assertEquals("Red", testBoard.getCard(0, 0).getColor());
		assertEquals("Square", testBoard.getCard(0, 0).getShape());
		assertEquals(4, testBoard.getSize());
		System.out.println(testBoard.toString());
		System.out.println("Does this look like a newly initialized board? Y/N\n");
		String input = scanner.nextLine();
		assertEquals("Y", input);
	}
	
	@Test
	public void testMode() {
		Board testBoard = new Board(4);
		assertEquals(0, testBoard.getMode());
		testBoard.changeMode(2);
		assertEquals(2, testBoard.getMode());
	}
	
	@Test
	public void testShuffle() {
		Board testBoard = new Board(6);
		testBoard.initBoard();
		testBoard.shuffle();
		System.out.println(testBoard.toString());
		System.out.println("Does this look like a shuffled board? Y/N\n");
	    String input = scanner.nextLine();
	    assertEquals("Y", input);
	}
	
	@Test
	public void testOdd() {
		Board testBoard = new Board(5);
		testBoard.changeMode(1);
		testBoard.initBoard();
		testBoard.shuffle();
		System.out.println(testBoard.toString());
		System.out.println("Does this look like a shuffled odd one out board? Y/N\n");
	    String input = scanner.nextLine();
	    assertEquals("Y", input);
	}

}

