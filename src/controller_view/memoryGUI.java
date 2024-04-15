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
import model.MemoryGame;


public class memoryGUI extends Application {
	
	private LoginPane loginPane;
	private BoardPane boardPane;
	private LeaderboardPane leaderboardPane;
	
	private MenuItem newGame;
	private MenuItem leaderboard;
	private MemoryGame game;
	
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
		leaderboardPane = new LeaderboardPane(accountCollection);
		
		
		
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
	
	private void addMenu() {
		// see ButtonView from TTTStart
		newGame = new MenuItem("New Game");
		MenuItem other = new MenuItem("Other");
		leaderboard = new MenuItem("Leaderboard");

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
		leaderboard.setOnAction(event -> {
			// When leaderboard is clicked in the menu, switch leaderboardPane to be the center
			
			all.setCenter(leaderboardPane);
			System.out.print("Leaderboard Clicked");			
			
		});
		
		//newGame.setOnAction(event -> {
			// Handle when youre in a different pane and new game is clicked...
			//all.setCenter(boardPane);
		//});
	}


}