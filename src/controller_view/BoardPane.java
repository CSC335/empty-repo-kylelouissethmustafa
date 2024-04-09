package controller_view;
import java.io.File;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.OurObserver;

public class BoardPane extends BorderPane implements OurObserver {
	private Canvas canvas;
	private GraphicsContext gc;
	private MenuItem newGame;
	
	/**
	 * Should eventually maybe pass this the Board object itself...
	 * 
	 */
	public BoardPane() {
		addMenu();
		layoutBoard();
		
		this.registerHandlers();
	}
	
	public void addMenu() {
		// see ButtonView from TTTStart
		newGame = new MenuItem("New Game");
		MenuItem other = new MenuItem("Other");
		
		Menu options = new Menu("Options");
		options.getItems().addAll(newGame, other);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(options);
		
		this.setTop(menuBar);
	}
	
	public void drawCards() {
		/**
		 * This function should know the state of the game...
		 * Should blank out the canvas
		 * Should go through all of the cards
		 * Figure out their card type, whether or not flipped
		 * 		display image accordingly.
		 * 
		 * Called initially, and then by observer as game changes.
		 * 
		 */
		
		// EVERYTHING BELOW IS TESTING CODE
		Image cardBack = new Image("file:BasicCardBack.png");
		ImageView backView = new ImageView(cardBack);
		backView.setFitWidth(40);
		backView.setFitHeight(40);
		
		// start x, start y, offset x, offset y
		gc.drawImage(cardBack, 30, 30, 180, 180);
		gc.drawImage(cardBack, 230, 30, 180, 180);
		gc.drawImage(cardBack, 430, 30, 180, 180);
		gc.drawImage(cardBack, 630, 30, 180, 180);
		
		gc.drawImage(cardBack, 30, 230, 180, 180);
		gc.drawImage(cardBack, 230, 230, 180, 180);
		gc.drawImage(cardBack, 430, 230, 180, 180);
		gc.drawImage(cardBack, 630, 230, 180, 180);
	}
	
	public void layoutBoard() {
		this.setMinWidth(850);
		this.setMinHeight(500);
		
		canvas = new Canvas(850, 480);
		gc = canvas.getGraphicsContext2D();
		
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		drawCards();
		
		this.setCenter(canvas);
		
	}
	
	public void registerHandlers() {
		/**
		 * Register a click by getting x and y coordinates, converting that to
		 * cell on grid, flip that card, wait for second card selection,
		 * and check match. On match, leave them flipped, increment paired guessed
		 * by one. Else, flip both back, reset cards flipped to 0, go back to start...
		 * 
		 */
		
		/**
		 * In the future here, we probably should detect the card and update the game accordingly
		 * Since game is a Observable and this is observer, every time the game changes, 
		 * this class should be notified and then we should call the drawCards method again...
		 */
		canvas.setOnMousePressed( event -> {
			double curX = event.getSceneX() - 1.6;
			double curY = event.getSceneY() - 22.4;
			//System.out.println("clicked point: " + curX + " " + curY);
			if(curX >= 30 && curX <= 810 && curY >= 30 && curY <= 410) {
				if(curX <= 210) {
					if(curY <= 210) {
						System.out.println("Card 1");
					} else if(curY >= 230) {
						System.out.println("Card 5");
					}
				} else if(curX >= 230 && curX <= 410) {
					if(curY <= 210) {
						System.out.println("Card 2");
					} else if(curY >= 230) {
						System.out.println("Card 6");
					}
				} else if(curX >= 430 && curX <= 610) {
					if(curY <= 210) {
						System.out.println("Card 3");
					} else if(curY >= 230) {
						System.out.println("Card 7");
					}
				} else if(curX >= 630) {
					if(curY <= 210) {
						System.out.println("Card 4");
					} else if(curY >= 230) {
						System.out.println("Card 8");
					}
				}
			}
		});
		
		newGame.setOnAction(event -> {
			System.out.println("New Game Clicked");
		});
	}
	
	@Override
	public void update(Object theObserved) {
		// TODO Auto-generated method stub
		
	}
	
}
