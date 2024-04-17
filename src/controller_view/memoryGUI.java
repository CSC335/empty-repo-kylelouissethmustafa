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
	private StatsPane statsPane;
	private LeaderboardPane leaderboard2x2;
	private LeaderboardPane leaderboard3x3;
	private LeaderboardPane leaderboard4x4;
	private LeaderboardPane leaderboard5x5;
	private LeaderboardPane leaderboard6x6;
	
	private MenuBar menuBar;
	private MenuItem logout;
	private Menu newGame;
	private MenuItem twoGame;
	private MenuItem threeGame;
	private MenuItem fourGame;
	private MenuItem fiveGame;
	private MenuItem sixGame;
	private Menu leaderboard;
	private MemoryGame game;
	private MenuItem twoByTwo;
	private MenuItem threeByThree;
	private MenuItem fourByFour;
	private MenuItem fiveByFive;
	private MenuItem sixBySix;
	private MenuItem userStats;
	
	private Accounts currAcct;
	
	private AccountCollection accountCollection;

	public static void main(String[] args) {
		launch(args);
	}

	private BorderPane all;


	@Override
	public void start(Stage primaryStage) throws Exception {
		accountCollection = new AccountCollection();
		loginPane = new LoginPane(currAcct, accountCollection, this);
		boardPane = new BoardPane(newGame, leaderboard, this);
		statsPane = new StatsPane(this);
		
		LayoutGUI();
		
		addMenu();
		
		addTestAccounts(); // TODO - remove this
		
		registerHandlers();

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
		
		all.setCenter(loginPane);
		//all.setBottom(loginPane);
	}
	
	private AccountCollection getAcctCollection() {
		return accountCollection;
	}
	
	public void onSuccessfulLogin(Accounts loginAcct) {
		boardPane.clearCanvas();
		all.setTop(menuBar);
		all.setCenter(boardPane);
		this.currAcct = accountCollection.getAccount(loginAcct.getUsername(), loginAcct.getPassword());
		System.out.println("Current User: " + currAcct.getUsername());
	}
	
	public Accounts getCurrAcct() {
		return this.currAcct;
	}
	
	private void addTestAccounts() {
		Accounts account1 = new Accounts("Seth", "Seth123");
		account1.setNewBestScore(20, 2);
		account1.setNewBestScore(50, 3);
		
		Accounts account2 = new Accounts("Mustafa", "Mustafa123");
		account2.setNewBestScore(50, 2);
		account2.setNewBestScore(20, 3);
		
		
		Accounts account3 = new Accounts("Mustafa2", "Mustafa1");
		account3.setNewBestScore(10, 2);
		account3.setNewBestScore(90, 3);
		
		accountCollection.add(account1);
		accountCollection.add(account2);
		accountCollection.add(account3);
	}
	
	private void addMenu() {
		// see ButtonView from TTTStart
		newGame = new Menu("New Game");
		twoGame = new MenuItem("2x2");
		threeGame = new MenuItem("3x3");
		fourGame = new MenuItem("4x4");
		fiveGame = new MenuItem("5x5");
		sixGame = new MenuItem("6x6");
		newGame.getItems().addAll(twoGame, threeGame, fourGame, fiveGame, sixGame);
		MenuItem other = new MenuItem("Other");
		leaderboard = new Menu("Leaderboard");
		twoByTwo = new MenuItem("2x2");
		threeByThree = new MenuItem("3x3");
		fourByFour = new MenuItem("4x4");
		fiveByFive = new MenuItem("5x5");
		sixBySix = new MenuItem("6x6");
		leaderboard.getItems().addAll(twoByTwo, threeByThree, fourByFour, fiveByFive, sixBySix);
		logout = new MenuItem("Logout");

		Menu options = new Menu("Options");
		userStats = new MenuItem("User Stats");
		options.getItems().addAll(newGame, leaderboard, logout, userStats, other);
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(options);
	}
	
	public MenuItem getNewGame() {
		return newGame;
	}


	/**
	 * Event handlers
	 */
	private void registerHandlers() {
		logout.setOnAction(event -> {
			currAcct = null;
			all.setTop(null);
			all.setCenter(loginPane);
			loginPane.logout();
		});
		
		userStats.setOnAction(event -> {
			statsPane.layoutStatsPane();
			all.setCenter(statsPane);
		});
		
		
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
		
		twoGame.setOnAction(event -> {
			if(currAcct != null) {
				all.setCenter(boardPane);
				boardPane.startNewGame(0, 2);
			}
		});
		
		threeGame.setOnAction(event -> {
			if(currAcct != null) {
				all.setCenter(boardPane);
				boardPane.startNewGame(1, 3);
			}
		});
		
		fourGame.setOnAction(event -> {
			if(currAcct != null) {
				all.setCenter(boardPane);
				boardPane.startNewGame(0, 4);
			}
		});
		
		fiveGame.setOnAction(event -> {
			if(currAcct != null) {
				all.setCenter(boardPane);
				boardPane.startNewGame(1, 5);
			}
		});
		
		sixGame.setOnAction(event -> {
			if(currAcct != null) {
				all.setCenter(boardPane);
				boardPane.startNewGame(0, 6);
			}
		});
	}


}