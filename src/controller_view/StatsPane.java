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
    private int totalScore;
    private Label best2x2Score;
    private Label best3x3Score;
    private Label best4x4Score;
    private Label best5x5Score;
    private Label best6x6Score;
    private int best2x2Val;
    private int best3x3Val;
    private int best4x4Val;
    private int best5x5Val;
    private int best6x6Val;
    private String best2x2String;
    private String best3x3String;
    private String best4x4String;
    private String best5x5String;
    private String best6x6String;
    private Label usernameLabel;
    private Label gamesPlayedLabel;
    private Label best2x2Label;
    private Label best3x3Label;
    private Label best4x4Label;
    private Label best5x5Label;
    private Label best6x6Label;
    private memoryGUI gui;
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
    	this.username =  new Label(gui.getCurrAcct().getUsername());  
    	this.totalGamesPlayed = new Label(String.valueOf(gui.getCurrAcct().getGamesPlayed()));
    	this.best2x2Val = gui.getCurrAcct().get2x2Score();
    	this.best3x3Val = gui.getCurrAcct().get3x3Score();
    	this.best4x4Val = gui.getCurrAcct().get4x4Score();
    	this.best5x5Val = gui.getCurrAcct().get5x5Score();
    	this.best6x6Val = gui.getCurrAcct().get6x6Score();
    	if(this.best2x2Val < 0){
    		this.best2x2String = "Not Attempted";
    	} else {
    		this.best2x2String = String.valueOf(this.best2x2Val);
    	}
    	if(this.best3x3Val < 0){
    		this.best3x3String = "Not Attempted";
    	} else {
    		this.best3x3String = String.valueOf(this.best3x3Val);
    	}
    	if(this.best4x4Val < 0){
    		this.best4x4String = "Not Attempted";
    	} else {
    		this.best4x4String = String.valueOf(this.best4x4Val);
    	}
    	if(this.best5x5Val < 0){
    		this.best5x5String = "Not Attempted";
    	} else {
    		this.best5x5String = String.valueOf(this.best5x5Val);
    	}
    	if(this.best6x6Val < 0){
    		this.best6x6String = "Not Attempted";
    	} else {
    		this.best6x6String = String.valueOf(this.best6x6Val);
    	}
    	
    	setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
    	
    	this.best2x2Score = new Label(this.best2x2String);
    	this.best3x3Score = new Label(this.best3x3String);
    	this.best4x4Score = new Label(this.best4x4String);
    	this.best5x5Score = new Label(this.best5x5String);
    	this.best6x6Score = new Label(this.best6x6String);
    	
    	logoImage = new Image("file:StatsLogo.png");
    	canvas = new Canvas(800, 200);
        gc = canvas.getGraphicsContext2D();
        
        gc.drawImage(logoImage, 250, 0, 200, 150);
    	
    	userStats = new Label("User Stats");
    	userStats.setStyle("-fx-font-size: 30; -fx-font-weight: bold");
    	
        usernameLabel = new Label("Username: ");
        gamesPlayedLabel = new Label("Games Played: ");
        best2x2Label = new Label("Best 2x2 Score: ");
        best3x3Label = new Label("Best 3x3 Score: ");
        best4x4Label = new Label("Best 4x4 Score: ");
        best5x5Label = new Label("Best 5x5 Score: ");
        best6x6Label = new Label("Best 6x6 Score: ");
        
        usernameLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        gamesPlayedLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        best2x2Label.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        best3x3Label.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        best4x4Label.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        best5x5Label.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        best6x6Label.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        
        username.setStyle("-fx-font-size: 15;");
        totalGamesPlayed.setStyle("-fx-font-size: 15;");
        best2x2Score.setStyle("-fx-font-size: 15;");
        best3x3Score.setStyle("-fx-font-size: 15;");
        best4x4Score.setStyle("-fx-font-size: 15;");
        best5x5Score.setStyle("-fx-font-size: 15;");
        best6x6Score.setStyle("-fx-font-size: 15;");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        
        grid.add(userStats, 27, 0);
        grid.add(usernameLabel, 27, 1);
        grid.add(username, 28, 1);
        grid.add(gamesPlayedLabel, 27, 2);
        grid.add(totalGamesPlayed, 28, 2);
        grid.add(best2x2Label, 27, 3);
        grid.add(best2x2Score, 28, 3);
        grid.add(best3x3Label, 27, 4);
        grid.add(best3x3Score, 28, 4);
        grid.add(best4x4Label, 27, 5);
        grid.add(best4x4Score, 28, 5);
        grid.add(best5x5Label, 27, 6);
        grid.add(best5x5Score, 28, 6);
        grid.add(best6x6Label, 27, 7);
        grid.add(best6x6Score, 28, 7);

        this.setTop(canvas);
        setCenter(grid);
        
        this.setPadding(new Insets(20, 20, 20, 100));
        // top, blank, blank, left
    }
}
