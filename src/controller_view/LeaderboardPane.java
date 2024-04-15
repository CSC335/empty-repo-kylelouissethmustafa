package controller_view;

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

public class LeaderboardPane extends BorderPane {
	
	private BorderPane pane;
	
	private Label leaderBoardTitle;
	
	private TableView<Accounts> table;
	private static ObservableList<Accounts> accounts = FXCollections.observableArrayList();
	
	
	private TableColumn<Accounts, String> usernameColumn;
	private TableColumn<Accounts, String> scoreColumn;
	
	private AccountCollection accountCollection;
	
	public LeaderboardPane(AccountCollection accountCollection) {
		
		// Setting attributes.
		pane = new BorderPane();
		leaderBoardTitle = new Label("LeaderBoard");
		
		table = new TableView<>();
		
		usernameColumn = new TableColumn<>("Username");
		scoreColumn = new TableColumn<>("Score");
		
		this.accountCollection = accountCollection;
		
		Accounts account1 = new Accounts("Seth", "Seth123");
		
		
		usernameColumn.setCellValueFactory(new PropertyValueFactory<Accounts, String>("Username"));
		scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, String>("Score"));
		
		table.setItems(accounts);
		
		table.getColumns().addAll(usernameColumn, scoreColumn);
		
		addAllUsernames();
		layoutLeaderboard();
		registerHandlers();
	}
	
	public void addMenu() {
		// see ButtonView from TTTStart
	}
	
	public void layoutLeaderboard() {
		table.setPrefSize(400, 400);
		
		pane.setPadding(new Insets(20, 20, 20, 20));
		leaderBoardTitle.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: black");
		
		pane.setTop(leaderBoardTitle);
		pane.setCenter(table);
		
		this.setCenter(pane);
	}
	
	private static void addAllUsernames() {
		Accounts account1 = new Accounts("Seth", "Seth123");
		account1.setNewBestScore(2, 2);
		accounts.add(account1);
	}
	
	public void registerHandlers() {
		
	}
}
