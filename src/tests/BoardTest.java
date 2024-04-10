/**
 * Tests the Board class
 * 
 * @author Kyle Myint
 */

package tests;

import model.Board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Scanner;


public class BoardTest {

	@Test
	public void testInit() {
		Board testBoard = new Board(4);
		testBoard.initBoard(4);
		assertEquals("Red", testBoard.getCard(0, 0).getColor());
		assertEquals("Square", testBoard.getCard(0, 0).getShape());
		System.out.println(testBoard.toString());
		Scanner scanner = new Scanner(System.in);
		System.out.println("Does this look like a newly initialized board? Y/N\n");
		assertEquals("Y", scanner.nextLine());
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
		testBoard.initBoard(6);
		testBoard.shuffle();
		System.out.println(testBoard.toString());
		Scanner scan = new Scanner(System.in);
		System.out.println("Does this look like a shuffled board? Y/N\n");
	    String input = scan.nextLine();
	    assertEquals("Y", input);
		scan.close();
	}
	

}

