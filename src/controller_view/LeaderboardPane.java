package controller_view;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class LeaderboardPane extends BorderPane {
	
	private GridPane pane;
	
	private Label leaderBoardTitle;
	
	private TableView<String> tableView;
	private TableColumn<String, String> firstColumn;
	private TableColumn<String, String> secondColumn;
	
	
	public LeaderboardPane() {
		
		pane = new GridPane();
		
		leaderBoardTitle = new Label("Leader Board");
		
		firstColumn = new TableColumn<>("Username");
		secondColumn = new TableColumn<>("Score");
		
		firstColumn.setCellValueFactory(new PropertyValueFactory<String, String>("Username"));
		secondColumn.setCellValueFactory();
		
		layoutLeaderboard();
		registerHandlers();
	}
	
	public void addMenu() {
		// see ButtonView from TTTStart
	}
	
	public void layoutLeaderboard() {
		pane.setHgap(10);
		pane.setVgap(10);
		
		pane.add(leaderBoardTitle, 1, 1);
		pane.add(leaderBoardTitle, 0, 0);
	}
	
	public void registerHandlers() {
		
	}
}
