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
import model.Card;
import model.MemoryGame;
import model.OurObserver;

public class BoardPane extends BorderPane implements OurObserver {
	private Canvas canvas;
	private GraphicsContext gc;
	private MenuItem newGame;
	private MenuItem leaderboard;
	private MemoryGame game;
	private BorderPane messageArea;
	private GridPane liveStatsGrid;
	
	private Label scoreLbl;
	private Label curScore;
	private Label gamePrompt;

	private int cardSize;
	private int startX;
	private int startY;
	private int cardGap;

	/**
	 * Should eventually maybe pass this the Board object itself...
	 * 
	 */
	public BoardPane(MenuItem newGame, MenuItem leaderboard) {
		layoutBoard();
		//addMenu();
		this.newGame = newGame;
		this.leaderboard = leaderboard;
		

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
		 * This function should know the state of the game... Should blank out the
		 * canvas Should go through all of the cards Figure out their card type, whether
		 * or not flipped display image accordingly.
		 * 
		 * Called initially?, and then by observer as game changes.
		 * 
		 */
		int boardSize = game.getSize(); // TODO - change to game.getSize();

		Image cardBack = new Image("file:BasicCardBack.png");
		Image cardFront = new Image("file:BasicCardInside.png");
		Image redSquare = new Image("file:RedSquare.png");
		Image blueSquare = new Image("file:BlueSquare.png");

		if (boardSize == 2) {
			cardSize = 180;
			startX = 200;
			startY = 30;
			cardGap = 20;
		} else if (boardSize == 4) {
			cardSize = 100;
			startX = 160;
			startY = 20;
			cardGap = 15;
		}

		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				int thisStartX = startX + (col * (cardSize + cardGap));
				int thisStartY = startY + (row * (cardSize + cardGap));
				Card thisCard = game.getBoard().getCard(row, col);
				
				if(thisCard.getRevealed()) {
					if(thisCard.getColor() == "Red" && thisCard.getShape() == "Square") {
						gc.drawImage(redSquare, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Blue" && thisCard.getShape() == "Square") {
						gc.drawImage(blueSquare, thisStartX, thisStartY, cardSize, cardSize);
					} else {
						gc.drawImage(cardFront, thisStartX, thisStartY, cardSize, cardSize);
					}
				} else {
					gc.drawImage(cardBack, thisStartX, thisStartY, cardSize, cardSize);
				}
			}
		}

	}

	public void drawCardsTest() {
		/**
		 * This function should know the state of the game... Should blank out the
		 * canvas Should go through all of the cards Figure out their card type, whether
		 * or not flipped display image accordingly.
		 * 
		 * Called initially, and then by observer as game changes.
		 * 
		 */

		// EVERYTHING BELOW IS TESTING CODE
		Image cardBack = new Image("file:BasicCardBack.png");

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

		// drawCards(); // might not need this on initialization
		
		liveStatsGrid = new GridPane();
		messageArea = new BorderPane();
		
		scoreLbl = new Label("Score:");
		scoreLbl.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		curScore = new Label("0");
		curScore.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		gamePrompt = new Label("Start a New Game!");
		gamePrompt.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: blue");
		liveStatsGrid.setHgap(5);
		
		liveStatsGrid.add(scoreLbl, 0, 0);
		liveStatsGrid.add(curScore, 1, 0);
		
		messageArea.setTop(gamePrompt);
		messageArea.setCenter(liveStatsGrid);
		liveStatsGrid.setPadding(new Insets(0, 0, 0, 60));
		messageArea.setPadding(new Insets(0, 0, 0, 300));

		this.setTop(canvas);
		this.setCenter(messageArea);
	}

	public void registerHandlers() {
		/**
		 * Register a click by getting x and y coordinates, converting that to cell on
		 * grid, flip that card, wait for second card selection, and check match. On
		 * match, leave them flipped, increment paired guessed by one. Else, flip both
		 * back, reset cards flipped to 0, go back to start...
		 * 
		 */

		/**
		 * In the future here, we probably should detect the card and update the game
		 * accordingly Since game is a Observable and this is observer, every time the
		 * game changes, this class should be notified and then we should call the
		 * drawCards method again...
		 */

		canvas.setOnMousePressed(event -> {
			if (game != null && game.gameActive()) { // TODO - and game is active
				double curX = event.getSceneX() - 1.6;
				double curY = event.getSceneY() - 22.4;
				int gameSize = game.getSize(); // change to = game.getSize();

				int xBorder = startX + ((cardSize + cardGap) * (gameSize - 1)) + cardSize;
				int yBorder = startY + ((cardSize + cardGap) * (gameSize - 1)) + cardSize;

				int curRow = -1;
				int curCol = -1;
				
				if (curX >= startX && curX <= xBorder && curY >= startY && curY <= yBorder) {
					if (curX <= startX + cardSize) {
						curCol = 0;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					} else if (curX >= (startX + 1 * (cardSize + cardGap))
							&& curX <= (startX + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
						curCol = 1;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					} else if (curX >= (startX + 2 * (cardSize + cardGap))
							&& curX <= (startX + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
						curCol = 2;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					} else if (curX >= (startX + 3 * (cardSize + cardGap))
							&& curX <= (startX + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
						curCol = 3;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					} else if (curX >= (startX + 4 * (cardSize + cardGap))
							&& curX <= (startX + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
						curCol = 4;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					} else if (curX >= (startX + 5 * (cardSize + cardGap))
							&& curX <= (startX + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
						curCol = 5;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					}
					if(curRow == -1 | curCol == -1) {
						System.out.println("Registered invalid click");
					} else {
						System.out.println("user clicked row: " + curRow + " col: " + curCol);
						game.cardClicked(curRow, curCol);
					}
				}
			}
		});

		newGame.setOnAction(event -> {
			System.out.println("New Game Clicked");
			game = new MemoryGame(0, 2);
			game.addObserver(this);
			game.initGame();
			gamePrompt.setText("Click Cards to Begin!");
		});
		
	}

	@Override
	public void update(Object theObserved) {
		// TODO Auto-generated method stub
		drawCards();
		curScore.setText("" + game.getScore());
		if(!game.gameActive()) {
			gamePrompt.setText("Congrats! You won!");
		}
	}

}
