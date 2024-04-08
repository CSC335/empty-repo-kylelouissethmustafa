package controller_view;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.OurObserver;

public class BoardPane extends BorderPane implements OurObserver {
	private GridPane gameGrid;
	
	
	/**
	 * Should eventually maybe pass this the Board object itself...
	 * 
	 */
	public BoardPane() {
		layoutBoard();
		registerHandlers();
	}
	
	public void addMenu() {
		// see ButtonView from TTTStart
	}
	
	public void layoutBoard() {
		gameGrid = new GridPane();
		gameGrid.setPrefWidth(400);
		gameGrid.setPrefHeight(400);
		gameGrid.setHgap(8);
		gameGrid.setVgap(8);
		
		// NOTE: will have to change layout based on num cards
		
		// Add cards here
	}
	
	public void registerHandlers() {
		/**
		 * Register a click by getting x and y coordinates, converting that to
		 * cell on grid, flip that card, wait for second card selection,
		 * and check match. On match, leave them flipped, increment paired guessed
		 * by one. Else, flip both back, reset cards flipped to 0, go back to start...
		 * 
		 */
	}
	
	@Override
	public void update(Object theObserved) {
		// TODO Auto-generated method stub
		
	}
	
}
