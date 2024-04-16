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

public class LeaderboardPane extends BorderPane {
	
	private BorderPane pane;
	
	private Label leaderBoardTitle;
	
	private TableView<Accounts> table;
	private static ObservableList<Accounts> accounts = FXCollections.observableArrayList();
	
	
	private TableColumn<Accounts, String> usernameColumn;
	private TableColumn<Accounts, Integer> scoreColumn;
	
	private AccountCollection accountCollection;
	private AccountCollection croppedCollection;
	
	private int gameMode;
	
	public LeaderboardPane(AccountCollection accountCollection, int gameMode) {
		
		// Setting attributes.
		pane = new BorderPane();
		leaderBoardTitle = new Label("LeaderBoard");
		
		table = new TableView<>();
		
		usernameColumn = new TableColumn<>("Username");
		scoreColumn = new TableColumn<>("Score");
		
		this.gameMode = gameMode;
		this.accountCollection = accountCollection;
		
		usernameColumn.setCellValueFactory(new PropertyValueFactory<Accounts, String>("Username"));
		
		croppedCollection = new AccountCollection();
		
		if(gameMode == 2) {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("2x2Score"));
			croppedCollection.clear();
			for(int i = 0; i < accountCollection.getSize(); i++) {
				if(accountCollection.getElement(i).get2x2Score() >= 0) {
					croppedCollection.add(accountCollection.getElement(i));
				}
			}
		} else if(gameMode == 3) {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("3x3Score"));
			croppedCollection.clear();
			for(int i = 0; i < accountCollection.getSize(); i++) {
				if(accountCollection.getElement(i).get3x3Score() >= 0) {
					croppedCollection.add(accountCollection.getElement(i));
				}
			}
		} else if (gameMode == 4) {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("4x4Score"));
			croppedCollection.clear();
			for(int i = 0; i < accountCollection.getSize(); i++) {
				if(accountCollection.getElement(i).get4x4Score() >= 0) {
					croppedCollection.add(accountCollection.getElement(i));
				}
			}
		} else if (gameMode == 5) {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("5x5Score"));
			croppedCollection.clear();
			for(int i = 0; i < accountCollection.getSize(); i++) {
				if(accountCollection.getElement(i).get5x5Score() >= 0) {
					croppedCollection.add(accountCollection.getElement(i));
				}
			}
		} else if (gameMode == 6) {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("6x6Score"));
			croppedCollection.clear();
			for(int i = 0; i < accountCollection.getSize(); i++) {
				if(accountCollection.getElement(i).get6x6Score() >= 0) {
					croppedCollection.add(accountCollection.getElement(i));
				}
			}
		} else {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("2x2Score"));
			croppedCollection.clear();
			for(int i = 0; i < accountCollection.getSize(); i++) {
				if(accountCollection.getElement(i).get2x2Score() >= 0) {
					croppedCollection.add(accountCollection.getElement(i));
				}
			}
		}
		// Finish these gameMode 2-6
		
		table.setItems(accounts);
		
		table.getColumns().addAll(usernameColumn, scoreColumn);
		
		addAllUsernames();
		resortTable();
		layoutLeaderboard();
		registerHandlers();
	}
	
	public void addMenu() {
		// see ButtonView from TTTStart
	}
	
	
	public void resortTable() {
		// Set default sort column
        table.getSortOrder().add(scoreColumn);
        scoreColumn.setSortType(TableColumn.SortType.ASCENDING);
        table.sort();
	}
	
	public void layoutLeaderboard() {
		table.setPrefSize(100, 100);
		
		usernameColumn.setPrefWidth(220);
		scoreColumn.setPrefWidth(220);
		
		pane.setPadding(new Insets(80, 100, 100, 80));
		leaderBoardTitle.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: black");
		
		pane.setTop(leaderBoardTitle);
		pane.setCenter(table);
		
		this.setCenter(pane);
	}
	
	private void addAllUsernames() {
		accounts.clear();
		for(int i = 0; i < croppedCollection.getSize(); i++) {
			accounts.add(croppedCollection.getElement(i));
		}
	}
	
	public void registerHandlers() {
		
	}
}
