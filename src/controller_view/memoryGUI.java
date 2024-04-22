/**
 * This is the main GUI for the project, which displays various panes
 * and menus within it as the application is used.
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
	private SettingsPane settingsPane;
	private LeaderboardPane leaderboardPane;
	private LeaderboardPane leaderboard3x3;
	private LeaderboardPane leaderboard4x4;
	private LeaderboardPane leaderboard5x5;
	private LeaderboardPane leaderboard6x6;
	
	private MenuBar menuBar;
	private MenuItem logout;
	private MenuItem newGame;
	private MenuItem leaderboard;
	private MemoryGame game;
	private MenuItem userStats;
	private MenuItem gameSettings;
	
	private Accounts currAcct;
	
	private AccountCollection accountCollection;
	
	private int gameDim;
	private int gameMode;

	public static void main(String[] args) {
		launch(args);
	}

	private BorderPane all;


	@Override
	/**
	 * This method starts the GUI, calling methods to layout the GUI,
	 * adding the menu, and registering handlers.
	 */
	public void start(Stage primaryStage) throws Exception {
		accountCollection = new AccountCollection();
		loginPane = new LoginPane(currAcct, accountCollection, this);
		boardPane = new BoardPane(this);
		statsPane = new StatsPane(this);
		settingsPane = new SettingsPane(this);
		
		LayoutGUI();
		
		addMenu();
		
		addTestAccounts(); // TODO - remove this for final production.
		
		setupGameSettings(2, 0);
		
		registerHandlers();

		Scene scene = new Scene(all, 850, 650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Memory Game");
		primaryStage.show();
	}

	/**
	 * This method lays out the gui by setting the loginPane
	 * to the center initially.
	 */
	private void LayoutGUI() {
		all = new BorderPane();
		
		all.setCenter(loginPane);
	}
	
	/**
	 * A getter for the accountCollection.
	 * 
	 * @return the collection of accounts in the application
	 */
	private AccountCollection getAcctCollection() {
		return accountCollection;
	}
	
	/**
	 * The method that handles a successful login, setting up a menu and
	 * clear canvas, as well as setting the current user.
	 * 
	 * @param loginAcct The account that just successfully logged in.
	 */
	public void onSuccessfulLogin(Accounts loginAcct) {
		boardPane.clearCanvas();
		all.setTop(menuBar);
		all.setCenter(boardPane);
		this.currAcct = accountCollection.getAccount(loginAcct.getUsername(), loginAcct.getPassword());
		System.out.println("Current User: " + currAcct.getUsername());
	}
	
	/**
	 * A getter for the current user's account.
	 * 
	 * @return The current user's account.
	 */
	public Accounts getCurrAcct() {
		return this.currAcct;
	}
	
	private void addTestAccounts() {
		Accounts account1 = new Accounts("Seth", "Seth123");
		account1.setNewBestScore(20, 2, 0);
		account1.setNewBestScore(50, 3, 1);
		
		Accounts account2 = new Accounts("Mustafa", "Mustafa123");
		account2.setNewBestScore(50, 2, 0);
		account2.setNewBestScore(20, 3, 1);
		
		
		Accounts account3 = new Accounts("Mustafa2", "Mustafa1");
		account3.setNewBestScore(10, 2, 0);
		account3.setNewBestScore(90, 3, 1);
		
		accountCollection.add(account1);
		accountCollection.add(account2);
		accountCollection.add(account3);
	}
	
	private void addMenu() {
		// see ButtonView from TTTStart
		newGame = new MenuItem("New Game");
		MenuItem other = new MenuItem("Other");
		leaderboard = new MenuItem("Leaderboard");
		logout = new MenuItem("Logout");

		Menu options = new Menu("Options");
		userStats = new MenuItem("User Stats");
		gameSettings = new MenuItem("Game Settings");
		options.getItems().addAll(newGame, gameSettings, leaderboard, userStats, logout, other);
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(options);
	}
	
	/**
	 * A getter for newGame.
	 * 
	 * @return The new game.
	 */
	public MenuItem getNewGame() {
		return newGame;
	}
	
	public void setupGameSettings(int dim, int mode) {
		this.gameDim = dim;
		this.gameMode = mode;
	}


	/**
	 * This method registers event handlers, including logout, click of UserStats in menu,
	 * clicks of new game for each game mode,and clicks of leaderboard for each game mode.
	 * 
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
		
		gameSettings.setOnAction(event -> {
			all.setCenter(settingsPane);
		});
		
		leaderboard.setOnAction(event -> {
			// When leaderboard is clicked in the menu, switch leaderboardPane to be the center
			
			leaderboardPane = new LeaderboardPane(accountCollection);
			
			all.setCenter(leaderboardPane);
			System.out.println("Leaderboard Clicked");			
			
		});
		
		newGame.setOnAction(event -> {
			if(currAcct != null) {
				all.setCenter(boardPane);
				int curDim = this.gameDim;
				int curMode = this.gameMode;
				
				boardPane.startNewGame(curMode, curDim);
			}
		});
	}
}