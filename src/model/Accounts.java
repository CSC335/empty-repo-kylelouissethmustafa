/*
 * Louis Romeo
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Accounts implements java.io.Serializable {
    private String username;
    private String password;
    private int gamesPlayed;
    private int best2Score;
    private int best3Score;
    private int best4Score;
    private int best5Score;
    private int best6Score;

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
    
    public void incrementGamesPlayed() {
    	this.gamesPlayed++;
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
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
    
    public int getGamesPlayed() {
    	return this.gamesPlayed;
    }

    public boolean equals(Object o) {
    	if(this.getUsername() == ((Accounts) o).getUsername() &&
    			this.getPassword() == ((Accounts) o).getPassword()) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
