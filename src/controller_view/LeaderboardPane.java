package controller_view;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.AccountCollection;
import model.Accounts;


/**
 * This class represents a leaderboard pane in a game application.
 * 
 * The LeaderboardPane class provides functionality to display a leaderboard with scores of accounts
 * based on different game modes. It extends BorderPane and contains UI components including labels, 
 * a table view, and table columns for displaying usernames and scores.  
 * The class includes method to initialize the leaderboard, add usernames, layout the components, 
 * and resort the table based on scores.
 */
public class LeaderboardPane extends BorderPane {
	
	private BorderPane pane;
	
	private Label leaderBoardTitle;
	
	private TableView<Accounts> table;
	private static ObservableList<Accounts> accounts = FXCollections.observableArrayList();
	
	
	private TableColumn<Accounts, String> usernameColumn;
	private TableColumn<Accounts, Integer> scoreColumn;
	
	private AccountCollection accountCollection;
	
	private int gameMode;
	
	
	/**
	 * This is a public method/constructor that constructs a new LeaderboardPane instance.
	 * The constructor initializes a new leaderboard pane with the provided account collection
	 * and game mode. The leaderboard displays the scores of accounts according 
	 * to the specified game mode, ranging from 2x2 grid to 6x6 grid.
	 * 
	 * 
	 * @param accountCollection The collection of accounts to be displayed on the leaderboard. 
	 * @param gameMode The game mode for which the leaderboard is being constructed. 
	 * 				   Valied values are 2 through 6 representing the dimensions of the game grid.
	 */
	public LeaderboardPane(AccountCollection accountCollection, int gameMode) {
		
		// Initialize UI components.
		pane = new BorderPane();
		leaderBoardTitle = new Label("LeaderBoard");
		table = new TableView<>();
		usernameColumn = new TableColumn<>("Username");
		scoreColumn = new TableColumn<>("Score");
		
		// Set provided parameters.
		this.gameMode = gameMode;
		this.accountCollection = accountCollection;
		
		usernameColumn.setCellValueFactory(new PropertyValueFactory<Accounts, String>("Username"));
		
		// Set up the columns based on game mode.
		if(gameMode == 2) {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("2x2Score"));
			
		} else if(gameMode == 3) {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("3x3Score"));
			
		} else if (gameMode == 4) {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("4x4Score"));
			
		} else if (gameMode == 5) {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("5x5Score"));
			
		} else if (gameMode == 6) {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("6x6Score"));
			
		} else {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("2x2Score"));
		}
		// Finish these gameMode 2-6
		
		// Set up table data and columns.
		table.setItems(accounts);
		table.getColumns().addAll(usernameColumn, scoreColumn);
		
		// Populate the table with account data and perform initial sorting (ASCENDING).
		addAllUsernames();
		resortTable();
		
		// Layout the UI components.
		layoutLeaderboard();
		
		// Register event handlers/
		registerHandlers();
	}
	
	public void addMenu() {
		// see ButtonView from TTTStart
	}
	
	/**
	 * This is a public method that sorts the table based on the score column in ascending order.
	 * 
	 * This method sets the sets the default sort column to the score column and sorts the table 
	 * in ascending order based on the scores.
	 */
	public void resortTable() {
		// Set default sort column to score column.
        table.getSortOrder().add(scoreColumn);
        
        // Set sort type to ascending.
        scoreColumn.setSortType(TableColumn.SortType.ASCENDING);
        
        // Perform sorting.
        table.sort();
	}
	
	
	/**
	 * This is a public method that layouts the components of the leaderboard within the pane.
	 * 
	 * This method sets the preferred sizes and positions of the table, columns, and title label 
	 * within the pane. It applies styling to the title label.
	 * 
	 */
	public void layoutLeaderboard() {
		// Set preferred size for the table.
		table.setPrefSize(100, 100);
		
		// Set preferred widths for the columns.
		usernameColumn.setPrefWidth(220);
		scoreColumn.setPrefWidth(220);
		
		// Set padding for the pane and apply styling to the leaderboard title.
		pane.setPadding(new Insets(80, 100, 100, 80));
		leaderBoardTitle.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: black");
		
		// Set the title label at the top and the table at the center of the pane.
		pane.setTop(leaderBoardTitle);
		pane.setCenter(table);
		
		// Set this pane as the center content of the parent container.
		this.setCenter(pane);
	}
	
	
	/**
	 * Adds all usernames from the account collection to the accounts list.
	 * 
	 * This method clears the accounts list and then iterates through the account collection,
	 * adding each account to the accounts list. It is used to populate the leaderboard table 
	 * with usernames before sorting and displaying scores.  
	 */
	private void addAllUsernames() {
		// Clear the accounts list to prepare for adding new usernames.
		accounts.clear();
		
		// Iterate through the content collection and add each account to the account list.
		for(int i = 0; i < accountCollection.getSize(); i++) {
			accounts.add(accountCollection.getElement(i));
		}
	}
	
	public void registerHandlers() {
		
	}
}
