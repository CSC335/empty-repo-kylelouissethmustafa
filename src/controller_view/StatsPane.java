package controller_view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Accounts;

/**
 * The StatsPane class is a BorderPane that is added to memoryGUI
 * to view game statistics of the current user.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 *
 */
public class StatsPane extends BorderPane {
    
	private Accounts accounts;
    private Label username;
    private Label totalGamesPlayed;
    private Label usernameLabel;
    private Label gamesPlayedLabel;
    
    private int best2Norm;
    private int best3Odd;
    private int best3ThreeKind;
    private int best4Norm;
    private int best5Odd;
    private int best6Norm;
    private int best6ThreeKind;
    private Label str2Norm;
    private Label str3Odd;
    private Label str3ThreeKind;
    private Label str4Norm;
    private Label str5Odd;
    private Label str6Norm;
    private Label str6ThreeKind;
    
    
    
    
    private memoryGUI gui;
    private BorderPane pane;
    private GridPane grid;
    
    private Label userStats;

    private Image logoImage;
    
    private Canvas canvas;
	private GraphicsContext gc;
    /**
     * The constructor for StatsPane, simply sets the gui instance variable.
     * 
     * @param gui The memoryGUI for referencing of methods.
     */
    public StatsPane(memoryGUI gui) {
        this.gui = gui;
    }

    /**
     * This method lays out the StatsPane by creating a GridPane with numerous
     * labels that describe the current User's memory game statistics.
     */
    public void layoutStatsPane() {
    	Accounts curAcct = gui.getCurrAcct();
    	
    	this.username =  new Label(curAcct.getUsername());  
    	this.totalGamesPlayed = new Label(String.valueOf(curAcct.getGamesPlayed()));
    	
    	
    	this.best2Norm = curAcct.getBestScore(2, 0);
        this.best3Odd = curAcct.getBestScore(3, 1);
        this.best3ThreeKind = curAcct.getBestScore(3, 2);
        this.best4Norm = curAcct.getBestScore(4, 0);
        this.best5Odd = curAcct.getBestScore(5, 1);
        this.best6Norm = curAcct.getBestScore(6, 0);
        this.best6ThreeKind = curAcct.getBestScore(6, 2);
        
        str2Norm = new Label("Best 2x2 Normal Score: ");
        str3Odd = new Label("Best 3x3 Odd One Out Score:");
        str3ThreeKind = new Label("Best 3x3 Three of a Kind Score: ");
        str4Norm = new Label("Best 4x4 Normal Score: ");
        str5Odd = new Label("Best 5x5 Odd One Out Score: ");
        str6Norm = new Label("Best 6x6 Normal Score: ");
        str6ThreeKind = new Label("Best 6x6 Three of a Kind Score: ");
        
    	
    	setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
    	
    	logoImage = new Image("file:StatsLogo.png");
    	canvas = new Canvas(800, 200);
        gc = canvas.getGraphicsContext2D();
        
        gc.drawImage(logoImage, 290, 0, 200, 200);
    	
    	userStats = new Label("User Stats");
    	userStats.setStyle("-fx-font-size: 50; -fx-font-weight: bold");
    	userStats.setPadding(new Insets(0,0,10,0));
    	
        usernameLabel = new Label("Username: ");
        gamesPlayedLabel = new Label("Games Played: ");
        
        usernameLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        gamesPlayedLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        str2Norm.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        str3Odd.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        str3ThreeKind.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        str4Norm.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        str5Odd.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        str6Norm.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        str6ThreeKind.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        
        username.setStyle("-fx-font-size: 15;");
        totalGamesPlayed.setStyle("-fx-font-size: 15;");
        Label lbl2Norm = new Label(String.valueOf(best2Norm));
        if(lbl2Norm.getText().equals("-1")) {
        	lbl2Norm.setText("Not Attempted");
        }
        lbl2Norm.setStyle("-fx-font-size: 15;");
        Label lbl3Odd = new Label(String.valueOf(best3Odd));
        if(lbl3Odd.getText().equals("-1")) {
        	lbl3Odd.setText("Not Attempted");
        }
        lbl3Odd.setStyle("-fx-font-size: 15;");
        Label lbl3ThreeKind = new Label(String.valueOf(best3ThreeKind));
        if(lbl3ThreeKind.getText().equals("-1")) {
        	lbl3ThreeKind.setText("Not Attempted");
        }
        lbl3ThreeKind.setStyle("-fx-font-size: 15;");
        Label lbl4Norm = new Label(String.valueOf(best4Norm));
        if(lbl4Norm.getText().equals("-1")) {
        	lbl4Norm.setText("Not Attempted");
        }
        lbl4Norm.setStyle("-fx-font-size: 15;");
        Label lbl5Odd = new Label(String.valueOf(best5Odd));
        if(lbl5Odd.getText().equals("-1")) {
        	lbl5Odd.setText("Not Attempted");
        }
        lbl5Odd.setStyle("-fx-font-size: 15;");
        Label lbl6Norm = new Label(String.valueOf(best6Norm));
        if(lbl6Norm.getText().equals("-1")) {
        	lbl6Norm.setText("Not Attempted");
        }
        lbl6Norm.setStyle("-fx-font-size: 15;");
        Label lbl6ThreeKind = new Label(String.valueOf(best6ThreeKind));
        if(lbl6ThreeKind.getText().equals("-1")) {
        	lbl6ThreeKind.setText("Not Attempted");
        }
        lbl6ThreeKind.setStyle("-fx-font-size: 15;");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        
        //grid.add(userStats, 0, 0);
        grid.add(usernameLabel, 0, 1);
        grid.add(username, 1, 1);
        grid.add(gamesPlayedLabel, 0, 2);
        grid.add(totalGamesPlayed, 1, 2);
        grid.add(str2Norm, 0, 3);
        grid.add(lbl2Norm, 1, 3);
        grid.add(str3Odd, 0, 4);
        grid.add(lbl3Odd, 1, 4);
        grid.add(str3ThreeKind, 0, 5);
        grid.add(lbl3ThreeKind, 1, 5);
        grid.add(str4Norm, 0, 6);
        grid.add(lbl4Norm, 1, 6);
        grid.add(str5Odd, 0, 7);
        grid.add(lbl5Odd, 1, 7);
        grid.add(str6Norm, 0, 8);
        grid.add(lbl6Norm, 1, 8);
        grid.add(str6ThreeKind, 0, 9);
        grid.add(lbl6ThreeKind, 1, 9);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(180);
        grid.getColumnConstraints().add(col1);

        pane = new BorderPane();
        pane.setTop(userStats);
        pane.setCenter(grid);
        
        pane.setPadding(new Insets(-20, 0, 0, 270));
        
        this.setTop(canvas);
        this.setCenter(pane);
        
        this.setPadding(new Insets(0, 20, 20, 20));
        // top, right, bottom, left
    }
}
