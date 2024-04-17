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
import model.Accounts;
import model.Card;
import model.MemoryGame;
import model.OurObserver;

/**
 * The BoardPane class is a BorderPane that displays the MemoryGame
 * and allows for user interaction with the game. Live stats and
 * game messages are displayed at the bottom of the pane.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 *
 */
public class BoardPane extends BorderPane implements OurObserver {
	private Canvas canvas;
	private GraphicsContext gc;
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
	
	private memoryGUI gui;

	/**
	 * The Board Pane constructor, which lays out the game board
	 * visually and registers event handlers.
	 * 
	 * @param gui A reference to the memoryGUI so its methods may be used.
	 * 
	 */
	public BoardPane(memoryGUI gui) {
		layoutBoard();
		this.gui = gui;
		

		this.registerHandlers();
	}

	/**
	 * This method draws the cards of the memory game to the canvas,
	 * taking into account their state and card value.
	 * 
	 */
	public void drawCards() {
		/**
		 * This function should know the state of the game... Should blank out the
		 * canvas Should go through all of the cards Figure out their card type, whether
		 * or not flipped display image accordingly.
		 * 
		 * Called initially?, and then by observer as game changes.
		 * 
		 */
		clearCanvas();
		int boardSize = game.getSize();

		Image cardBack = new Image("file:BasicCardBack.png");
		Image cardFront = new Image("file:BasicCardInside.png");
		Image redSquare = new Image("file:RedSquare.png");
		Image redCircle = new Image("file:RedCircle.png");
		Image redPlus = new Image("file:RedPlus.png");
		Image blueSquare = new Image("file:BlueSquare.png");
		Image blueCircle = new Image("file:BlueCircle.png");
		Image bluePlus = new Image("file:BluePlus.png");
		Image greenSquare = new Image("file:GreenSquare.png");
		Image greenCircle = new Image("file:GreenCircle.png");
		Image greenPlus = new Image("file:GreenPlus.png");
		Image blackSquare = new Image("file:BlackSquare.png");
		Image blackCircle = new Image("file:BlackCircle.png");
		Image blackPlus = new Image("file:BlackPlus.png");
		Image yellowSquare = new Image("file:YellowSquare.png");
		Image yellowCircle = new Image("file:YellowCircle.png");
		Image yellowPlus = new Image("file:YellowPlus.png");
		Image orangeSquare = new Image("file:OrangeSquare.png");
		Image orangeCircle = new Image("file:OrangeCircle.png");
		Image orangePlus = new Image("file:OrangePlus.png");

		if (boardSize == 2) {
			cardSize = 180;
			startX = 210;
			startY = 40;
			cardGap = 20;
		} else if(boardSize == 3) {
			cardSize = 140;
			startX = 180;
			startY = 10;
			cardGap = 15;
		} else if (boardSize == 4) {
			cardSize = 100;
			startX = 175;
			startY = 15;
			cardGap = 15;
		} else if(boardSize == 5) {
			cardSize = 75;
			startX = 180;
			startY = 10;
			cardGap = 15;
		} else if(boardSize == 6) {
			cardSize = 60;
			startX = 180;
			startY = 15;
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
					} else if(thisCard.getColor() == "Red" && thisCard.getShape() == "Circle") {
						gc.drawImage(redCircle, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Red" && thisCard.getShape() == "Plus") {
						gc.drawImage(redPlus, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Blue" && thisCard.getShape() == "Square") {
						gc.drawImage(blueSquare, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Blue" && thisCard.getShape() == "Circle") {
						gc.drawImage(blueCircle, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Blue" && thisCard.getShape() == "Plus") {
						gc.drawImage(bluePlus, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Green" && thisCard.getShape() == "Square") {
						gc.drawImage(greenSquare, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Green" && thisCard.getShape() == "Circle") {
						gc.drawImage(greenCircle, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Green" && thisCard.getShape() == "Plus") {
						gc.drawImage(greenPlus, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Black" && thisCard.getShape() == "Square") {
						gc.drawImage(blackSquare, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Black" && thisCard.getShape() == "Circle") {
						gc.drawImage(blackCircle, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Black" && thisCard.getShape() == "Plus") {
						gc.drawImage(blackPlus, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Yellow" && thisCard.getShape() == "Square") {
						gc.drawImage(yellowSquare, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Yellow" && thisCard.getShape() == "Circle") {
						gc.drawImage(yellowCircle, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Yellow" && thisCard.getShape() == "Plus") {
						gc.drawImage(yellowPlus, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Orange" && thisCard.getShape() == "Square") {
						gc.drawImage(orangeSquare, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Orange" && thisCard.getShape() == "Circle") {
						gc.drawImage(orangeCircle, thisStartX, thisStartY, cardSize, cardSize);
					} else if(thisCard.getColor() == "Orange" && thisCard.getShape() == "Plus") {
						gc.drawImage(orangePlus, thisStartX, thisStartY, cardSize, cardSize);
					} else {
						gc.drawImage(cardFront, thisStartX, thisStartY, cardSize, cardSize);
					}
				} else {
					gc.drawImage(cardBack, thisStartX, thisStartY, cardSize, cardSize);
				}
			}
		}

	}
	
	/**
	 * This method clears the canvas before redrawing of a new game.
	 */
	public void clearCanvas() {
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gamePrompt.setText("Start a New Game!");
		curScore.setText("0");
	}
	
	/**
	 * This method creates the canvas for the game and adds
	 * the game prompt and live score to the pane.
	 */
	public void layoutBoard() {
		this.setMinWidth(850);
		this.setMinHeight(500);

		canvas = new Canvas(850, 480);
		gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
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

	/**
	 * This method registers handlers for the BoardPane, in this case
	 * specifically, it handles clicks on the canvas. Calculations are
	 * made based on game mode and board size to handle clicks of cards
	 * properly, and updating of account statistics occurs on ending of a
	 * game.
	 */
	public void registerHandlers() {

		canvas.setOnMousePressed(event -> {
			if (game != null && game.gameActive()) {
				double curX = event.getSceneX() - 1.6;
				double curY = event.getSceneY() - 22.4;
				int gameSize = game.getSize();

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
			
			if(game != null && !game.gameActive()) {
				System.out.println("Game has ended!");
				System.out.println("Game mode: " + game.getGameMode() + " size: " + game.getSize());
				Accounts currAcct = gui.getCurrAcct();
				currAcct.incrementGamesPlayed();
				if(game.getGameMode() == 0 && game.getSize() == 2) {
					if((game.getScore() < currAcct.get2x2Score()) | currAcct.get2x2Score() == -1) {
						currAcct.setNewBestScore(game.getScore(), game.getSize());
					}
				} else if(game.getGameMode() == 1 && game.getSize() == 3) {
					if((game.getScore() < currAcct.get3x3Score())  | currAcct.get3x3Score() == -1) {
						currAcct.setNewBestScore(game.getScore(), game.getSize());
					}
				} else if(game.getGameMode() == 0 && game.getSize() == 4) {
					if((game.getScore() < currAcct.get4x4Score())  | currAcct.get4x4Score() == -1) {
						currAcct.setNewBestScore(game.getScore(), game.getSize());
					}
				} else if(game.getGameMode() == 1 && game.getSize() == 5) {
					if((game.getScore() < currAcct.get5x5Score()) | currAcct.get5x5Score() == -1) {
						currAcct.setNewBestScore(game.getScore(), game.getSize());
					}
				} else if(game.getGameMode() == 0 && game.getSize() == 6) {
					if((game.getScore() < currAcct.get6x6Score()) | currAcct.get6x6Score() == -1) {
						currAcct.setNewBestScore(game.getScore(), game.getSize());
					}
				}
			}
		});
		
	}
	
	/**
	 * This method creates a new MemoryGame, adding this Pane as an observer,
	 * initializes the game, and sets the game prompt.
	 * 
	 * @param gameMode the integer representing the current game's game mode.
	 * @param boardSize  the integer representing the current game's board size.
	 */
	public void startNewGame(int gameMode, int boardSize) {
		System.out.println("New Game Clicked");
		game = new MemoryGame(gameMode, boardSize);
		game.addObserver(this);
		game.initGame();
		gamePrompt.setText("Click Cards to Begin!");
	}

	@Override
	/**
	 * The method that is called on change of state of the MemoryGame, redrawing
	 * cards.
	 * 
	 * @param theObserved represents the MemoryGame being observed by this Pane.
	 */
	public void update(Object theObserved) {
		drawCards();
		curScore.setText("" + game.getScore());
		if(!game.gameActive()) {
			gamePrompt.setText("Congrats! You won!");
		}
	}

}
