/*
 * Louis Romeo
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Accounts class allows for initialization of a user,
 * with username, password, and MemoryGame related statistics.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 *
 */
public class Accounts implements java.io.Serializable {
    private String username;
    private String password;
    private int gamesPlayed;
    private int best2Score;
    private int best3Score;
    private int best4Score;
    private int best5Score;
    private int best6Score;

    /**
     * The constructor for accounts, which sets all best scores
     * to -1 by default, gamesPlayed to 0, and sets username and password.
     * 
     * @param username The Account's username.
     * @param password The Accounts password.
     */
    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;
        this.gamesPlayed = 0;
        // NOTE - if -1, not played that mode yet...
        this.best2Score = -1;
        this.best3Score = -1;
        this.best4Score = -1;
        this.best5Score = -1;
        this.best6Score = -1;
    }
    
    /**
     * Given a newScore and game mode, this method sets a new
     * best score for the account.
     * 
     * @param newScore The value of the newest score.
     * @param gameMode The specified game mode to update best score of.
     */
    public void setNewBestScore(int newScore, int gameMode) {
    	if(gameMode == 2) {
    		this.best2Score = newScore;
    	} else if(gameMode == 3) {
    		this.best3Score = newScore;
    	} else if(gameMode == 4) {
    		this.best4Score = newScore;
    	} else if(gameMode == 5) {
    		this.best5Score = newScore;
    	} else if(gameMode == 6) {
    		this.best6Score = newScore;
    	}
    }
    
    /**
     * Increments games played for the account.
     */
    public void incrementGamesPlayed() {
    	this.gamesPlayed++;
    }
    
    /**
     * A getter for Account's username.
     * @return The account's username.
     */
    public String getUsername() {
    	return this.username;
    }
    
    /**
     * A getter for Account's password.
     * @return The account's password.
     */
    public String getPassword() {
    	return this.password;
    }
    
    /**
     * A getter for the best 2x2 game mode score.
     * 
     * @return The Account's best 2x2 game mode score.
     */
    public Integer getScore() {
    	return this.best2Score;
    	//return String.valueOf(this.best2Score);
    }
    
    /**
     * A getter for the best 2x2 game mode score.
     * 
     * @return The Account's best 2x2 game mode score.
     */
    public Integer get2x2Score() {
    	return this.best2Score;
    }
    
    /**
     * A getter for the best 3x3 game mode score.
     * 
     * @return The Account's best 3x3 game mode score.
     */
    public Integer get3x3Score() {
    	return this.best3Score;
    }
    
    /**
     * A getter for the best 4x4 game mode score.
     * 
     * @return The Account's best 4x4 game mode score.
     */
    public Integer get4x4Score() {
    	return this.best4Score;
    }
    
    /**
     * A getter for the best 5x5 game mode score.
     * 
     * @return The Account's best 5x5 game mode score.
     */
    public Integer get5x5Score() {
    	return this.best5Score;
    }
    
    /**
     * A getter for the best 6x6 game mode score.
     * 
     * @return The Account's best 6x6 game mode score.
     */
    public Integer get6x6Score() {
    	return this.best6Score;
    }
    
    /**
     * For specified game mode, a getter for Account's best score.
     * 
     * @param gameMode The game mode being queried for best user score.
     * @return The best score for the Account's specified game mode.
     */
    public int getBestScore(int gameMode) {
    	int score;
    	if(gameMode == 2) {
    		score = this.best2Score;
    	} else if(gameMode == 3) {
    		score = this.best3Score;
    	} else if(gameMode == 4) {
    		score = this.best4Score;
    	} else if(gameMode == 5) {
    		score = this.best5Score;
    	} else if(gameMode == 6) {
    		score = this.best6Score;
    	} else {
    		score = -2;
    	}
    	
    	if(score == -1) {
    		System.out.println("User has not played this mode");
    		return -1;
    	} else if(score == -2) {
    		System.out.println("Invalid game mode");
    		return score;
    	} else {
    		return score;
    	}
    }
    
    /**
     * A getter for number of games played by the Account.
     * 
     * @return The number of games played by the account.
     */
    public int getGamesPlayed() {
    	return this.gamesPlayed;
    }

    /**
     * An equals method that declares two accounts equal if they have the
     * same username and password.
     */
    public boolean equals(Object o) {
    	if(this.getUsername() == ((Accounts) o).getUsername() &&
    			this.getPassword() == ((Accounts) o).getPassword()) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
