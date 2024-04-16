/**
 * This is the main GUI for the project
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */

package controller_view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AccountCollection;
import model.Accounts;
import model.MemoryGame;


public class memoryGUI extends Application {
	
	private LoginPane loginPane;
	private BoardPane boardPane;
	private LeaderboardPane leaderboard2x2;
	private LeaderboardPane leaderboard3x3;
	private LeaderboardPane leaderboard4x4;
	private LeaderboardPane leaderboard5x5;
	private LeaderboardPane leaderboard6x6;
	
	private MenuItem newGame;
	private Menu leaderboard;
	private MemoryGame game;
	private MenuItem twoByTwo;
	private MenuItem threeByThree;
	private MenuItem fourByFour;
	private MenuItem fiveByFive;
	private MenuItem sixBySix;
	
	private AccountCollection accountCollection;

	public static void main(String[] args) {
		launch(args);
	}

	private BorderPane all;


	@Override
	public void start(Stage primaryStage) throws Exception {
		LayoutGUI();
		
		addMenu();
		
		loginPane = new LoginPane();
		boardPane = new BoardPane(newGame, leaderboard);
		accountCollection = new AccountCollection();
		addTestAccounts();		
		
		registerHandlers();
		
		all.setCenter(boardPane);

		Scene scene = new Scene(all, 850, 650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Memory Game");
		primaryStage.show();
	}

	/**
	 * Layout for the GUI
	 */
	private void LayoutGUI() {
		
		all = new BorderPane();
		
		all.setCenter(boardPane);
		//all.setBottom(loginPane);
	}
	
	private AccountCollection getAcctCollection() {
		return accountCollection;
	}
	
	private void addTestAccounts() {
		Accounts account1 = new Accounts("Seth", "Seth123");
		account1.setNewBestScore(2, 2);
		account1.setNewBestScore(5, 3);
		
		Accounts account2 = new Accounts("Mustafa", "Mustafa123");
		account2.setNewBestScore(5, 2);
		account2.setNewBestScore(2, 3);
		
		
		Accounts account3 = new Accounts("Mustafa2", "Mustafa1");
		account3.setNewBestScore(1, 2);
		account3.setNewBestScore(9, 3);
		
		accountCollection.add(account1);
		accountCollection.add(account2);
		accountCollection.add(account3);
	}
	
	private void addMenu() {
		// see ButtonView from TTTStart
		newGame = new MenuItem("New Game");
		MenuItem other = new MenuItem("Other");
		leaderboard = new Menu("Leaderboard");
		twoByTwo = new MenuItem("2x2");
		threeByThree = new MenuItem("3x3");
		fourByFour = new MenuItem("4x4");
		fiveByFive = new MenuItem("5x5");
		sixBySix = new MenuItem("6x6");
		leaderboard.getItems().addAll(twoByTwo, threeByThree, fourByFour, fiveByFive, sixBySix);

		Menu options = new Menu("Options");
		options.getItems().addAll(newGame, leaderboard, other);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(options);

		all.setTop(menuBar);
	}
	
	public MenuItem getNewGame() {
		return newGame;
	}


	/**
	 * Event handlers
	 */
	private void registerHandlers() {
		twoByTwo.setOnAction(event -> {
			// When leaderboard is clicked in the menu, switch leaderboardPane to be the center
			
			leaderboard2x2 = new LeaderboardPane(accountCollection, 2);
			
			all.setCenter(leaderboard2x2);
			System.out.println("Leaderboard Clicked");			
			
		});
		
		threeByThree.setOnAction(event -> {
			// When leaderboard is clicked in the menu, switch leaderboardPane to be the center
			
			leaderboard3x3 = new LeaderboardPane(accountCollection, 3);
			
			all.setCenter(leaderboard3x3);
			System.out.println("Leaderboard Clicked");			
			
		});
		
		fourByFour.setOnAction(event -> {
			// When leaderboard is clicked in the menu, switch leaderboardPane to be the center
			
			leaderboard4x4 = new LeaderboardPane(accountCollection, 4);
			
			all.setCenter(leaderboard4x4);
			System.out.println("Leaderboard Clicked");			
			
		});
		
		fiveByFive.setOnAction(event -> {
			// When leaderboard is clicked in the menu, switch leaderboardPane to be the center
			
			leaderboard5x5 = new LeaderboardPane(accountCollection, 5);
			
			all.setCenter(leaderboard5x5);
			System.out.println("Leaderboard Clicked");			
			
		});
		
		sixBySix.setOnAction(event -> {
			// When leaderboard is clicked in the menu, switch leaderboardPane to be the center
			
			leaderboard6x6 = new LeaderboardPane(accountCollection, 6);
			
			all.setCenter(leaderboard6x6);
			System.out.println("Leaderboard Clicked");			
			
		});
		
		//newGame.setOnAction(event -> {
			// Handle when youre in a different pane and new game is clicked...
			//all.setCenter(boardPane);
		//});
	}


}