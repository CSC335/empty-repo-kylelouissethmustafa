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
	
	private Image cardBack;
	private Image cardFront;
	private Image a1;
	private Image a2;
	private Image a3;
	private Image b1;
	private Image b2;
	private Image b3;
	private Image c1;
	private Image c2;
	private Image c3;
	private Image d1;
	private Image d2;
	private Image d3;
	private Image e1;
	private Image e2;
	private Image e3;
	private Image f1;
	private Image f2;
	private Image f3;
	
	
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
		
		initImages();

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
					drawCard(thisCard, thisStartX, thisStartY);
				} else {
					gc.drawImage(cardBack, thisStartX, thisStartY, cardSize, cardSize);
				}
			}
		}

	}
	
	private void initImages() {
		cardBack = new Image("file:img/BasicCardBack.png");
		cardFront = new Image("file:img/BasicCardInside.png");
		
		int gameTheme = game.getTheme();
		
		if(gameTheme == 0) {
			a1 = new Image("file:img/RedSquare.png");
			a2 = new Image("file:img/RedCircle.png");
			a3 = new Image("file:img/RedPlus.png");
			b1 = new Image("file:img/BlueSquare.png");
			b2 = new Image("file:img/BlueCircle.png");
			b3 = new Image("file:img/BluePlus.png");
			c1 = new Image("file:img/GreenSquare.png");
			c2 = new Image("file:img/GreenCircle.png");
			c3 = new Image("file:img/GreenPlus.png");
			d1 = new Image("file:img/BlackSquare.png");
			d2 = new Image("file:img/BlackCircle.png");
			d3 = new Image("file:img/BlackPlus.png");
			e1 = new Image("file:img/YellowSquare.png");
			e2 = new Image("file:img/YellowCircle.png");
			e3 = new Image("file:img/YellowPlus.png");
			f1 = new Image("file:img/OrangeSquare.png");
			f2 = new Image("file:img/OrangeCircle.png");
			f3 = new Image("file:img/OrangePlus.png");
		} else if(gameTheme == 1) {
			a1 = new Image("file:img/Ape.png");
			a2 = new Image("file:img/Bear.png");
			a3 = new Image("file:img/Beaver.png");
			b1 = new Image("file:img/Bulldog.png");
			b2 = new Image("file:img/ClimbMonkey.png");
			b3 = new Image("file:img/Deer.png");
			c1 = new Image("file:img/Dog.png");
			c2 = new Image("file:img/Duck.png");
			c3 = new Image("file:img/Elephant.png");
			d1 = new Image("file:img/FlySquirrel.png");
			d2 = new Image("file:img/Frog.png");
			d3 = new Image("file:img/Goose.png");
			e1 = new Image("file:img/Lion.png");
			e2 = new Image("file:img/Monkey.png");
			e3 = new Image("file:img/Pig.png");
			f1 = new Image("file:img/Raccoon.png");
			f2 = new Image("file:img/Sheep.png");
			f3 = new Image("file:img/Tiger.png");
		} else if(gameTheme == 2) {
			a1 = new Image("file:img/Alien.png");
			a2 = new Image("file:img/alienShip.png");
			a3 = new Image("file:img/asteroid.png");
			b1 = new Image("file:img/Earth.png");
			b2 = new Image("file:img/FullShip.png");
			b3 = new Image("file:img/Jupiter.png");
			c1 = new Image("file:img/Mars.png");
			c2 = new Image("file:img/Neptune.png");
			c3 = new Image("file:img/opAlien.png");
			d1 = new Image("file:img/orangeAlien.png");
			d2 = new Image("file:img/orangeAstronaut.png");
			d3 = new Image("file:img/pbAlien.png");
			e1 = new Image("file:img/rbRocket.png");
			e2 = new Image("file:img/robot.png");
			e3 = new Image("file:img/rover.png");
			f1 = new Image("file:img/spaceMachine.png");
			f2 = new Image("file:img/Uranus.png");
			f3 = new Image("file:img/whiteAstronaut.png");
		}
	}
	
	public void drawCard(Card thisCard, int thisStartX, int thisStartY) {
		if("A".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
		    gc.drawImage(a1, thisStartX, thisStartY, cardSize, cardSize);
		} else if("A".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
		    gc.drawImage(a2, thisStartX, thisStartY, cardSize, cardSize);
		} else if("A".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
		    gc.drawImage(a3, thisStartX, thisStartY, cardSize, cardSize);
		} else if("B".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
		    gc.drawImage(b1, thisStartX, thisStartY, cardSize, cardSize);
		} else if("B".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
		    gc.drawImage(b2, thisStartX, thisStartY, cardSize, cardSize);
		} else if("B".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
		    gc.drawImage(b3, thisStartX, thisStartY, cardSize, cardSize);
		} else if("C".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
		    gc.drawImage(c1, thisStartX, thisStartY, cardSize, cardSize);
		} else if("C".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
		    gc.drawImage(c2, thisStartX, thisStartY, cardSize, cardSize);
		} else if("C".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
		    gc.drawImage(c3, thisStartX, thisStartY, cardSize, cardSize);
		} else if("D".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
		    gc.drawImage(d1, thisStartX, thisStartY, cardSize, cardSize);
		} else if("D".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
		    gc.drawImage(d2, thisStartX, thisStartY, cardSize, cardSize);
		} else if("D".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
		    gc.drawImage(d3, thisStartX, thisStartY, cardSize, cardSize);
		} else if("E".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
		    gc.drawImage(e1, thisStartX, thisStartY, cardSize, cardSize);
		} else if("E".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
		    gc.drawImage(e2, thisStartX, thisStartY, cardSize, cardSize);
		} else if("E".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
		    gc.drawImage(e3, thisStartX, thisStartY, cardSize, cardSize);
		} else if("F".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
		    gc.drawImage(f1, thisStartX, thisStartY, cardSize, cardSize);
		} else if("F".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
		    gc.drawImage(f2, thisStartX, thisStartY, cardSize, cardSize);
		} else if("F".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
		    gc.drawImage(f3, thisStartX, thisStartY, cardSize, cardSize);
		} else {
			System.out.println("Drawing generic...");
			gc.drawImage(cardFront, thisStartX, thisStartY, cardSize, cardSize);
		}
	}
	
	/**
	 * This method clears the canvas before redrawing of a new game.
	 */
	public void clearCanvas() {
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		if(this.game == null) {
			gamePrompt.setText("Start a New Game!");
		} else {
			gamePrompt.setText("You are doing great!");
		}
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
		messageArea.setPadding(new Insets(0, 0, 0, 310));

		this.setTop(canvas);
		this.setCenter(messageArea);
	}
	
	// TODO - need scores for other game modes.
	private void updateScores() {
		if(game != null && !game.gameActive()) {
			System.out.println("Game has ended!");
			Accounts currAcct = gui.getCurrAcct();
			currAcct.incrementGamesPlayed();
			
			currAcct.setNewBestScore(game.getScore(), game.getSize(), game.getGameMode());
		}
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
						game.cardClicked(curRow, curCol);
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
	public void startNewGame(int gameMode, int boardSize, int gameTheme) {
		System.out.println("New Game Clicked");
		game = new MemoryGame(gameMode, boardSize, gameTheme);
		game.addObserver(this);
		game.initGame();
		gamePrompt.setText("Click Cards to Begin!");
		gui.getCurrAcct().setNewGame(game);
	}
	
	public void setGame(MemoryGame game) {
		this.game = game;
		this.game.addObserver(this);
		drawCards();
		gamePrompt.setText("Resume Your Game!");
		curScore.setText("" + game.getScore());
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
			updateScores();
			gui.getCurrAcct().endCurrGame();
		}
	}

}
